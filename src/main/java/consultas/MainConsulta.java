package consultas;
import inserciones.*;
public class MainConsulta {

    public static void main(String[] args) {

        System.out.println("\n======================================");
        System.out.println("   INSERTANDO DATOS DE PRUEBA EN DB4O");
        System.out.println("======================================");

        // 1. Insertar clientes
        System.out.println("\nInsertando Clientes...");
        insercionesClientes.main(null);

        // 2. Insertar instructores
        System.out.println("\nInsertando Instructores...");
        insercionesInstructores.main(null);

        // 3. Insertar clases
        System.out.println("\nInsertando Clases...");
        insercionesClases.main(null);

        System.out.println("\n======================================");
        System.out.println("   CONSULTANDO DATOS ALMACENADOS");
        System.out.println("======================================");

        // 4. Consultar clientes
        System.out.println("\nTabla de Clientes:");
        consultaClientes.main(null);

        // 5. Consultar instructores
        System.out.println("\nTabla de Instructores:");
        consultaInstructores.main(null);

        // 6. Consultar clases
        System.out.println("\nTabla de Clases:");
        consultaClases.main(null);

        System.out.println("\n======================================");
        System.out.println("   FIN DE EJECUCION");
        System.out.println("======================================\n");
    }
}
