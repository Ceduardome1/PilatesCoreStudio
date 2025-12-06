package componentesGraficos;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.*;

import actores.Instructor;
import dominio.Clase;
import dominio.Horario;
import dominio.Sala;
import interfaces.Formato;

public class PanelClases extends JPanel implements Formato {
	private static final long serialVersionUID = 1L;
	private JButton btnSeleccionar, btnLimpiar;
	private MiTablaEstatica reporteClase;
	private MiTablaEstatica reporteSala;
	private MiTablaEstatica reporteInstructor;
	
	public PanelClases() {
		HazInterfaz();
	}
	
	private void HazInterfaz() {
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
	
		btnSeleccionar = (JButton) Formato.TextoAcentuado( new JButton("Buscar Clase"), colorAcento1 );
		btnLimpiar= (JButton) Formato.TextoAcentuado( new JButton("Limpiar"), colorAcento2 );
		
		JComponent comps [][] = {
			{reporteClase, reporteSala },
			{reporteInstructor}
		};
		
		JComponent comps1 [][] = {
			{ btnLimpiar, btnSeleccionar }
		};
		
		JLabel etiq = (JLabel) Formato.TextoPrincipal( Formato.EtiquetaCentranda( new JLabel("Selector Clases") ) );	
		gbc.gridy = 0; gbc.weighty=1;
		add( etiq, gbc );
		
		JPlantilla panTablas = new JPlantilla( comps );	
		gbc.gridx = 0; gbc.gridy = 1;
		gbc.weightx = 1; gbc.weighty=49;
		add( panTablas, gbc );
		
		JPlantilla panBtns = new JPlantilla( comps1 );	
		gbc.gridy = 2; gbc.weighty=1;
		add( panBtns, gbc );
		
	}
	
	public void MostrarInfoClase( Clase clase ) {
		reiniciarInterfaz();
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
			""+sala.getUltimaCamaAsignada()+" camas"
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
	public void reiniciarInterfaz() {
		reporteClase.reiniciarInterfaz();
		reporteInstructor.reiniciarInterfaz();
	}
	
	public JButton getBtnSeleccionar() {
		return btnSeleccionar;
	}

	public JButton getBtnLimpiar() {
		return btnLimpiar;
	}
}