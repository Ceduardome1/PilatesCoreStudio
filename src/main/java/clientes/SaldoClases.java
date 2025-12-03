package clientes;

import java.time.LocalDate;

public class SaldoClases {

	private final int clasesDisponibles;
	private final LocalDate fecha;

	public SaldoClases(int clasesDisponibles, LocalDate fecha) {
		super();
		this.clasesDisponibles = clasesDisponibles;
		this.fecha = fecha;
	}

	public int getClasesDisponibles() {
		return clasesDisponibles;
	}

	public LocalDate getFecha() {
		return fecha;
	}

}
