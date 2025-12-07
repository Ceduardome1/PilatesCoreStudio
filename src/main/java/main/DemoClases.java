package main;

import actores.Administrador;
import bd.BD;
import bd.RepositorioClases;
import bd.RepositorioInstructores;
import bd.RepositorioSalas;
import casosUso.ControladorClase;
import controladoresGraficos.ControladorVistaClases;
import dominio.Direccion;
import dominio.Sucursal;
import servicios.ServicioClases;
import servicios.ServicioInstructores;
import servicios.ServicioSalas;
import vistas.VistaClases;

public class DemoClases {
	
	public static void main( String[] args ) {
		
		BD bd = new BD(); 
		bd.crearConexion();
		
		RepositorioInstructores repoInstructores = new RepositorioInstructores();
		ServicioInstructores servicioInstructores = new ServicioInstructores( bd,repoInstructores );
		
		RepositorioClases repoClases = new RepositorioClases();
		ServicioClases servicioClases = new ServicioClases( bd, repoClases );
		
		RepositorioSalas repoSalas = new RepositorioSalas();
		ServicioSalas servicioSalas = new ServicioSalas( bd, repoSalas );
		
		Direccion direccion = new Direccion( 80000, (short)231, "Cristobal Colón", "Centro" );
		Sucursal sucursal = new Sucursal( 1, direccion );
		Administrador admin = new Administrador( 1, "Cesar", "Meza", "Escobar", sucursal );
		
		ControladorClase controlador = new ControladorClase( admin, servicioClases, servicioSalas, servicioInstructores );
		
        VistaClases vista = new VistaClases();
  
        new ControladorVistaClases( vista, controlador );
        
	}
	
}
