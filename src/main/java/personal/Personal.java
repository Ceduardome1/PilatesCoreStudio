package personal;
import java.util.HashMap;

import dominio.Operaciones;
import dominio.Sucursal;

public abstract class Personal {
	
	private final String idPersonal, nombre, apellidoPat, apellidoMat;
	private final TipoPersonal tipoPesonal;
	private final Sucursal sucursal;
	private final Permiso[] permisos;
	private final HashMap< Operaciones, Permiso > mapaPermisos;
	
	public Personal(String idPersonal, String nombre, String apellidoPat, 
	String apellidoMat, TipoPersonal tipoPesonal, Sucursal sucursal, Permiso[] permisos ) {
		this.idPersonal = idPersonal;
		this.nombre = nombre;
		this.apellidoMat = apellidoMat;
		this.apellidoPat = apellidoPat;
		this.sucursal = sucursal;
		this.permisos = permisos;
		this.tipoPesonal = tipoPesonal;
		
		mapaPermisos = new HashMap< Operaciones, Permiso >();
			for ( Permiso permiso : permisos )
				mapaPermisos.put( permiso.getOperacion(), permiso );		
	}

	public String getIdPersonal() {
		return idPersonal;
	}

	public String getNombre() {
		return nombre;
	}

	public String getApellidoMat() {
		return apellidoMat;
	}

	public String getApellidoPat() {
		return apellidoPat;
	}

	public Sucursal getSucursal() {
		return sucursal;
	}

	public Permiso getPermiso( Operaciones operacion ) {
		return mapaPermisos.get( operacion );
	}

	public Permiso[] getPermisos() {
		return permisos;
	}

	public TipoPersonal getTipoPesonal() {
		return tipoPesonal;
	}
	
	public String toString() {
		return String.format( "%s %s % s", nombre, apellidoPat, apellidoMat );
	}
}