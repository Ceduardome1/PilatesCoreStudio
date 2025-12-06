package consultas;

import dominio.Clase;
import bd.BD;

import java.util.List;

public class consultaClases {

    public static void main(String[] args) {

        BD bd = new BD();
        bd.crearConexion();
        
        List<Clase> lista = bd.getConexion().query(Clase.class);

        System.out.println("=============== CLASES ===============");
        System.out.printf("%-5s %-6s %-12s %-12s %-6s %-10s%n",
                "ID", "Sala", "Instructor", "Fecha", "Hora", "Admin");

        for (Clase c : lista) {
            System.out.printf("%-5d %-6d %-12s %-12s %-6s %-10s%n",
                    c.getIdClase(),
                    c.getSala().getIdSala(),
                    c.getInstructor().getNombre(),
                    c.getHorario().getFecha(),
                    c.getHorario().getHora(),
                    c.getEmisor().getNombre()
            );
        }

        bd.cerrarConexion();
    }
}
