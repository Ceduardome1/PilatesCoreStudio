package servicios;

import java.util.List;

import actores.Instructor;
import bd.BD;
import bd.RepositorioInstructores;

public class ServicioInstructores {
	
	private final BD conexion;
	private final RepositorioInstructores repo;

    public ServicioInstructores( BD conexion, RepositorioInstructores repo ) {
		this.conexion = conexion;
		this.repo = repo;
	}

	public Instructor buscarInstructor( Integer idInstructor) throws Exception {
       return repo.buscar( idInstructor, conexion );
    }

	public List<Instructor> filtrarInstructores( Instructor filtro ) throws Exception {
		return repo.buscar( filtro, conexion );
	}
    
    
}