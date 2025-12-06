package consultas;

import actores.Cliente;
import bd.BD;

import java.util.List;

public class consultaClientes {

    public static void main(String[] args) {

        BD bd = new BD();
        bd.crearConexion();
        
        List<Cliente> lista = bd.getConexion().query(Cliente.class);

        System.out.println("=========== CLIENTES ===========");
        System.out.printf("%-5s %-10s %-10s %-10s %-15s %-10s %-12s%n",
                "ID", "Nombre", "ApPat", "ApMat", "Correo", "Clases", "Vigencia");

        for (Cliente c : lista) {
            System.out.printf("%-5d %-10s %-10s %-10s %-15s %-10d %-12s%n",
                    c.getIdCliente(),
                    c.getNombre(),
                    c.getApellidoPaterno(),
                    c.getApellidoMaterno(),
                    c.getCorreo(),
                    c.getSaldoClases().getClasesDisponibles(),
                    c.getSaldoClases().getVigencia()
            );
        }

        bd.cerrarConexion();
    }
}
