package casosUso;

import java.util.List;

import servicios.*;
import actores.Cliente;
import actores.Instructor;
import actores.Recepcionista;
import dominio.Clase;
import dominio.Reservacion;
import interfaces.ServicioBusquedaClases;
import interfaces.ServicioBusquedaClientes;
import interfaces.ServicioBusquedaInstructores;

public class ControladorReservacion implements ServicioBusquedaClientes, ServicioBusquedaInstructores, ServicioBusquedaClases {

	private final Recepcionista emisor;
private final OrquestadorClases servicioClases;
	private final OrquestadorReservaciones servicioReservaciones;
	
	
	public ControladorReservacion( Recepcionista emisor,
	OrquestadorReservaciones servicioReservaciones, OrquestadorClases servicioClases ) {
		this.emisor = emisor;
		this.servicioClases = servicioClases;
		this.servicioReservaciones = servicioReservaciones;
	}

	public void iniciarReservación() {}

	public List< Cliente > buscarCliente( Cliente filtro ) throws Exception {
		return servicioReservaciones.filtrarClientes( filtro );
	}

	public Cliente seleccionarCliente( Integer idCliente )throws Exception {
		return servicioReservaciones.buscarCliente( idCliente );
	}

	public List< Instructor> buscarInstructor( Instructor filtro ) throws Exception {
		return servicioClases.filtrarInstructores( filtro );
	}

	public Instructor seleccionarInstructor( Integer idInstructor ) throws Exception {
		return servicioClases.buscarInstructor( idInstructor );
	}
	
	public List< Clase > buscarClase( Clase filtro ) throws Exception {
		return servicioClases.filtrarClases( filtro, false );
	}

	public Clase seleccionarClase( Integer idClase ) throws Exception {
		return servicioClases.buscarClase( idClase );
	}

	public Reservacion reservarClase( Cliente cliente, Clase clase ) throws Exception {
		return servicioReservaciones.reservarClase( cliente, clase, emisor );
	}
	
}