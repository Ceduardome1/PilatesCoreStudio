package controladoresGraficos;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import actores.Instructor;
import interfaces.ControladorGrafico;
import interfaces.SelectorInstructor;
import interfaces.ServicioBusquedaInstructores;
import utilerias.Rutinas;
import vistas.VistaSelectorClientes;
import vistas.VistaSelectorInstructores;

public class ControladorSelectorInstructores implements ControladorGrafico, ActionListener, ListSelectionListener {
	
	private SelectorInstructor instructorListener;
	private ServicioBusquedaInstructores servicioInstructores;
	private VistaSelectorInstructores vista;
	private Integer idSeleccionado;
	
	private String campos[];
	private Integer idBuscar;
	
	public ControladorSelectorInstructores( VistaSelectorInstructores vista, ServicioBusquedaInstructores servicioInstructores, SelectorInstructor instructoresListener ) {
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
		vista.hacerVisible();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if( e.getSource() == vista.getBtnConsultar() ) {
			consultar();
			return;
		}
		
		if( e.getSource() == vista.getBtnSeleccionar() ) {
			seleccionar();
			return;
		}
		
		if( e.getSource() == vista.getBtnLimpiar() ) {
			reiniciar();
			return;
		}
		
		if( e.getSource() == vista.getBtnCancelar() ) {
			cerrar();
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

	@Override
	public void reiniciar() {
		idSeleccionado = null;
		vista.LimpiarCampos();
	}

	@Override
	public void cerrar() {
		vista.dispose();
	}
	
	private void seleccionar() {
			if( idSeleccionado == null ) {
				Rutinas.MensajeError("No hay ningún instructor seleccionado.");
				return;
			}
		Instructor instructor = consultarInstructor();
			if( instructor == null ) return;
		instructorListener.onInstructorSeleccionado( instructor );
		cerrar();
	}

	private void consultar() {
			if(! ValidarCampos() ) return;
		List<Instructor> instructor = consultarInstructores();
			if( instructor == null ) return;
		vista.ActualizarValoresTabla( instructor );
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
				
				Instructor filtro = new Instructor( idBuscar, campos[1], campos[2], campos[3], null );
				instructores = servicioInstructores.buscarInstructor( filtro );
					
					if ( instructores.isEmpty() ) {
						Rutinas.Mensaje("Aviso", "No hay coincidencias para el criterio de busqueda seleccionado.");
						return null;
					}
				
			} catch (Exception e) {
				e.printStackTrace();
				Rutinas.MensajeError( e.getMessage() );
				reiniciar();
				return null;
			}
			
		return instructores;
	}
	
	private Instructor consultarInstructor() {
		
		Instructor instructor = null;
		
			try {
				
				instructor = servicioInstructores.seleccionarInstructor( idSeleccionado );
					
					if ( instructor == null ) {
						Rutinas.MensajeError("El instructor seleccionado ya no existe en la BD.");
						return null;
					}
				
			} catch (Exception e) {
				Rutinas.MensajeError("Ocurrio un error al consultar el instructor.");
				reiniciar();
				return null;
			}
			
		return instructor;
	}

}