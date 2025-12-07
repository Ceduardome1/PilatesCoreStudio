package interfaces;

import java.util.List;

import dominio.Clase;

public interface ServicioBusquedaClases {

	public abstract List< Clase > buscarClase( Clase filtro ) throws Exception;
	public abstract Clase seleccionarClase( Integer idClase ) throws Exception;
	
}
