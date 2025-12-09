package dominio;

public class Sala {

	private final Integer idSala;
	private final Sucursal sucursal;
	private final int camasTotales;
	private Integer idUltimaCamaAsignada;
	
	public Sala(Integer idSala, Sucursal sucursal, int camasTotales ) {
		this.idSala = idSala;
		this.sucursal = sucursal;
		this.camasTotales = camasTotales;
		this.idUltimaCamaAsignada = 0;
	}

	public Sala( Sala sala ) {
		this( sala.getIdSala(), sala.getSucursal(), sala.getCamasTotales() );
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

	public Integer asignarCama() {
			if( !estaDisponible() )
				return null;
		Integer cama = idUltimaCamaAsignada;
		idUltimaCamaAsignada++;
		return cama;
	}
	
	public boolean estaDisponible() {
		return idUltimaCamaAsignada < camasTotales;
	}
	
	public int getCamasDisponibles() {
		return camasTotales - idUltimaCamaAsignada;
	}
	
	public boolean corresponde( Sala otra ) {
		return otra.idSala == idSala;
	}
}