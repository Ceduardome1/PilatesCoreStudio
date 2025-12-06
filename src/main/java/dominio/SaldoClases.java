package dominio;

import java.time.LocalDate;

public class SaldoClases {

	private int clasesDisponibles;
	public void setClasesDisponibles(int clasesDisponibles) {
		this.clasesDisponibles = clasesDisponibles;
	}

	public void setVigencia(LocalDate vigencia) {
		this.vigencia = vigencia;
	}

	private LocalDate vigencia;

	public SaldoClases( int clasesDisponibles, LocalDate vigencia) {
		this.clasesDisponibles = clasesDisponibles;
		this.vigencia = vigencia;
	}

	public int getClasesDisponibles() {
		return clasesDisponibles;
	}

	public LocalDate getVigencia() {
		return vigencia;
	}

}
