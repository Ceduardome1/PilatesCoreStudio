package main;
import consultas.ConsultaClases;
import consultas.ConsultaClientes;
import consultas.ConsultaInstructores;
import inserciones.*;
public class MainGenerar {

    public static void main(String[] args) {

        System.out.println("\n======================================");
        System.out.println("   INSERTANDO DATOS DE PRUEBA EN DB4O");
        System.out.println("======================================");

        System.out.println("\nGenrando Clases...");
        InsercionesAleatoriasClases.main(null);
        
        System.out.println("\nGenrando Reservaciones...");
        InsercionesAleatoriasReservaciones.main(null);
        
        System.out.println("\n======================================");
        System.out.println("   CONSULTANDO DATOS ALMACENADOS");
        System.out.println("======================================");

        System.out.println("\nTabla de Clientes:");
        ConsultaClientes.main(null);

        System.out.println("\nTabla de Instructores:");
        ConsultaInstructores.main(null);

        System.out.println("\nTabla de Clases:");
        ConsultaClases.main(null);
        
        System.out.println("\n======================================");
        System.out.println("   FIN DE EJECUCION");
        System.out.println("======================================\n");
    }
}
