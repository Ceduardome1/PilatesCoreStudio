package dominio;

public class Sucursal {

	private final Integer idSucursal;
	private final Direccion direccion;
	
	public Sucursal(int idSucursal, Direccion direccion) {
		this.idSucursal = idSucursal;
		this.direccion = direccion;
	}

	public int getIdSucursal() {
		return idSucursal;
	}

	public Direccion getDireccion() {
		return direccion;
	}
	
	public boolean corresponde( Sucursal otra ) {
		return otra!=null && otra.idSucursal == idSucursal;
	}
}