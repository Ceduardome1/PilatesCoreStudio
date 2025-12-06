package controladoresGraficos;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import actores.Administrador;
import actores.Instructor;
import dominio.Clase;
import dominio.Horario;
import dominio.Sala;
import interfaces.SelectorHorario;
import interfaces.SelectorInstructor;
import rutinas.Rutinas;
import servicios.ServicioClases;
import servicios.ServicioInstructores;
import servicios.ServicioSalas;
import vistas.VistaClases;

public class ControladorVistaClases implements ActionListener, SelectorHorario, SelectorInstructor {

	private final VistaClases vista;
	private final ServicioInstructores servicioInstructores;
	private final ServicioClases servicioClases;
	private final ServicioSalas servicioSalas;
	
	private final Administrador responsable;
	private Horario horario;
	private Sala sala;
	private Instructor instructor;
	
	public ControladorVistaClases(VistaClases vista, ServicioInstructores servicioInstructores, ServicioClases servicioClases,
	ServicioSalas servicioSalas, Administrador responsable) {
		this.vista = vista;
		this.servicioInstructores = servicioInstructores;
		this.servicioClases = servicioClases;
		this.servicioSalas = servicioSalas;
		this.responsable = responsable;
		
		horario=null;
		sala=null;
		instructor=null;
		
		hazEscuchas();
		vista.HazVentana();
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
		
			if ( e.getSource() ==  vista.getBtnGuardar() ){
				guardarClase();
				return;
			};
			
			if ( e.getSource() == vista.getPanelHorarios().getBtnSeleccionar() ){
				vista.abrirSelectorHorario( this );
				return;
			};
			
			if ( e.getSource() == vista.getPanelInstructores().getBtnSeleccionar() ){
				vista.abrirSelectorInstructor( this, servicioInstructores );
				return;
			};
			
			if ( e.getSource() == vista.getBtnLimpiar() ) {
				vista.reiniciarInterfaz();
				this.horario = null;
				this.instructor = null;
				return;
			};
			
			if ( e.getSource() == vista.getPanelHorarios().getBtnLimpiar() ){
				vista.getPanelHorarios().reiniciarInterfaz();
				this.horario = null;
				return;
			};
			
			if ( e.getSource() == vista.getPanelInstructores().getBtnLimpiar() ){
				vista.getPanelInstructores().reiniciarInterfaz();
				this.instructor = null;
				return;
			};
			
			if ( e.getSource() == vista.getBtnSalir() ){
				vista.dispose();
				return;
			};	
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
	
	private Sala asignarSala() {
		
		try {
			
			return sala = servicioSalas.asignarSala( horario );
			
		} catch (Exception e) {
			Rutinas.MensajeError("Ocurrio un error al asignar la sala.");
			vista.reiniciarInterfaz();
			return null;
		}
		
	}
	
	private void guardarClase() {
		
			if( !validar() ) return;
			
			if( asignarSala() == null ) {
				Rutinas.MensajeError( "No hay salas disponibles en ese horario." );
				return;
			}
				
			try {
				
				Integer idClase = servicioClases.generarIdClase(); 
				
				servicioClases.registrarClase( 
					new Clase( idClase, sala, instructor, horario, responsable ) 
				);
				
			} catch (Exception e) {
				Rutinas.MensajeError("Ocurrio un error al guardar la clase.");
				vista.reiniciarInterfaz();
				return;
			}
		
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
	
}
