package dominio;

import actores.Administrador;
import actores.Instructor;

public class Clase {
	
	private final Integer idClase;
	private final Sala sala;
	private final Instructor instructor;
	private final Horario horario;
	private final Administrador emisor;
	
	public Clase( Integer idClase, Sala sala, Instructor instructor, Horario horario, Administrador emisor ) {
		this.idClase = idClase;
		this.sala = sala;
		this.instructor = instructor;
		this.horario = horario;
		this.emisor = emisor;
	}
	public Sala getSala() {
		return sala;
	}
	public Instructor getInstructor() {
		return instructor;
	}
	public Horario getHorario() {
		return horario;
	}
	public Administrador getEmisor() {
		return emisor;
	}
	public Integer getIdClase() {
		return idClase;
	}
	public boolean equals( Clase otra ) {
		return otra.idClase == idClase;
	}
	
}
