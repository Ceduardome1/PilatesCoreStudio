package casosUso;

import java.util.List;

import actores.Administrador;
import actores.Instructor;
import dominio.Clase;
import dominio.Horario;
import dominio.Sucursal;
import interfaces.ServicioBusquedaInstructores;
import servicios.OrquestadorClases;

public class ControladorClase implements ServicioBusquedaInstructores{

	private final Administrador emisor;
	private final Sucursal sucursal;
	private final OrquestadorClases servicioClases;

	public ControladorClase( Administrador emisor, Sucursal sucursal, 
	OrquestadorClases servicioClases ) {
		this.emisor = emisor;
		this.sucursal = sucursal;
		this.servicioClases = servicioClases;
	}

	public void crearClase() {}

	public List< Instructor > buscarInstructor( Instructor filtro ) throws Exception {
		return servicioClases.filtrarInstructores( filtro );
	}

	public  Instructor seleccionarInstructor( Integer idInstructor ) throws Exception {
		return servicioClases.buscarInstructor( idInstructor );
	}

	public Clase registrarClase( Instructor instructor, Horario horario ) throws Exception {
		return servicioClases.registrarClase( horario, instructor, emisor, sucursal );
	}

}