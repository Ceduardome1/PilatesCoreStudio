package dominio;

import java.time.LocalDate;
import java.time.LocalTime;

public class Horario {

	private final LocalDate fecha;
	private final LocalTime hora;
	
	public Horario(LocalDate fecha, LocalTime hora) {
		this.fecha = fecha;
		this.hora = hora;
	}

	
	public LocalDate getFecha() {
		return fecha;
	}

	public LocalTime getHora() {
		return hora;
	}

	public String mostrarFecha() {
		return fecha.toString();
	}
	
	public String mostrarHora() {
		return hora.toString();
	}
}
