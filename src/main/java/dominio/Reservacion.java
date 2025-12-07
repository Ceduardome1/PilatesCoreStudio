package dominio;

import actores.Cliente;
import actores.Recepcionista;

public class Reservacion {

	private final Integer idReservacion;
	private final Integer idCamaAsignada;
	private final Recepcionista emisor;
	private final Clase clase;
	private final Cliente cliente;
	
	public Reservacion( Integer idReservacion, 
	Integer idCamaAsignada, Clase clase, Cliente cliente, 
	Recepcionista emisor) {
		this.idReservacion = idReservacion;
		this.idCamaAsignada = idCamaAsignada;
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

	public Integer getIdCamaAsignada() {
		return idCamaAsignada;
	}	
	
	public boolean corresponde( Reservacion otra ) {
		return otra.getIdReservacion() == idReservacion;
	}
	
}