package dominio;

import actores.Cliente;
import actores.Recepcionista;

public class Reservacion {

	private final Integer idReservacion;
	private final Clase clase;
	private final Cliente cliente;
	private final Recepcionista emisor;
	
	public Reservacion( Integer idReservacion, Clase clase, Cliente cliente, Recepcionista emisor) {
		this.idReservacion = idReservacion;
		this.clase = clase;
		this.cliente = cliente;
		this.emisor = emisor;
	}

	public Clase getClase() {
		return clase;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public Recepcionista getEmisor() {
		return emisor;
	}

	public Integer getIdReservacion() {
		return idReservacion;
	}	
	
}