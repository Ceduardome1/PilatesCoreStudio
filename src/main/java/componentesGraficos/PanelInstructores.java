package componentesGraficos;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.*;

import actores.Instructor;
import interfaces.ComponenteGrafico;

public class PanelInstructores extends JPanel implements ComponenteGrafico {
	private static final long serialVersionUID = 1L;
	private JButton btnSeleccionar, btnLimpiar;
	private MiTablaEstatica reporteInstructor;
	
	public PanelInstructores() {
		HazInterfaz();
	}
	
	private void HazInterfaz() {
		setLayout( ( new GridBagLayout() ) );
		GridBagConstraints gbc = new GridBagConstraints();
	    gbc.fill = GridBagConstraints.BOTH;
	    gbc.anchor = GridBagConstraints.CENTER;
		gbc.insets = new Insets(pxMargen, pxMargen, pxMargen, pxMargen);

		String encabezadosInstructor[] = { "ID", "Nombre", "Ap.Paterno", "Ap.Materno" };
		reporteInstructor = new MiTablaEstatica( encabezadosInstructor, "Información del Instructor" );
	
		btnSeleccionar = (JButton) TextoAcentuado( new JButton("Buscar Instructor"), colorAcento1 );
		btnLimpiar= (JButton) TextoAcentuado( new JButton("Limpiar"), colorAcento2 );
		
		JComponent comps [][] = {
			{reporteInstructor}
		};
		
		JComponent comps1 [][] = {
			{ btnLimpiar, btnSeleccionar }
		};
		
		JPlantilla panTablas = new JPlantilla( comps );	
		gbc.gridx = 0; gbc.gridy = 0;
		gbc.weightx = 1; gbc.weighty=49;
		add( panTablas, gbc );
		
		JPlantilla panBtns = new JPlantilla( comps1 );	
		gbc.gridy = 1; gbc.weighty=1;
		add( panBtns, gbc );
		
	}
	
	public void MostrarInstructor( Instructor inst ) {
			if( inst == null ) return;
		reiniciar();
		String infoInst[] = { 
				""+inst.getIdPersonal(),
				inst.getNombre(),
				inst.getApellidoPat(),
				inst.getApellidoMat()
		};
		reporteInstructor.agregaRenglon( infoInst );
		
	}
	
	public JButton getBtnSeleccionar() {
		return btnSeleccionar;
	}

	public JButton getBtnLimpiar() {
		return btnLimpiar;
	}

	public void reiniciar() {
		reporteInstructor.reiniciar();
	}
	
	@Override
	public void hacerVisible() {}
}