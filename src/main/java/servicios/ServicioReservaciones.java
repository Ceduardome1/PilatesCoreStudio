package servicios;

import actores.Cliente;
import actores.Recepcionista;
import bd.BD;
import bd.RepositorioReservaciones;
import dominio.Clase;
import dominio.Reservacion;
import dominio.Sala;

public class ServicioReservaciones {

	private final BD conexion;
	private final RepositorioReservaciones repo;
	private final ServicioClientes servicioClientes;
	private final ServicioSalas servicioSalas;
	
    public ServicioReservaciones(BD conexion, RepositorioReservaciones repo, ServicioClientes servicioClientes, ServicioSalas servicioSalas) {
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
				
				Integer idReservacion = generarIdReservacion();
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
	
	public void registrarReservacion( Reservacion reservacion) throws Exception {
		repo.insertar( reservacion, conexion );
	}

	public Integer generarIdReservacion() {
		// TODO Auto-generated method stub
		return null;
	}
	
}