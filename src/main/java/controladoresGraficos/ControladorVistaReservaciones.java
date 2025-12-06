package controladoresGraficos;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.List;

import actores.Cliente;
import bd.BD;
import bd.RepositorioClientes;
import dominio.Clase;
import dominio.SaldoClases;
import interfaces.SelectorClase;
import interfaces.SelectorCliente;
import servicios.*;
import vistas.VistaReservaciones;
public class ControladorVistaReservaciones implements ActionListener, SelectorCliente, SelectorClase {

	private VistaReservaciones vista;
	private ServicioClientes servicioClientes;
	private ServicioClases servicioClases;
	private ServicioSalas servicioSalas;
	private ServicioInstructores servicioInstructores;
	private ServicioReservaciones servicioReservaciones;
	
	private Clase clase;
	private Cliente cliente;
	
	public ControladorVistaReservaciones( ServicioClientes servicioClientes, VistaReservaciones vista ) {
		this.vista = vista;
		this.servicioClientes = servicioClientes; 

		clase=null;
		cliente = null;
		hazEscuchas();
		vista.HazVentana();
	}
	
	private void hazEscuchas() {
		vista.getBtnLimpiar().addActionListener( this );
		vista.getBtnReservar().addActionListener( this );
		vista.getBtnSalir().addActionListener( this );
		vista.getPanelClientes().getBtnSeleccionar().addActionListener( this );
		vista.getPanelClientes().getBtnLimpiar().addActionListener( this );
		vista.getPanelClases().getBtnSeleccionar().addActionListener( this );
		vista.getPanelClases().getBtnLimpiar().addActionListener( this );
	
	}
	
	@Override
	public void onClienteSeleccionado( Cliente cliente ) {
			if( cliente == null ) return;
		this.cliente = cliente;
		vista.getPanelClientes().MostrarCliente( cliente );
	}
	
	@Override
	public void onClaseSeleccionada( Clase clase ) {
			if( clase == null ) return;
		this.clase = clase;
		vista.getPanelClases().MostrarInfoClase( clase );
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {

		if( e.getSource() ==  vista.getBtnReservar() ) {
			
			return;
		}
	
		if( e.getSource() ==  vista.getPanelClientes().getBtnSeleccionar() ) {
		    vista.abrirSelectorClientes( this, servicioClientes );
		    return;
		}
		
		if( e.getSource() ==  vista.getPanelClases().getBtnSeleccionar() ) {
			 vista.abrirSelectorClases( this, servicioClases, servicioInstructores );
			return;
		}
		
		if( e.getSource() ==  vista.getPanelClientes().getBtnLimpiar() ) {
			cliente = null;
			vista.getPanelClientes().reiniciarInterfaz();
			return;
		}
		
		if( e.getSource() ==  vista.getPanelClases().getBtnLimpiar() ) {
			clase = null;
			vista.getPanelClases().reiniciarInterfaz();
			return;
		}
		
		if( e.getSource() ==  vista.getBtnLimpiar() ) {
			cliente = null; clase = null;
			vista.reiniciarInterfaz();
			return;
		}
		
		if( e.getSource() ==  vista.getBtnSalir() ) {
			vista.dispose();
			return;
		}
		
	}

	public static void main(String[] args) {
			BD bd = new BD(); 
			bd.crearConexion();
			RepositorioClientes repoClientes = new RepositorioClientes();
			ServicioClientes servicioClientes = new ServicioClientes( repoClientes, bd );
	       
	        VistaReservaciones vista = new VistaReservaciones();
	    	new ControladorVistaReservaciones( servicioClientes, vista );
	}

}