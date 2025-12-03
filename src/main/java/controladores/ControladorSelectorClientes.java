package controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import clientes.Cliente;
import extras.Rutinas;
import servicios.GestionClientes;
import vistas.VistaSelectorClientes;

public class ControladorSelectorClientes implements ActionListener, ListSelectionListener {
	
	private SelectorCliente clienteListener;
	private GestionClientes servicioClientes;
	private VistaSelectorClientes vista;
	private Integer idSeleccionado;
	
	private String campos[];
	private Integer idBuscar;
	private Long telBusqueda;
	
	public ControladorSelectorClientes( GestionClientes servicioClientes, VistaSelectorClientes vista, SelectorCliente clienteListener ) {
		this.vista = vista;
		this.clienteListener = clienteListener;
		this.servicioClientes = servicioClientes;
		campos = new String[ VistaSelectorClientes.getNCamposCliente()];
		idSeleccionado = null;
		HazEscuchas();
	}
	
	private void HazEscuchas() {
		vista.getBtnConsultar().addActionListener( this );
		vista.getBtnSeleccionar().addActionListener( this );
		vista.getBtnCancelar().addActionListener( this );
		vista.getBtnLimpiar().addActionListener( this );
		vista.getReporteClientes().getJTable().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		vista.getReporteClientes().getJTable().getSelectionModel()
		.addListSelectionListener( this );
		vista.HazVentana();
	}
	
	private boolean ValidarCampos() {
		JTextField[] camposCliente = vista.getCamposCliente();
		
			for( int i=0, n = camposCliente.length; i<n; i++ ) {
				campos[i] = camposCliente[i].getText().toUpperCase();
					if( campos[i].isBlank() )
						campos[i] = null;
			}
			
			if( campos[0] != null ) {
					if( !Rutinas.EsCadenaNumerica( campos[0] ) ) {
						Rutinas.MensajeError("El identificador es númerico.");
						return false;
					}
				idBuscar = Integer.parseInt( campos[0] );	
			} else idBuscar = null;
			
			if( campos[4] != null ) {
					if( !Rutinas.EsCadenaNumerica( campos[4] ) 
					|| campos[4].length()!=10 ) {
						Rutinas.MensajeError("Formato de telefono incorrecto.");
						return false;
					}
				telBusqueda = Long.parseLong( campos[4] );
			} else telBusqueda = null;

		return true;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if( e.getSource() == vista.getBtnConsultar() ) {
				if(! ValidarCampos() ) return;
			List<Cliente> clientes = 
				servicioClientes.buscarClientes( idBuscar, campos[1], campos[2], campos[3], telBusqueda, campos[5] );
			vista.ActualizarValoresTabla( clientes );	
			return;
		}
		
		if( e.getSource() == vista.getBtnSeleccionar() ) {
				if( idSeleccionado == null ) {
					Rutinas.MensajeError("No hay ningún cliente seleccionado.");
					return;
				}
			Cliente cliente = servicioClientes.buscarCliente( idSeleccionado );
			clienteListener.onClienteSeleccionado( cliente );
			vista.dispose();
			return;
		}
		
		if( e.getSource() == vista.getBtnLimpiar() ) {
			idSeleccionado = null;
			vista.LimpiarCampos();
			return;
		}
		
		if( e.getSource() == vista.getBtnCancelar() ) {
			vista.dispose();
			return;
		}
		
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
	    if (e.getValueIsAdjusting()) return;
	    int fila = vista.getReporteClientes().getJTable().getSelectedRow();
	    idSeleccionado = (fila >= 0) ? 
	    	vista.RecuperarIdClienteSeleccionado(fila) : null;
	}

}