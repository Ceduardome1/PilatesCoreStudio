package servicios;

import bd.BD;
import bd.RepositorioInstructores;
import bd.RepositorioSalas;
import dominio.Horario;
import dominio.Sala;

public class ServicioSalas {

	private final BD conexion;
	private final RepositorioSalas repo;

    public ServicioSalas(BD conexion, RepositorioSalas repo) {
		this.conexion = conexion;
		this.repo = repo;
	}
    
	public Sala asignarSala( Horario horario ) throws Exception {

		return null;
	}
	
	public Integer asignarCama( Sala sala ) throws Exception {

		return null;
	}
	
}
