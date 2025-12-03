package clases;

import dominio.Horario;
import personal.Administrador;
import personal.Instructor;

public class Clase {
	private final Sala sala;
	private final Instructor instructor;
	private final Horario horario;
	private final Administrador emisor;
	
	public Clase( Sala sala, Instructor instructor, Horario horario, Administrador emisor ) {
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
}
