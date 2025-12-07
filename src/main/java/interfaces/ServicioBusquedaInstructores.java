package interfaces;

import java.util.List;

import actores.Instructor;

public interface ServicioBusquedaInstructores {

	public abstract List< Instructor> buscarInstructor( Instructor filtro ) throws Exception;
	
	public abstract Instructor seleccionarInstructor( Integer idInstructor ) throws Exception;
	
}
