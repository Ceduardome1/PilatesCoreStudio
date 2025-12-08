package servicios;

import java.util.List;

import actores.Cliente;
import actores.Recepcionista;
import bd.BD;
import bd.RepositorioReservaciones;
import dominio.Clase;
import dominio.Reservacion;
import dominio.Sala;

public class OrquestadorReservaciones {

	private final BD conexion;
	private final RepositorioReservaciones repo;
	private final ServicioSalas servicioSalas;
	private final ServicioClientes servicioClientes;

    public OrquestadorReservaciones(BD conexion, RepositorioReservaciones repo, ServicioClientes servicioClientes, ServicioSalas servicioSalas) {
		this.conexion = conexion;
		this.repo = repo;
		this.servicioClientes = servicioClientes;
		this.servicioSalas = servicioSalas;
	}
	
	public Reservacion reservarClase( Cliente cliente, Clase clase, Recepcionista emisor ) throws Exception {
		
			try { 
				
				Sala sala = clase.getSala();
				Integer idCamaAsignada = servicioSalas.asignarCama( sala );
				
				servicioClientes.descontarClase( cliente );
				
				Integer idReservacion = repo.generarIdReservacion( conexion );
				Reservacion rvAct = new Reservacion( idReservacion, idCamaAsignada, clase, cliente, emisor );
				
				registrarReservacion( rvAct );
				
				conexion.confirmarTransaccion();
				return rvAct;
				
			}
			catch( Exception e ) {
				conexion.deshacerTransaccion();
				throw e;
			}
		
	}
	
	private void registrarReservacion( Reservacion reservacion) throws Exception {
		repo.insertar( reservacion, conexion );
	}

	public List<Cliente> filtrarClientes( Cliente filtro ) throws Exception {
		return servicioClientes.filtrarClientes( filtro );
	}

	public Cliente buscarCliente(Integer idCliente) throws Exception {
		return servicioClientes.buscarCliente( idCliente );
	}
	
}