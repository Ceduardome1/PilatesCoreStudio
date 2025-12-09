package bd;

import java.util.List;

import com.db4o.ObjectContainer;
import com.db4o.query.Predicate;

import actores.Instructor;
import dominio.Clase;
import dominio.Horario;

public class RepositorioClases {

	public void insertar( Clase clase, BD bd ) throws Exception {
		ObjectContainer db = bd.getConexion();
        db.store(clase);
	}
	
	public Clase buscar( Integer idClase, BD bd ) throws Exception {
		 
		ObjectContainer conexion = bd.getConexion();
			 List<Clase> lista = conexion.query(new Predicate<Clase>() {
	             public boolean match(Clase c) {
	                 return c.getIdClase().equals(idClase);
	             }
	         });
			 
         return lista.isEmpty() ? null : lista.get(0);
	}
	
	public List< Clase > buscar( Clase filtro, boolean horaExacta, BD bd ) throws Exception {
		
			if( filtro == null ) return null;
		
		Horario horario = filtro.getHorario();
		Instructor instructor = filtro.getInstructor();
		
		ObjectContainer conexion = bd.getConexion();

			return conexion.query(new Predicate<Clase>() {
                @Override
                public boolean match(Clase c) {

                    if ( horario == null ) return false;
                    
                    if( horaExacta &&
                    ! c.getHorario().corresponde( horario ) ) return false;
                    else if( c.getHorario().comparar( horario ) < 0 ) return false;
                    
                    if ( instructor != null && !c.getInstructor().corresponde( instructor ) )
                        return false;

                    return true;
                }
            });

	}

	public List<Clase> buscarTodo( BD bd ) {
		return bd.getConexion().query(Clase.class);
	}

	public Integer generarId( BD bd) throws Exception {
        List<Clase> clases = buscarTodo( bd );

	        if (clases.isEmpty()) {
	            return 1;
	        }

        int max = 0;
	        for ( Clase c : clases ) {
	            if ( c.getIdClase() > max ) {
	                max = c.getIdClase();
	            }
	        }
        return max + 1;
    }
}