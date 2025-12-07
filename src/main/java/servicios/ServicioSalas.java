package servicios;

import java.util.List;

import bd.BD;
import bd.RepositorioSalas;
import dominio.Clase;
import dominio.Sala;

public class ServicioSalas {

	private final BD conexion;
	private final RepositorioSalas repo;
	
    public ServicioSalas(BD conexion, RepositorioSalas repo) {
		this.conexion = conexion;
		this.repo = repo;
	}
    
	public Sala asignarSala( List<Clase> clasesEnHorario ) throws Exception {
		List<Sala> salasEnHorario = null;
		
		return repo.buscarDiferente( salasEnHorario, conexion ); 
	}
	
	public Integer asignarCama( Sala sala ) throws Exception {

		return null;
	}
	
}