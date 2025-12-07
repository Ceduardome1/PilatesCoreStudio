package controladoresGraficos;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import actores.Instructor;
import dominio.Clase;
import dominio.Horario;
import interfaces.ControladorGrafico;
import interfaces.SelectorClase;
import interfaces.SelectorHorario;
import interfaces.SelectorInstructor;
import interfaces.ServicioBusquedaClases;
import interfaces.ServicioBusquedaInstructores;
import utilerias.Rutinas;
import vistas.VistaSelectorClases;

public class ControladorSelectorClases implements ControladorGrafico, ActionListener, SelectorInstructor, SelectorHorario, ListSelectionListener {
	
	private final VistaSelectorClases vista;
	private final ServicioBusquedaInstructores servicioInstructores;
	private final ServicioBusquedaClases servicioClases;
	private final SelectorClase listenerClase;
	
	private Horario horario;
	private Instructor instructor;
	private Integer idClase;
	
	public ControladorSelectorClases( VistaSelectorClases vista,
	ServicioBusquedaClases servicioClases,
	ServicioBusquedaInstructores servicioInstructores, SelectorClase listenerClase ) {
	
		this.vista = vista;
		this.servicioInstructores = servicioInstructores;
		this.servicioClases = servicioClases;
		this.listenerClase = listenerClase;
		HazEscuchas();
		vista.hacerVisible();
		
		idClase = null;
		horario = null;
		instructor = null;
	
	}

	private void HazEscuchas() {
		vista.getReporteClases().getJTable().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		vista.getReporteClases().getJTable().getSelectionModel().addListSelectionListener( this );

		vista.getBtnConsultar().addActionListener( this );
		vista.getBtnLimpiar().addActionListener( this );
		vista.getBtnSeleccionar().addActionListener( this );
		vista.getBtnCancelar().addActionListener(this);
		
		vista.getPanelHorarios().getBtnLimpiar().addActionListener( this );
		vista.getPanelHorarios().getBtnSeleccionar().addActionListener( this );
		
		vista.getPanelInstructores().getBtnLimpiar().addActionListener( this );
		vista.getPanelInstructores().getBtnSeleccionar().addActionListener( this );
	}
	
	@Override
	public void onHorarioSeleccionado(Horario horario) {
			if( horario==null ) return;
		this.horario = horario;
		vista.getPanelHorarios().MostrarHorario( horario );
	}
	
	@Override
	public void onInstructorSeleccionado(Instructor instructor) {
			if(instructor==null) return;
		this.instructor = instructor;
		vista.getPanelInstructores().MostrarInstructor( instructor );
	}

	@Override
	public void actionPerformed( ActionEvent e ) {
		
		if( e.getSource() == vista.getBtnConsultar() ){
			consultar();
			return;
		}
		
		if( e.getSource() == vista.getBtnLimpiar() ){
			reiniciar();
			return;
		}
		
		if( e.getSource() == vista.getBtnSeleccionar() ){
			seleccionar();
			return;
		}
		
		if( e.getSource() == vista.getPanelHorarios().getBtnLimpiar() ){
			limpiarHorario();
			return;
		}
		
		if( e.getSource() == vista.getPanelHorarios().getBtnSeleccionar() ){
			seleccionarHorario();
			return;
		}
		
		if( e.getSource() == vista.getPanelInstructores().getBtnLimpiar()  ){
			limpiarInstructor();
			return;
		}
		
		if( e.getSource() == vista.getPanelInstructores().getBtnSeleccionar()  ){
			seleccionarInstructor();
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
	    idClase = (fila >= 0) ? 
	    	vista.RecuperarIdClienteSeleccionado(fila) : null;
	}
	
	@Override
	public void reiniciar() {
		idClase = null;
		horario = null;
		instructor = null;
		vista.reiniciar();
	}

	@Override
	public void cerrar() {
		vista.dispose();
	}
	
	private void consultar() {
		
		Clase filtro = new Clase( null, null, instructor, horario, null );
		List< Clase > listaClases=null;
			
			try {
				
				listaClases = servicioClases.buscarClase( filtro );
			
					if( listaClases.isEmpty() ) {
						Rutinas.MensajeError("No hay clases que satisfagan esos criterios.");
						return;
					}
				
				vista.ActualizarValoresTabla( listaClases );
					
			} catch (Exception e) {
				Rutinas.MensajeError( e.getMessage() );
				return;
			}
			
		
	}
	
	private void seleccionar() {
		
			if( idClase == null ) {
				Rutinas.MensajeError("Debe seleccionar una clase.");
				return;
			}
		
			try {
				Clase clase = servicioClases.seleccionarClase( idClase );	
				listenerClase.onClaseSeleccionada( clase );
				cerrar();
			}
			catch( Exception e ) {
				Rutinas.MensajeError( e.getMessage() );
			}
			
	}
	
	private void seleccionarInstructor() {
		vista.abrirSelectorInstructor( servicioInstructores, this );
	}

	private void limpiarInstructor() {
		instructor = null;
		vista.getPanelInstructores().reiniciar();
	}

	private void seleccionarHorario() {
		vista.abrirSelectorHorario( this );
	}

	private void limpiarHorario() {
		horario = null;
		vista.getPanelHorarios().reiniciar();
	}

}