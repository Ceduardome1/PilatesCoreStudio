package consultas;

import actores.Instructor;
import bd.BD;

import java.util.List;

public class consultaInstructores {

    public static void main(String[] args) {

        BD bd = new BD();
        bd.crearConexion();
        
        List<Instructor> lista = bd.getConexion().query(Instructor.class);

        System.out.println("======= INSTRUCTORES ==========");
        System.out.printf("%-5s %-12s %-12s %-12s %-10s%n",
                "ID", "Nombre", "ApPat", "ApMat", "Sucursal");

        for (Instructor i : lista) {

            System.out.printf("%-5d %-12s %-12s %-12s %-10d%n",
                    i.getIdPersonal(),
                    i.getNombre(),
                    i.getApellidoPat(),
                    i.getApellidoMat(),
                    i.getSucursal().getIdSucursal()
            );
        }

        bd.cerrarConexion();
    }
}
