package controladoresCasosUso;

import java.util.List;

import servicios.*;
import actores.Cliente;
import actores.Instructor;
import actores.Recepcionista;
import dominio.Clase;
import dominio.Reservacion;
import dominio.Sala;
import interfaces.ServicioBusquedaClases;
import interfaces.ServicioBusquedaClientes;
import interfaces.ServicioBusquedaInstructores;

public class ControladorReservacion implements ServicioBusquedaClientes, ServicioBusquedaInstructores, ServicioBusquedaClases {

	private final ServicioClientes servicioClientes;
	private final ServicioInstructores servicioInstructores;
	private final ServicioClases servicioClases;
	private final ServicioSalas servicioSalas;
	private final ServicioReservaciones servicioReservaciones;
	private final Recepcionista emisor;
	
	
	public ControladorReservacion( Recepcionista emisor, ServicioClientes servicioClientes, ServicioInstructores servicioInstructores,
	ServicioClases servicioClases, ServicioSalas servicioSalas, ServicioReservaciones servicioReservaciones ) {
		this.emisor = emisor;
		this.servicioClientes = servicioClientes;
		this.servicioInstructores = servicioInstructores;
		this.servicioClases = servicioClases;
		this.servicioSalas = servicioSalas;
		this.servicioReservaciones = servicioReservaciones;
	}

	public void iniciarReservación() {
		
	}

	public List< Cliente > buscarCliente( Cliente filtro ) throws Exception {
		return servicioClientes.filtrarClientes( filtro );
	}

	public Cliente seleccionarCliente( Integer idCliente )throws Exception {
		return servicioClientes.buscarCliente( idCliente );
	}

	public List< Instructor> buscarInstructor( Instructor filtro ) throws Exception {
		return servicioInstructores.filtrarInstructores( filtro );
	}

	public Instructor seleccionarInstructor( Integer idInstructor ) throws Exception {
		return servicioInstructores.buscarInstructor( idInstructor );
	}
	
	public List< Clase > buscarClase( Clase filtro ) throws Exception {
		return servicioClases.filtrarClases( filtro );
	}

	public Clase seleccionarClase( Integer idClase ) throws Exception {
		return servicioClases.buscarClase( idClase );
	}

	public Reservacion reservarClase( Cliente cliente, Clase clase ) throws Exception {
		
		Sala sala = clase.getSala();
		Integer idCamaAsignada = servicioSalas.asignarCama( sala );
		
		servicioClientes.descontarClase( cliente );
		
		Integer idReservacion = servicioReservaciones.generarIdReservacion();
		Reservacion rvAct = new Reservacion( idReservacion, idCamaAsignada, clase, cliente, emisor );
		
		servicioReservaciones.registrarReservacion( rvAct );
		
		return rvAct;
	
	}
	
}