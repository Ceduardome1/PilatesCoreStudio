package controladores;

import java.awt.event.*;
import java.lang.reflect.InvocationTargetException;

import dominio.Operaciones;
import extras.Rutinas;
import personal.Permiso;
import vistas.VistaNavegacion;

public class ControladorNavegacion implements ActionListener {

    private VistaNavegacion vista;
    
    public ControladorNavegacion(VistaNavegacion vista) {
        this.vista = vista;
        HazEscuchas();
    }
    
    private void HazEscuchas() {
    	vista.getBtnIngresar().addActionListener(this);
        vista.getBtnVolver().addActionListener(this);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {

	        if (e.getSource() == vista.getBtnIngresar()) {
	
	            Permiso permiso = (Permiso) vista.getCmbOperaciones().getSelectedItem();
	
	            if (permiso == null) {
	                Rutinas.MensajeError("Debe seleccionar una operación");
	                return;
	            }
	
	            Operaciones operacion = permiso.getOperacion();
	            boolean escritura = permiso.isEscritura();
	
	            Class<?> siguienteVentana = escritura ?
	                    operacion.getUIGestion() :
	                    operacion.getUIConsulta();
	
	            if (siguienteVentana != null) {
	                try {
	                    vista.setVisible(false);
	                    siguienteVentana.getDeclaredConstructor().newInstance();
	                }
	                catch (InstantiationException | IllegalAccessException |
	                       InvocationTargetException | NoSuchMethodException ex) {
	                    ex.printStackTrace();
	                }
	            }
	
	            vista.Limpiar();
	            vista.setVisible(true);
	            return;
	        }
	
	        if (e.getSource() == vista.getBtnVolver()) {
	            vista.dispose();
	        }
    }
    
}