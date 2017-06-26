package modelo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jesus on 6/25/2017.
 */
public class DatosArticulos {
    List<String> datos;
    int [] paginas;

    public DatosArticulos() {
    }

    public List<String> getDatos() {
        return datos;
    }

    public void setDatos(List<String> datos) {
        this.datos = datos;
    }

    public int[] getPaginas() {
        return paginas;
    }

    public void setPaginas(int[] paginas) {
        this.paginas = paginas;
    }
}
