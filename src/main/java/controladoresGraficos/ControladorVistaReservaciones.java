package controladoresGraficos;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import actores.Cliente;
import casosUso.ControladorReservacion;
import dominio.Clase;
import dominio.Reservacion;
import interfaces.ControladorGrafico;
import interfaces.SelectorClase;
import interfaces.SelectorCliente;
import utilerias.Rutinas;
import vistas.VistaReservaciones;
public class ControladorVistaReservaciones implements ControladorGrafico, ActionListener, SelectorCliente, SelectorClase {

	private VistaReservaciones vista;
	private ControladorReservacion controlador;
	
	private Clase clase;
	private Cliente cliente;
	
	public ControladorVistaReservaciones( VistaReservaciones vista, ControladorReservacion controlador ) {
		this.vista = vista;
		this.controlador = controlador; 

		clase=null;
		cliente = null;
		
		hazEscuchas();
		vista.hacerVisible();
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
			
		vista.getPanelClientes().MostrarCliente( cliente );
		
			if( !cliente.tieneSaldoValido() ) {
				Rutinas.MensajeError("El cliente no cuenta con saldo de clases disponible.");
				return;
			}
			
		this.cliente = cliente;
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
			reservar();
			return;
		}
	
		if( e.getSource() ==  vista.getPanelClientes().getBtnSeleccionar() ) {
		    vista.abrirSelectorClientes( controlador, this );
		    return;
		}
		
		if( e.getSource() ==  vista.getPanelClases().getBtnSeleccionar() ) {
			 vista.abrirSelectorClases( controlador, controlador, this );
			return;
		}
		
		if( e.getSource() ==  vista.getPanelClientes().getBtnLimpiar() ) {
			reiniciarCliente();
			return;
		}
		
		if( e.getSource() ==  vista.getPanelClases().getBtnLimpiar() ) {
			reiniciarClase();
			return;
		}
		
		if( e.getSource() ==  vista.getBtnLimpiar() ) {
			reiniciar();
			return;
		}
		
		if( e.getSource() ==  vista.getBtnSalir() ) {
			cerrar();
			return;
		}
		
	}

	@Override
	public void reiniciar() {
		cliente = null; clase = null;
		vista.reiniciar();
	}

	@Override
	public void cerrar() {
		vista.dispose();
	}
	
	private void reservar() {
		
			if( cliente == null ) {
				Rutinas.MensajeError( "Debe seleccionar un cliente." );
				return;
			}
		
			if( clase == null ) {
				Rutinas.MensajeError( "Debe seleccionar una clase." );
				return;
			}
			
		Reservacion reservacion = null;
		
			try {
				
				reservacion = controlador.reservarClase( cliente, clase );
				
			} catch ( Exception ex ) {
				Rutinas.MensajeError( ex.getMessage() );
				return;
			}
		
		Rutinas.Mensaje( "Información", "La Reservacion fue registrada con éxito!." );
		reiniciar();
		vista.mostrarReporte( reservacion );
	}

	public void reiniciarCliente() {
		cliente = null; 
		vista.getPanelClientes().reiniciar();
	}
	
	public void reiniciarClase() {
		clase = null;
		vista.getPanelClases().reiniciar();
	}

}