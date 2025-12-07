package bd;

import java.util.List;

import com.db4o.ObjectContainer;
import com.db4o.query.Predicate;

import actores.Cliente;

public class RepositorioClientes {

    public Cliente buscar(Integer idCliente, BD bd) throws Exception {

        ObjectContainer conexion = bd.getConexion();

        List<Cliente> lista = conexion.query(new Predicate<Cliente>() {
            public boolean match(Cliente c) {
                return c.getIdCliente().equals(idCliente);
            }
        });

        return lista.isEmpty() ? null : lista.get(0);

    }

    public List< Cliente> buscar(Cliente filtro, BD bd) throws Exception {
    		if( filtro == null ) return null;
        Integer clienteId = filtro.getIdCliente();
        String nombre = filtro.getNombre();
        String ap1 = filtro.getApellidoPaterno();
        String ap2 = filtro.getApellidoMaterno();
        Long telefono = filtro.getTelefono();
        String correo = filtro.getCorreo();

        ObjectContainer conexion = bd.getConexion();

        return conexion.query(new Predicate<Cliente>() {
            public boolean match(Cliente c) {

                if (clienteId != null && !c.getIdCliente().equals(clienteId)) {
                    return false;
                }
                if (nombre != null && !c.getNombre().equalsIgnoreCase(nombre)) {
                    return false;
                }
                if (ap1 != null && !c.getApellidoPaterno().equalsIgnoreCase(ap1)) {
                    return false;
                }
                if (ap2 != null && !c.getApellidoMaterno().equalsIgnoreCase(ap2)) {
                    return false;
                }
                if (telefono != null && !c.getTelefono().equals(telefono)) {
                    return false;
                }
                if (correo != null && !c.getCorreo().equalsIgnoreCase(correo)) {
                    return false;
                }

                return true;
            }
        });

    }

    public void actualizar(Cliente cliente, BD bd) throws Exception {
        bd.getConexion().store(cliente);
    }

}
