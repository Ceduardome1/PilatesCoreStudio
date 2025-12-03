package dominio;

public class Sucursal {

	private final int idSucursal;
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
	
}