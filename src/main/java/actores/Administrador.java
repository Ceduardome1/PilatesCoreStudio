package actores;

import dominio.Sucursal;

public class Administrador extends Personal {
	
	public Administrador(Integer idPersonal, String nombre, String apellidoPat, 
	String apellidoMat, Sucursal suc ) {
		super(idPersonal, nombre, apellidoPat, apellidoMat, suc);
	}
			
}