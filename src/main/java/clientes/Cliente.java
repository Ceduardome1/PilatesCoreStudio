package clientes;

public class Cliente {
	
	private final Integer idCliente;
	private final Long telefono;
	private final String nombre, apellidoPaterno, apellidoMaterno, correo;
	private final SaldoClases saldoClases;
	
	public Cliente(Integer idCliente, Long telefono, String nombre, String apellidoPaterno, String apellidoMaterno,
			String correo, SaldoClases saldoClases) {
		super();
		this.idCliente = idCliente;
		this.telefono = telefono;
		this.nombre = nombre;
		this.apellidoPaterno = apellidoPaterno;
		this.apellidoMaterno = apellidoMaterno;
		this.correo = correo;
		this.saldoClases = saldoClases;
	}
	public Integer getIdCliente() {
		return idCliente;
	}
	public Long getTelefono() {
		return telefono;
	}
	public String getNombre() {
		return nombre;
	}
	public String getApellidoPaterno() {
		return apellidoPaterno;
	}
	public String getApellidoMaterno() {
		return apellidoMaterno;
	}
	public String getCorreo() {
		return correo;
	}
	public SaldoClases getSaldoClases() {
		return saldoClases;
	}

}
