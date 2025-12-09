package bd;

import java.util.List;

import com.db4o.ObjectContainer;
import com.db4o.query.Predicate;

import actores.Instructor;
import dominio.Sucursal;

public class RepositorioInstructores {

    public Instructor buscar(Integer idInstructor, BD bd) throws Exception {

        ObjectContainer conexion = bd.getConexion();

        List<Instructor> lista = conexion.query(new Predicate<Instructor>() {
            @Override
            public boolean match(Instructor i) {
                return i.getIdPersonal().equals(idInstructor);
            }
        });

        return lista.isEmpty() ? null : lista.get(0);

    }

    public List< Instructor> buscar( Instructor filtro, BD bd ) throws Exception {

    		if( filtro == null ) return null;
        
    	Integer idPersonal = filtro.getIdPersonal();
        String nombre = filtro.getNombre();
        String apellidoPat = filtro.getApellidoPat();
        String apellidoMat = filtro.getApellidoMat();
        Sucursal sucursal = filtro.getSucursal();

        ObjectContainer conexion = bd.getConexion();

        return conexion.query( new Predicate<Instructor>() {
        	
            public boolean match(Instructor c) {

                if ( idPersonal != null && !c.getIdPersonal().equals(idPersonal)) {
                    return false;
                }
                if ( nombre != null && !c.getNombre().toUpperCase().startsWith(nombre.toUpperCase())) {
                    return false;
                }
                if ( apellidoPat != null && !c.getApellidoPat().toUpperCase().startsWith(apellidoPat.toUpperCase())) {
                    return false;
                }
                if ( apellidoMat != null && !c.getApellidoMat().toUpperCase().startsWith(apellidoMat.toUpperCase())) {
                    return false;
                }
                if ( sucursal != null && !c.getSucursal().corresponde( sucursal ) ) {
                    return false;
                }

                return true;
            }
        });

    }

}
