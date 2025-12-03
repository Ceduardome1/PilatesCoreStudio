package personal;

public enum TipoPersonal {
	Recepcionista("Recepcionista"), Administrador("Administrador"), Instructor("Instructor");

	private String descripcion;
	private TipoPersonal( String descripcion ) {
		this.descripcion = descripcion;
	}
	
	public String toString(){
		return descripcion;
	}

}