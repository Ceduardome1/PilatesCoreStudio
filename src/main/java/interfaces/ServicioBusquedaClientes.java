package interfaces;

import java.util.List;

import actores.Cliente;

public interface ServicioBusquedaClientes {

	public abstract List< Cliente > buscarCliente( Cliente filtro ) throws Exception;
	public abstract Cliente seleccionarCliente( Integer idCliente ) throws Exception;
	
}