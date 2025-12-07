package controladoresGraficos;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.LocalTime;

import com.github.lgooddatepicker.optionalusertools.DateVetoPolicy;

import dominio.Horario;
import dominio.HorarioLaboral;
import dominio.ReglasNegocio;
import interfaces.ControladorGrafico;
import interfaces.PoliticaHoras;
import interfaces.SelectorHorario;
import utilerias.Rutinas;
import vistas.VistaSelectorHorarios;

public class ControladorSelectorHorarios implements ControladorGrafico, ActionListener {

	private VistaSelectorHorarios vista; 
	private SelectorHorario listenerHorario;
	public ControladorSelectorHorarios( VistaSelectorHorarios vista, SelectorHorario listenerHorario ) {
		this.vista = vista;
		this.listenerHorario = listenerHorario;
		HazEscuchas();
		LimitarHorarios();
		vista.hacerVisible();
	}
	
	private void HazEscuchas() {
		vista.getBtnSeleccionar().addActionListener( this );
		vista.getBtnCancelar().addActionListener( this );
		vista.getBtnLimpiar().addActionListener( this );
	}
	
	
	private void LimitarHorarios() {
		
		LocalDate fechaInicial = LocalDate.now().plusDays( 
		    ReglasNegocio.getLimInfDiasCreacionClase() 
		);
	    LocalDate fechaFinal = fechaInicial.plusDays( 
	    	ReglasNegocio.getLimSupDiasCreacionClase() 
	    );
		
	    vista.getPanelFecha().getSettings().setVetoPolicy( new DateVetoPolicy() {
	        @Override
	        public boolean isDateAllowed(LocalDate date) {
	            return 
	            	HorarioLaboral.esLaboral( date.getDayOfWeek() )
	            	&& !date.isBefore(fechaInicial)
	            	&& !date.isAfter(fechaFinal);
	        }
	    });
	    
	    PoliticaHoras politicaHoras = new PoliticaHoras( 
	    	vista.getPanelFecha()
	    	, vista.getPanelHora() 
	    );
	    vista.getPanelHora().getSettings().setVetoPolicy( politicaHoras );
	    
	}
	
	private Horario obtenerHorario() {
		LocalDate fecha = vista.getPanelFecha().getDate();
			if( fecha == null ) {
				Rutinas.MensajeError("Debe seleccionar la fecha");
				return null;
			}
		
		LocalTime hora = vista.getPanelHora().getTime();
			if( hora == null ) {
				Rutinas.MensajeError("Debe seleccionar la hora");
				return null;
			}
		return new Horario( fecha, hora );
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
			if( e.getSource() == vista.getBtnLimpiar() ) {
				reiniciar();
				return;
			}
		
			if( e.getSource() == vista.getBtnCancelar() ) {
				cerrar();
				return;
			}
			
			if( e.getSource() == vista.getBtnSeleccionar() ) {
				seleccionar();
				return;
			}
		
	}

	@Override
	public void reiniciar() {
		vista.reiniciar();
	}

	@Override
	public void cerrar() {
		vista.dispose();
	}

	private void seleccionar() {
		Horario horario = obtenerHorario();
			if( horario == null ) return;
		listenerHorario.onHorarioSeleccionado( horario );
		vista.dispose();
	}

}
