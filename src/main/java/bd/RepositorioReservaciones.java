package bd;

import java.util.List;

import com.db4o.ObjectContainer;
import com.db4o.query.Predicate;

import actores.Cliente;
import dominio.Clase;
import dominio.Reservacion;

public class RepositorioReservaciones {
	
	public List<Reservacion> buscar(Reservacion filtro, BD bd) throws Exception {

	    	if (filtro == null) return null;
	    Cliente cliente = filtro.getCliente();
	    Clase clase = filtro.getClase();
	    ObjectContainer conexion = bd.getConexion();

	    return conexion.query(new Predicate<Reservacion>() {
	        @Override
	        public boolean match(Reservacion r) {
	        	
	        	if (cliente != null && !r.getCliente().corresponde(cliente))
	                return false;
	            if (clase != null && !r.getClase().getHorario().corresponde(clase.getHorario()))
	                return false;

	            return true;
	        }
	    });
	}
	
	public void insertar( Reservacion reservacion, BD bd ) throws Exception {
		ObjectContainer db = bd.getConexion();
        db.store(reservacion);
	}
	
	public List<Reservacion> buscarTodo( BD bd ) {
		return bd.getConexion().query(Reservacion.class);
	}
	
	public Integer generarId( BD bd) {
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