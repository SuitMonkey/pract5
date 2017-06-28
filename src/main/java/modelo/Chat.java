package modelo;

import org.eclipse.jetty.websocket.api.Session;

/**
 * Created by Francis Cáceres on 27/6/2017.
 */
public class Chat {
    private String nombre;
    private Session session;
    private String mensaje;

    public Chat(String nombre, Session session, String mensaje) {
        this.nombre = nombre;
        this.session = session;
        this.mensaje = mensaje;
    }

    public String getNombre() {
        return nombre;
    }

    public Session getSession() {
        return session;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public void setSession(Session session) {
        this.session = session;
    }
}
