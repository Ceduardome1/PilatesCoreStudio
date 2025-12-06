package controladoresGraficos;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import actores.Instructor;
import dominio.Horario;
import interfaces.SelectorClase;
import interfaces.SelectorHorario;
import interfaces.SelectorInstructor;
import servicios.ServicioClases;
import servicios.ServicioInstructores;
import vistas.VistaSelectorClases;

public class ControladorSelectorClases implements ActionListener, SelectorInstructor, SelectorHorario {

	private final ServicioClases servicioClases;
	private final ServicioInstructores servicioInstructores;
	private final VistaSelectorClases vista;
	private final SelectorClase listenerClases;
	
	public ControladorSelectorClases( ServicioClases servicioClases, ServicioInstructores servicioInstructores,
	VistaSelectorClases vista, SelectorClase listenerClases ) {
		this.servicioClases = servicioClases;
		this.servicioInstructores = servicioInstructores;
		this.vista = vista;
		this.listenerClases = listenerClases;
		
		vista.HazVentana();
	}

	@Override
	public void onHorarioSeleccionado(Horario horario) {
			if(horario==null) return;
		
			
	}
	
	@Override
	public void onInstructorSeleccionado(Instructor instructor) {
			if(instructor==null) return;
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}