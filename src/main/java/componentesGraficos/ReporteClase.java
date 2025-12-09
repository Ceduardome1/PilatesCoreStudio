package componentesGraficos;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.*;

import actores.Instructor;
import dominio.Clase;
import dominio.Horario;
import dominio.Sala;
import interfaces.ComponenteGrafico;

public class ReporteClase extends JDialog implements ComponenteGrafico {

	private static final long serialVersionUID = 1L;
	private static final int anchoVent=500, altoVent=500;

	private MiTablaEstatica reporteClase;
	private MiTablaEstatica reporteSala;
	private MiTablaEstatica reporteInstructor;

	public ReporteClase( Clase clase ) {
		hazInterfaz( clase );
		hacerVisible();
	}	

	private void hazInterfaz( Clase clase ) {
		setLayout( ( new GridBagLayout() ) );
		GridBagConstraints gbc = new GridBagConstraints();
	    gbc.fill = GridBagConstraints.BOTH;
	    gbc.anchor = GridBagConstraints.CENTER;
		gbc.insets = new Insets(pxMargen, pxMargen, pxMargen, pxMargen);
		
		String encabezadosClases[] = { "Fecha", "Hora" };
		reporteClase = new MiTablaEstatica( encabezadosClases, "Horario de la Clase" );
		
		String encabezadosSala[] = { "ID", "Camas Disponibles" };
		reporteSala = new MiTablaEstatica( encabezadosSala, "Información de la Sala" );
		
		String encabezadosInstructor[] = { "ID", "Nombre", "Ap.Paterno", "Ap.Materno" };
		reporteInstructor = new MiTablaEstatica( encabezadosInstructor, "Información del Instructor" );

		JComponent comps [][] = {
			{reporteClase, reporteSala },
			{reporteInstructor}
		};
		
		JLabel etiq = (JLabel) TextoPrincipal( EtiquetaCentranda( new JLabel("Clase Registrada:") ) );	
		gbc.gridy = 0; gbc.weighty=1;
		add( etiq, gbc );
		
		JPlantilla panTablas = new JPlantilla( comps );	
		gbc.gridx = 0; gbc.gridy = 1;
		gbc.weightx = 1; gbc.weighty=49;
		add( panTablas, gbc );

		mostrarInfoClase( clase );
		
	}

	public void mostrarInfoClase( Clase clase ) {
		
			if( clase == null ) return;
		reiniciar();
		Horario hora = clase.getHorario();
		Sala sala = clase.getSala();
		Instructor inst = clase.getInstructor();
		
		String infoClase[] = { 
			hora.mostrarFecha(),
			hora.mostrarHora()
		};
		reporteClase.agregaRenglon( infoClase );
		
		String infoSala[] = { 
			""+sala.getIdSala(),
			""+sala.getCamasDisponibles()+" camas"
		};
		reporteSala.agregaRenglon( infoSala );
		
		String infoInst[] = { 
				""+inst.getIdPersonal(),
				inst.getNombre(),
				inst.getApellidoPat(),
				inst.getApellidoMat()
		};
		reporteInstructor.agregaRenglon( infoInst );
		
	}
	
	@Override
	public void reiniciar() {}

	@Override
	public void hacerVisible() {
		setSize( anchoVent, altoVent );
		setLocationRelativeTo(null);
		setResizable( true ); 
		setDefaultCloseOperation( DISPOSE_ON_CLOSE );
		setModal( true );
		setVisible( true );
	}

}
