package bd;

import java.util.LinkedList;
import java.util.List;

import com.db4o.ObjectContainer;
import com.db4o.query.Predicate;

import dominio.Sala;
import dominio.Sucursal;

public class RepositorioSalas {

	public Sala buscar( Integer idSala, BD bd ) throws Exception {
		
		ObjectContainer conexion = bd.getConexion();

		List<Sala> lista = conexion.query(new Predicate<Sala>() {
            public boolean match(Sala s) {
                return s.getIdSala().equals(idSala);
            }
        });
		
        return lista.isEmpty() ? null : lista.get(0);
	}
	

	public List< Sala > buscar( Sala filtro, BD bd ) throws Exception {
	    
			if( filtro == null ) return null;
		
        Integer idSala = filtro.getIdSala();
        Sucursal sucursal = filtro.getSucursal();

        ObjectContainer conexion = bd.getConexion();

        return conexion.query(new Predicate<Sala>() {
            @Override
            public boolean match( Sala s ) {
                
                if ( idSala != null && !s.getIdSala().equals(idSala)) {
                    return false;
                }
                
                if ( sucursal != null && !s.getSucursal().corresponde(sucursal) ) {
                    return false;
                }

                return true;
            }
        });
	}

	public List<Sala> buscarTodo( BD bd ) {
		return bd.getConexion().query(Sala.class);
	}

	public void actualizar( Sala sala, BD bd ) throws Exception {
		bd.getConexion().store(sala);
	}

	public Sala buscarDiferente(LinkedList<Sala> salasOcupadas, BD conexion) throws Exception {
		List<Sala> todasLasSalas = buscarTodo( conexion ); 
			for ( Sala salaAsignada : todasLasSalas) {
				Sala salaAct = buscar( salaAsignada.getIdSala(), conexion);
					if( salaAct == null ) 
						return salaAsignada;
			}
		return null;
	}
	
}