package actores;
import dominio.Sucursal;

public abstract class Personal {
	
	private final Integer idPersonal;
	private final String nombre, apellidoPat, apellidoMat;
	private final Sucursal sucursal;

	public Personal(Integer idPersonal, String nombre, String apellidoPat, 
	String apellidoMat, Sucursal sucursal ) {
		this.idPersonal = idPersonal;
		this.nombre = nombre;
		this.apellidoMat = apellidoMat;
		this.apellidoPat = apellidoPat;
		this.sucursal = sucursal;
	}

	public Integer getIdPersonal() {
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
	
	public String toString() {
		return String.format( "%s %s % s", nombre, apellidoPat, apellidoMat );
	}
	
	public boolean equals( Personal otro ) {
		return otro.idPersonal == idPersonal;
	}
	
}