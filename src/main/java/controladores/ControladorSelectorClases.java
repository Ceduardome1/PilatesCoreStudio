package controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import dominio.Horario;
import interfaces.SelectorClase;
import interfaces.SelectorHorario;
import interfaces.SelectorInstructor;
import personal.Instructor;
import servicios.GestionClases;
import servicios.GestionInstructores;
import vistas.VistaSelectorClases;

public class ControladorSelectorClases implements ActionListener, SelectorInstructor, SelectorHorario {

	private final GestionClases servicioClases;
	private final GestionInstructores servicioInstructores;
	private final VistaSelectorClases vista;
	private final SelectorClase listenerClases;
	
	public ControladorSelectorClases( GestionClases servicioClases, GestionInstructores servicioInstructores,
	VistaSelectorClases vista, SelectorClase listenerClases ) {
		this.servicioClases = servicioClases;
		this.servicioInstructores = servicioInstructores;
		this.vista = vista;
		this.listenerClases = listenerClases;
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