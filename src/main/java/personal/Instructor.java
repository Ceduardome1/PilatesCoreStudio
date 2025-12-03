package personal;

import dominio.Operaciones;
import dominio.Sucursal;

public class Instructor extends Personal {
	
	private static final Permiso[] permisos = {
		new Permiso( Operaciones.Clases, false ),
	};
	
	public Instructor(String idPersonal, String nombre, String apellidoPat, 
	String apellidoMat, Sucursal suc, Permiso[] permisos) {
		super( idPersonal, nombre, apellidoPat, apellidoMat, TipoPersonal.Instructor , suc, permisos );
	}
	
	public Instructor(String idPersonal, String nombre, String apellidoPat, 
	String apellidoMat, Sucursal suc ) {
		this( idPersonal, nombre, apellidoPat, apellidoMat, suc, permisos );
	}
	
}