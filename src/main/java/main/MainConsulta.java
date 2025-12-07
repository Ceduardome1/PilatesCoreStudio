package main;
import consultas.ConsultaClases;
import consultas.ConsultaClientes;
import consultas.ConsultaInstructores;
import inserciones.*;
public class MainConsulta {

    public static void main(String[] args) {

        System.out.println("\n======================================");
        System.out.println("   INSERTANDO DATOS DE PRUEBA EN DB4O");
        System.out.println("======================================");

        // 1. Insertar clientes
        System.out.println("\nInsertando Clientes...");
        InsercionesIndividualesClientes.main(null);

        // 2. Insertar instructores
        System.out.println("\nInsertando Instructores...");
        InsercionesIndividualesInstructores.main(null);

        // 3. Insertar clases
        System.out.println("\nInsertando Clases...");
        insercionesIndividualesClases.main(null);

        System.out.println("\n======================================");
        System.out.println("   CONSULTANDO DATOS ALMACENADOS");
        System.out.println("======================================");

        // 4. Consultar clientes
        System.out.println("\nTabla de Clientes:");
        ConsultaClientes.main(null);

        // 5. Consultar instructores
        System.out.println("\nTabla de Instructores:");
        ConsultaInstructores.main(null);

        // 6. Consultar clases
        System.out.println("\nTabla de Clases:");
        ConsultaClases.main(null);

        System.out.println("\n======================================");
        System.out.println("   FIN DE EJECUCION");
        System.out.println("======================================\n");
    }
}
