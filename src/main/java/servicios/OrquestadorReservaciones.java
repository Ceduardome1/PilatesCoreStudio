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
				
					if( yaTieneReservacion(cliente, clase) )
						throw new Exception("El cliente ya cuenta con una reservación en ese horario.");
				
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

	private boolean yaTieneReservacion( Cliente cliente, Clase clase ) throws Exception {
		Reservacion filtro = new Reservacion(null, null, clase, cliente, null);
		Reservacion reservacionHecha= repo.buscar( filtro, conexion );
			if( reservacionHecha == null )
				return false;
		return true;
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