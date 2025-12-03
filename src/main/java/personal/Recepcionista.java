package personal;

import dominio.Operaciones;
import dominio.Sucursal;

public class Recepcionista extends Personal {

	private static final Permiso[] permisos = {
		new Permiso( Operaciones.Clientes, true ),
		new Permiso( Operaciones.Reservaciones, true ),
		new Permiso( Operaciones.Clases, false )
	};
	
	public Recepcionista(String idPersonal, String nombre, String apellidoPat, 
	String apellidoMat, Sucursal suc, Permiso[] permisos ) {
		super( idPersonal, nombre, apellidoPat, apellidoMat, TipoPersonal.Recepcionista, suc, permisos );
	}

	public Recepcionista(String idPersonal, String nombre, String apellidoPat, 
	String apellidoMat, Sucursal suc ) {
		this( idPersonal, nombre, apellidoPat, apellidoMat, suc, permisos );
	}
	
}