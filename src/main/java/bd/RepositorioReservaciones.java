package bd;

import java.util.List;

import com.db4o.ObjectContainer;

import dominio.Reservacion;

public class RepositorioReservaciones {
	
	public void insertar( Reservacion reservacion, BD bd ) throws Exception {
		ObjectContainer db = bd.getConexion();
        db.store(reservacion);
	}
	
	public List<Reservacion> buscarTodo( BD bd ) {
		return bd.getConexion().query(Reservacion.class);
	}
	
	public Integer generarIdReservacion( BD bd) {
		List<Reservacion> rvs = buscarTodo( bd );
	
			if (rvs.isEmpty()) {
				return 1;
			}

		int max = 0;
			for ( Reservacion r : rvs ) {
				if ( r.getIdReservacion() > max ) {
					max = r.getIdReservacion();
				}
			}
		return max + 1;
	}
	
}