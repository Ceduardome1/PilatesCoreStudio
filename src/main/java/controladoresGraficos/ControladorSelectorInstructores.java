package controladoresGraficos;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import actores.Instructor;
import interfaces.SelectorInstructor;
import rutinas.Rutinas;
import servicios.ServicioInstructores;
import vistas.VistaSelectorClientes;
import vistas.VistaSelectorInstructores;

public class ControladorSelectorInstructores implements ActionListener, ListSelectionListener {
	
	private SelectorInstructor instructorListener;
	private ServicioInstructores servicioInstructores;
	private VistaSelectorInstructores vista;
	private Integer idSeleccionado;
	
	private String campos[];
	private Integer idBuscar;
	
	public ControladorSelectorInstructores( ServicioInstructores servicioInstructores, VistaSelectorInstructores vista, SelectorInstructor instructoresListener ) {
		this.vista = vista;
		this.instructorListener = instructoresListener;
		this.servicioInstructores = servicioInstructores;
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
			
		return true;
	}
	
	private List<Instructor> consultarInstructores() {
		List<Instructor> instructores = null;
			try {
				instructores = servicioInstructores.filtrarInstructores( new Instructor( idBuscar, campos[1], campos[2], campos[3], null ) );
					if ( instructores.isEmpty() ) {
						Rutinas.Mensaje("Aviso", "No hay coincidencias para el criterio de busqueda seleccionado.");
						return null;
					}
			} catch (Exception e) {
				Rutinas.MensajeError("Ocurrio un error al consultar el instructor.");
				vista.reiniciarInterfaz();
				return null;
			}
		return instructores;
	}
	
	private Instructor consultarInstructor() {
		Instructor instructor = null;
			try {
				instructor = servicioInstructores.buscarInstructor( idSeleccionado );
					if ( instructor == null ) {
						Rutinas.MensajeError("El instructor seleccionado ya no existe en la BD.");
						return null;
					}
			} catch (Exception e) {
				Rutinas.MensajeError("Ocurrio un error al consultar el instructor.");
				vista.reiniciarInterfaz();
				return null;
			}
		return instructor;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if( e.getSource() == vista.getBtnConsultar() ) {
				if(! ValidarCampos() ) return;
			List<Instructor> instructor = consultarInstructores();
				if( instructor == null ) return;
			vista.ActualizarValoresTabla( instructor );	
			return;
		}
		
		if( e.getSource() == vista.getBtnSeleccionar() ) {
				if( idSeleccionado == null ) {
					Rutinas.MensajeError("No hay ningún instructor seleccionado.");
					return;
				}
			Instructor instructor = consultarInstructor();
				if( instructor == null ) return;
			instructorListener.onInstructorSeleccionado( instructor );
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