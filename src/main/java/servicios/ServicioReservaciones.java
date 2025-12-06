package servicios;

import bd.BD;
import bd.RepositorioReservaciones;
import dominio.Reservacion;

public class ServicioReservaciones {

	private final BD conexion;
	private final RepositorioReservaciones repo;

    public ServicioReservaciones(BD conexion, RepositorioReservaciones repo) {
		this.conexion = conexion;
		this.repo = repo;
	}
	
	public void registrarReservacion( Reservacion reservacion) throws Exception {
		
	}
	
}
