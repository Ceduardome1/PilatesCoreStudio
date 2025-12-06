package dominio;

import java.time.LocalDate;

public class SaldoClases {

	private int clasesDisponibles;

	private long segundosVigencia;
	private transient LocalDate vigencia;

	public SaldoClases( int clasesDisponibles, LocalDate vigencia) {
		this.clasesDisponibles = clasesDisponibles;
		this.vigencia = vigencia;
		segundosVigencia = vigencia.toEpochDay();
	}

	public int getClasesDisponibles() {
		return clasesDisponibles;
	}

	public LocalDate getVigencia() {
			if (vigencia == null) 
				vigencia = LocalDate.ofEpochDay( segundosVigencia );
		return vigencia;
	}

	public String mostarVigencia() {
		return getVigencia().toString();
	}
	
	public void setClasesDisponibles(int clasesDisponibles) {
		this.clasesDisponibles = clasesDisponibles;
	}
	
}
