package servicios;

import java.util.LinkedList;
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
		LinkedList< Sala > salasOcupadas = new LinkedList<Sala>();
			
				for ( Clase clase : clasesEnHorario ) 
					salasOcupadas.add( clase.getSala() );
				
		return repo.buscarDiferente( salasOcupadas, conexion );    
	}
	
	public Integer asignarCama( Sala sala ) throws Exception {
		sala = repo.buscar( sala.getIdSala(), conexion );
			if( sala == null ) return null;
		Integer camaAsignada = sala.asignarCama();
		repo.actualizar( sala, conexion );
		return camaAsignada;
	}
	
}