package actores;

import dominio.Sucursal;

public class Instructor extends Personal {
	
	public Instructor(Integer idPersonal, String nombre, String apellidoPat, 
	String apellidoMat, Sucursal suc ) {
		super(idPersonal, nombre, apellidoPat, apellidoMat, suc);
	}
	
}