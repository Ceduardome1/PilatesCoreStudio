package dominio;

public class Sala {

	private final Integer idSala;
	private final Sucursal sucursal;
	private final int camasTotales;
	private final int ultimaCamaAsignada; // SI ES == camasTotales ya no hay camas disponibles
	
	public Sala(Integer idSala, Sucursal sucursal, int camasTotales ) {
		this.idSala = idSala;
		this.sucursal = sucursal;
		this.camasTotales = camasTotales;
		this.ultimaCamaAsignada = 0;
	}

	public Integer getIdSala() {
		return idSala;
	}

	public Sucursal getSucursal() {
		return sucursal;
	}

	public int getCamasTotales() {
		return camasTotales;
	}

	public int getUltimaCamaAsignada() {
		return ultimaCamaAsignada;
	}
	
}