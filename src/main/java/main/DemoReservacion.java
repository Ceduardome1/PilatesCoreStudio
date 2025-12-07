package main;

import actores.Recepcionista;
import bd.*;
import casosUso.ControladorReservacion;
import servicios.*;
import vistas.VistaReservaciones;
import controladoresGraficos.ControladorVistaReservaciones;
import dominio.Direccion;
import dominio.Sucursal;

public class DemoReservacion {
	
	public static void main( String[] args ) {
		BD bd = new BD(); 
		bd.crearConexion();
		
		RepositorioClientes repoClientes = new RepositorioClientes();
		ServicioClientes servicioClientes = new ServicioClientes( bd, repoClientes );
       
		RepositorioInstructores repoInstructores = new RepositorioInstructores();
		ServicioInstructores servicioInstructores = new ServicioInstructores( bd,repoInstructores );
		
		RepositorioSalas repoSalas = new RepositorioSalas();
		ServicioSalas servicioSalas = new ServicioSalas( bd, repoSalas );
	
		RepositorioClases repoClases = new RepositorioClases();
		OrquestadorClases servicioClases = new OrquestadorClases( bd, repoClases, servicioSalas, servicioInstructores );
		
		RepositorioReservaciones repoReservaciones = new RepositorioReservaciones();
		OrquestadorReservaciones servicioReservaciones = new OrquestadorReservaciones( bd, repoReservaciones, servicioClientes, servicioSalas );
		
		Direccion direccion = new Direccion( 80000, (short)231, "Cristobal Colon", "Centro" );
		Sucursal sucursal = new Sucursal( 1, direccion );
		Recepcionista recepcionista = new Recepcionista( 1, "Cesar", "Meza", "Escobar", sucursal );
		
		ControladorReservacion controlador = new ControladorReservacion( 
			recepcionista, servicioReservaciones, servicioClases
		);
		
        VistaReservaciones vista = new VistaReservaciones();
  
        new ControladorVistaReservaciones( vista, controlador );
	}
	
}