package controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.List;

import clases.Clase;
import clientes.Cliente;
import clientes.SaldoClases;
import interfaces.SelectorClase;
import interfaces.SelectorCliente;
import servicios.*;
import vistas.VistaReservaciones;
public class ControladorReservaciones implements ActionListener, SelectorCliente, SelectorClase {

	private VistaReservaciones vista;
	private GestionClientes servicioClientes;
	private GestionClases servicioClases;
	private GestionSalas servicioSalas;
	private GestionInstructores servicioInstructores;
	private GestionReservaciones servicioReservaciones;
	
	private Clase clase;
	private Cliente cliente;
	
	public ControladorReservaciones( GestionClientes servicioClientes, VistaReservaciones vista ) {
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
			GestionClientes servicioMock = new GestionClientes() {

		        @Override
		        public List<Cliente> buscarClientes(Integer id, String nombre, String apP, String apM, Long tel, String correo) {
		            return List.of(
		                new Cliente(1, 551234567L, "JUAN", "PÉREZ", "LOPEZ", "correo1@mail.com", new SaldoClases( 5, LocalDate.now() ) ),
		                new Cliente(2, 558765432L, "MARIA", "DÍAZ", "ROSALES", "correo2@mail.com", new SaldoClases( 5, LocalDate.now() )),
		                new Cliente(3, 554433221L, "PEDRO", "MEZA", "HERNANDEZ", "correo3@mail.com", new SaldoClases( 5, LocalDate.now() ))
		            );
		        }
	
		        @Override
		        public Cliente buscarCliente(Integer id) {
		            switch (id) {
		                case 1: return new Cliente(1, 551234567L, "JUAN", "PÉREZ", "LOPEZ", "correo1@mail.com", new SaldoClases( 5, LocalDate.now() ));
		                case 2: return new Cliente(2, 558765432L, "MARIA", "DÍAZ", "ROSALES", "correo2@mail.com", new SaldoClases( 5, LocalDate.now() ));
		                case 3: return new Cliente(3, 554433221L, "PEDRO", "MEZA", "HERNANDEZ", "correo3@mail.com", new SaldoClases( 5, LocalDate.now() ));
		                default: return null;
		            }
		        }
		            
	        };
	       
	        VistaReservaciones vista = new VistaReservaciones();
	    	new ControladorReservaciones( servicioMock, vista );
	    }

}