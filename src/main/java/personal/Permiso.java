package personal;
import dominio.Operaciones;

public class Permiso {

	private final boolean escritura;
	private final Operaciones operacion;
	
	public Permiso( Operaciones operacion, boolean escritura) {
		this.escritura = escritura;
		this.operacion = operacion;
	}

	public boolean isEscritura() {
		return escritura;
	}

	public Operaciones getOperacion() {
		return operacion;
	}
	
	public String toString() {
		String salida = (escritura)? "Gestionar " : "Consultar ";
		return salida + operacion.toString();
	}
	
}