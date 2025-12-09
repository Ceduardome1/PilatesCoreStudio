package bd;

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

	public void insertar( Sala sala, BD bd ) throws Exception {
		bd.getConexion().store(sala);
	}
	
	public void actualizar( Sala sala, BD bd ) throws Exception {
		bd.getConexion().store(sala);
	}

	public Sala buscarDiferente(List<Sala> salasOcupadas, BD conexion) throws Exception {
	    List<Sala> todasLasSalas = buscarTodo(conexion);
		    if(todasLasSalas.isEmpty())
		        throw new Exception("No hay salas registradas.");

		    for(Sala sala : todasLasSalas) {
		        boolean ocupada = false;
		        for(Sala s : salasOcupadas) 
		            if(s.getIdSala().equals(sala.getIdSala())) {
		                ocupada = true;
		                break;
		            }
		        if(!ocupada) return sala;
		    }

	    return null;
	}
	
}