package servicios;

import java.util.List;

import actores.Administrador;
import actores.Instructor;
import bd.BD;
import bd.RepositorioClases;
import dominio.Clase;
import dominio.Horario;
import dominio.Sala;
import dominio.Sucursal;

public class OrquestadorClases {

	private final BD bd;
	private final RepositorioClases repo;

	private final ServicioSalas servicioSalas;
	private final ServicioInstructores servicioInstructores;

	public OrquestadorClases( BD bd, RepositorioClases repo, ServicioSalas servicioSalas, ServicioInstructores servicioInstructores ) {
		this.servicioInstructores = servicioInstructores;
		this.repo = repo;
		this.bd = bd;
		this.servicioSalas = servicioSalas;
	}
	
	public Clase registrarClase( Horario horario,  Instructor instructor, 
	Administrador admin, Sucursal sucursal ) throws Exception {
	    	
		try {
			
			Clase filtro = new Clase( null, null, null, horario, null );
			
			List< Clase > clasesEnHorario = filtrarClases( filtro, true );
			Sala sala = servicioSalas.asignarSala( clasesEnHorario );
			
				if( sala == null ) 
					throw new Exception("Todas las salas estan ocupadas en el horario seleccionado.");
				
			Integer idClase = repo.generarId( bd );
			
			Clase clase = new Clase( idClase, sala, instructor, horario, admin );
			
			registrarClase( clase );
			
			bd.confirmarTransaccion();
			
			return clase;
		
		}
		catch( Exception e ) {
			bd.deshacerTransaccion();
			throw e;
		}
		
		
	}
    
    private void registrarClase( Clase clase ) throws Exception {
	    	try {
	            repo.insertar( clase, bd );
	        } catch (Exception e) {
	        	bd.deshacerTransaccion();
	            throw e;
	        }
    }

    public List<Clase> filtrarClases( Clase filtro, boolean horaExacta ) throws Exception {
        return repo.buscar( filtro, horaExacta, bd );
    }

    public Clase buscarClase(Integer id) throws Exception {
        return repo.buscar( id, bd );
    }

	public List<Instructor> filtrarInstructores( Instructor filtro ) throws Exception {
		return servicioInstructores.filtrarInstructores( filtro ); 
	}
	
	public Instructor buscarInstructor( Integer idInstructor ) throws Exception {
		return servicioInstructores.buscarInstructor( idInstructor );
	}
	
}