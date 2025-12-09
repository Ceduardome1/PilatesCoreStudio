package dominio;

import java.time.LocalDate;
import java.time.LocalTime;

public class Horario {

	private final long segundosFecha;
	private final long segundosHora;
	
	private transient LocalDate fecha;
	private transient LocalTime hora;

	public Horario(LocalDate fecha, LocalTime hora) {
		this.fecha = fecha;
		this.hora = hora;
		segundosFecha = fecha.toEpochDay();
		segundosHora = hora.toSecondOfDay();
	}

	public LocalDate getFecha() {
			if (fecha == null) 
	            fecha = LocalDate.ofEpochDay(segundosFecha);
		return fecha;
	}

	public LocalTime getHora() {
			if (hora == null) 
	            hora = LocalTime.ofSecondOfDay(segundosHora);
		return hora;
	}

	public int comparar(Horario otro) {

	    long esteTotal = this.segundosFecha * 86400 + this.segundosHora;
	    long otroTotal = otro.segundosFecha * 86400 + otro.segundosHora;

	    return Long.compare(esteTotal, otroTotal);
	}
	
	public boolean corresponde(Horario otro) {
	    return segundosFecha == otro.segundosFecha
	    	&& segundosHora == otro.segundosHora;
	}
	
	public String mostrarFecha() {
		return getFecha().toString();
	}
	
	public String mostrarHora() {
		return getHora().toString();
	}
	
}
