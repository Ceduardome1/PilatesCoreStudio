package servicios;

import java.util.List;

import actores.Cliente;
import bd.BD;
import bd.RepositorioClientes;

public class ServicioClientes {

	private final BD bd;
	private final RepositorioClientes repo;

	public ServicioClientes( BD bd, RepositorioClientes repo ) {
		this.repo = repo;
		this.bd = bd;
	}

	public Cliente buscarCliente( Integer idCliente ) throws Exception {
		return repo.buscar( idCliente, bd );
    }

    public List<Cliente> filtrarClientes( Cliente filtro ) throws Exception {
    	return repo.buscar( filtro, bd );
    }
    
    public void descontarClase( Cliente cliente ) throws Exception {
	        
    	cliente.descontarClase();
    	
    		if( !cliente.esSaldoValido() )
    			throw new Exception( "El saldo de clases del cliente ya no es válido." );
 
    		try {
	        	repo.actualizar( cliente, bd );
	        } catch (Exception e) {
	        	bd.deshacerTransaccion();
	            throw e;
	        }
    		
        bd.confirmarTransaccion();
    }

}