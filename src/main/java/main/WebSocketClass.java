package main;

import modelo.Chat;
import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketClose;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketConnect;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketMessage;
import org.eclipse.jetty.websocket.api.annotations.WebSocket;

import java.io.IOException;

/**
 * Created by Francis Cáceres on 25/6/2017.
 */
@WebSocket
public class WebSocketClass {

    private boolean existe = false;

    @OnWebSocketConnect
    public void conectando(Session usuario){
        System.out.println("Conectando Usuario: "+usuario.getLocalAddress().getAddress().toString());
        //Main.usuariosConectados.add(usuario);
    }

    /**
     * Una vez cerrado la conexión, es ejecutado el metodo anotado
     * @param usuario
     * @param statusCode
     * @param reason
     */
    @OnWebSocketClose
    public void cerrandoConexion(Session usuario, int statusCode, String reason) {
        System.out.println("Desconectando el usuario: "+usuario.getLocalAddress().getAddress().toString());
        Main.usuariosConectados.remove(usuario);
    }

    /**
     * Una vez recibido un mensaje es llamado el metodo anotado.
     * @param usuario
     * @param message
     */
    @OnWebSocketMessage
    public void recibiendoMensaje(Session usuario, String message) {
        System.out.println("Recibiendo del cliente: "+usuario.getLocalAddress().getAddress().toString()+" - Mensaje: "+message);
        try {

            //Enviar un simple mensaje al cliente que mando al servidor..
            //usuario.getRemote().sendString("Mensaje enviado al Servidor: "+message);

            //mostrando a todos los clientes
            //Main.enviarMensajeAClientesConectados(message, "azul");

            String[] partes = message.split("/");

            for(Chat u : Main.usuariosConectados){
                if(u.getNombre().equals(partes[0])){
                    if(u.getSession() != usuario){
                        u.setSession(usuario);
                    }
                    existe = true;
                    u.setMensaje(partes[1]);
                    break;
                }
            }
            if(!existe) {
                if (partes[0].equals("adminAutor")) {
                    Main.usuariosConectados.add(new Chat(partes[0], usuario, ""));
                } else {
                    Main.usuariosConectados.add(new Chat(partes[0], usuario, partes[1]));
                }
            }

            if(partes[0].equals("adminAutor")){
                Main.enviarMensajeAlCliente(partes[1],partes[2]);
            }else {
                Main.enviarMensajeAlCliente("adminAutor",partes[1]);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
