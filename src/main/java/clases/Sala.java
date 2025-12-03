package clases;

import dominio.Sucursal;

public class Sala {

	private final Integer idSala;
	private final Sucursal sucursal;
	private final int camasTotales;
	private final int camasDisponibles;
	
	public Sala(Integer idSala, Sucursal sucursal, int camasTotales, int camasDisponibles) {
		this.idSala = idSala;
		this.sucursal = sucursal;
		this.camasTotales = camasTotales;
		this.camasDisponibles = camasDisponibles;
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

	public int getCamasDisponibles() {
		return camasDisponibles;
	}
	
}
