package controladoresGraficos;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import actores.Instructor;
import casosUso.ControladorClase;
import dominio.Clase;
import dominio.Horario;
import interfaces.ControladorGrafico;
import interfaces.SelectorHorario;
import interfaces.SelectorInstructor;
import utilerias.Rutinas;
import vistas.VistaClases;

public class ControladorVistaClases implements ControladorGrafico, ActionListener, SelectorHorario, SelectorInstructor {

	private final VistaClases vista;
	private final ControladorClase controlador;
	
	private Horario horario;
	private Instructor instructor;
	
	public ControladorVistaClases( VistaClases vista, ControladorClase controlador ) {
		this.vista = vista;
		this.controlador = controlador;
		
		horario=null;
		instructor=null;
		
		hazEscuchas();
		vista.hacerVisible();
	}

	private void hazEscuchas() {
		vista.getBtnLimpiar().addActionListener( this );
		vista.getBtnGuardar().addActionListener( this );
		vista.getBtnSalir().addActionListener( this );
		vista.getPanelHorarios().getBtnSeleccionar().addActionListener( this );
		vista.getPanelHorarios().getBtnLimpiar().addActionListener( this );
		vista.getPanelInstructores().getBtnSeleccionar().addActionListener( this );
		vista.getPanelInstructores().getBtnLimpiar().addActionListener( this );
	}

	@Override
	public void actionPerformed(ActionEvent e) {

			if ( e.getSource() == vista.getPanelHorarios().getBtnSeleccionar() ){
				vista.abrirSelectorHorario( this );
				return;
			};
			
			if ( e.getSource() == vista.getPanelInstructores().getBtnSeleccionar() ){
				vista.abrirSelectorInstructor( controlador, this );
				return;
			};
			
			if ( e.getSource() ==  vista.getBtnGuardar() ){
				registrar();
				return;
			};
			
			if ( e.getSource() == vista.getBtnLimpiar() ) {
				reiniciar();
				return;
			};
			
			if ( e.getSource() == vista.getPanelHorarios().getBtnLimpiar() ){
				reiniciarHorario();
				return;
			};
			
			if ( e.getSource() == vista.getPanelInstructores().getBtnLimpiar() ){
				reiniciarInstructor();
				return;
			};
			
			if ( e.getSource() == vista.getBtnSalir() ){
				cerrar();
				return;
			};	
	}

	@Override
	public void onHorarioSeleccionado(Horario horario) {
			if( horario == null) return;
		this.horario = horario;
		vista.getPanelHorarios().MostrarHorario( horario );
	}

	@Override
	public void onInstructorSeleccionado(Instructor instructor) {
			if( instructor == null) return;
		this.instructor = instructor;
		vista.getPanelInstructores().MostrarInstructor(instructor);
	}

	@Override
	public void reiniciar() {
		this.horario = null;
		this.instructor = null;
		vista.reiniciar();
	}

	@Override
	public void cerrar() {
		vista.dispose();
	}
	
	private void reiniciarInstructor() {
		vista.getPanelInstructores().reiniciar();
		this.instructor = null;
	}

	private void reiniciarHorario() {
		vista.getPanelHorarios().reiniciar();
		this.horario = null;
	}

	private boolean validar() {
		
			 if( horario == null) {
				 Rutinas.MensajeError("Debe definir un horario.");
				 return false;
			 }
		
			 if( instructor == null ) {
				 Rutinas.MensajeError("Debe seleccionar un instructor.");
				 return false;
			 }
	
		 return true;
	}
	
	private void registrar() {
		
			if( !validar() ) return;
		Clase clase = null;
			try {
				
				clase = controlador.registrarClase( instructor, horario );
				
			} catch (Exception e) {
				Rutinas.MensajeError( e.getMessage() );
				reiniciar();
				return;
			}
		
		Rutinas.Mensaje( "Información", "La Clase fue registrada con éxito!." );
		reiniciar();
		vista.mostrarReporte( clase );
	}
	
}
