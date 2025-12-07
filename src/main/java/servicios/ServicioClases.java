package servicios;

import java.util.List;
import bd.BD;
import bd.RepositorioClases;
import dominio.Clase;

public class ServicioClases {

	private final BD bd;
	private final RepositorioClases repo;

	public ServicioClases( BD bd, RepositorioClases repo ) {
		this.repo = repo;
		this.bd = bd;
	}
	
    public void registrarClase( Clase clase ) throws Exception {
	    	try {
	            repo.insertar( clase, bd );
	        } catch (Exception e) {
	        	bd.deshacerTransaccion();
	            throw e;
	        }
    	bd.confirmarTransaccion();
    }

    public List<Clase> filtrarClases( Clase filtro ) throws Exception {
        return repo.buscar( filtro, bd );
    }

    public Clase buscarClase(Integer id) throws Exception {
        return repo.buscar( id, bd );
    }

    public Integer generarIdClase() throws Exception {
    	
        List<Clase> clases = repo.buscarTodo( bd );

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