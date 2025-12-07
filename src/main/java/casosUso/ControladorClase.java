package casosUso;

import java.util.List;

import actores.Administrador;
import actores.Instructor;
import dominio.Clase;
import dominio.Horario;
import dominio.Sucursal;
import interfaces.ServicioBusquedaInstructores;
import servicios.ServicioClases;
import servicios.ServicioInstructores;

public class ControladorClase implements ServicioBusquedaInstructores{

	private final Administrador emisor;
	private final Sucursal sucursal;
	private final ServicioClases servicioClases;
	private final ServicioInstructores servicioInstructores;

	public ControladorClase( Administrador emisor, Sucursal sucursal, 
	ServicioClases servicioClases, ServicioInstructores servicioInstructores) {
		this.emisor = emisor;
		this.sucursal = sucursal;
		this.servicioClases = servicioClases;
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

	public Clase registrarClase( Instructor instructor, Horario horario ) throws Exception {
		return servicioClases.registrarClase( horario, instructor, emisor, sucursal );
	}

}