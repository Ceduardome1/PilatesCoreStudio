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

    public List< Instructor> buscar( Instructor instructor, BD bd ) throws Exception {

        Integer idPersonal = instructor.getIdPersonal();
        String nombre = instructor.getNombre();
        String apellidoPat = instructor.getApellidoPat();
        String apellidoMat = instructor.getApellidoMat();
        Sucursal sucursal = instructor.getSucursal();

        ObjectContainer conexion = bd.getConexion();

        return conexion.query(new Predicate<Instructor>() {
            public boolean match(Instructor c) {

                if ( idPersonal != null && !c.getIdPersonal().equals(idPersonal)) {
                    return false;
                }
                if ( nombre != null && !c.getNombre().equalsIgnoreCase(nombre)) {
                    return false;
                }
                if ( apellidoPat != null && !c.getApellidoPat().equalsIgnoreCase(apellidoPat)) {
                    return false;
                }
                if ( apellidoMat != null && !c.getApellidoMat().equalsIgnoreCase(apellidoMat)) {
                    return false;
                }
                if ( sucursal != null && !c.getSucursal().equals( sucursal ) ) {
                    return false;
                }

                return true;
            }
        });

    }

    public void actualizar(Instructor instructor, BD bd) throws Exception {
        bd.getConexion().store(instructor);
    }

}
