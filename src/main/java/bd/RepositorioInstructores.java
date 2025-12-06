package bd;

import java.util.List;

import com.db4o.ObjectContainer;
import com.db4o.query.Predicate;

import actores.Instructor;

public class RepositorioInstructores {

	public Instructor buscar( Integer idInstructor, BD bd ) throws Exception {
		 
		ObjectContainer conexion = bd.getConexion();

	            List<Instructor> lista = conexion.query(new Predicate<Instructor>() {
	                @Override
	                public boolean match(Instructor i) {
	                    return i.getIdPersonal().equals(idInstructor);
	                }
	            });

	     return lista.isEmpty() ? null : lista.get(0);
	            
	}
	
	public List< Instructor > buscar( Instructor instructor, BD bd ) throws Exception {
		
		return null;
	}
	
}