package casosUso;

import java.util.List;

import actores.Administrador;
import actores.Instructor;
import dominio.Clase;
import dominio.Horario;
import dominio.Sala;
import interfaces.ServicioBusquedaInstructores;
import servicios.ServicioClases;
import servicios.ServicioInstructores;
import servicios.ServicioSalas;

public class ControladorClase implements ServicioBusquedaInstructores{

	private final Administrador emisor;
	private final ServicioClases servicioClases;
	private final ServicioSalas servicioSalas;
	private final ServicioInstructores servicioInstructores;

	public ControladorClase( Administrador emisor, ServicioClases servicioClases, ServicioSalas servicioSalas,
	ServicioInstructores servicioInstructores) {
		this.emisor = emisor;
		this.servicioClases = servicioClases;
		this.servicioSalas = servicioSalas;
		this.servicioInstructores = servicioInstructores;
	}

	public void crearClase() {
		
	}

	public List< Instructor > buscarInstructor( Instructor filtro ) throws Exception {
		return servicioInstructores.filtrarInstructores( filtro );
	}

	public  Instructor seleccionarInstructor( Integer idInstructor ) throws Exception {
		return servicioInstructores.buscarInstructor( idInstructor );
	}

	public void registrarClase( Instructor instructor, Horario horario ) throws Exception {
		
		Sala sala = servicioSalas.asignarSala( horario );
		
		Integer id = servicioClases.generarIdClase();
		
		Clase clase = new Clase( id, sala, instructor, horario, emisor );
		
		servicioClases.registrarClase( clase );
		
	}

}