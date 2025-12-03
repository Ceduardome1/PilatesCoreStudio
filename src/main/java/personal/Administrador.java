package personal;

import dominio.Operaciones;
import dominio.Sucursal;

public class Administrador extends Personal {

	private static final Permiso[] permisos = {
		new Permiso( Operaciones.Clases, true )
	};
	
	public Administrador(String idPersonal, String nombre, String apellidoPat, 
	String apellidoMat, Sucursal suc, Permiso[] permisos) {
		super(idPersonal, nombre, apellidoPat, apellidoMat, TipoPersonal.Administrador, suc, permisos );
	}

	public Administrador(String idPersonal, String nombre, String apellidoPat, 
		String apellidoMat, Sucursal suc ) {
		this( idPersonal, nombre, apellidoPat, apellidoMat, suc, permisos );
	}
			
}