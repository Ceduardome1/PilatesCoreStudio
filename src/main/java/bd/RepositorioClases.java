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
	
	public List< Clase > buscar( Horario horario, Instructor instructor, BD bd ) throws Exception {
		
		ObjectContainer conexion = bd.getConexion();

			return conexion.query(new Predicate<Clase>() {
                @Override
                public boolean match(Clase c) {

                    if (horario != null && !c.getHorario().equals(horario))
                        return false;

                    if (instructor != null && !c.getInstructor().equals(instructor))
                        return false;

                    return true;
                }
            });

	}

	public List<Clase> consultarTodo( BD bd ) {
		return bd.getConexion().query(Clase.class);
	}

}