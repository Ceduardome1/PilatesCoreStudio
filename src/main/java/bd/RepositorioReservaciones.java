package bd;

import java.util.List;

import dominio.Reservacion;

public class RepositorioReservaciones {
	
	public void insertar( Reservacion reservacion, BD bd ) throws Exception {
		
	}
	
	public List<Reservacion> buscarTodo( BD bd ) {
		return bd.getConexion().query(Reservacion.class);
	}
	
}