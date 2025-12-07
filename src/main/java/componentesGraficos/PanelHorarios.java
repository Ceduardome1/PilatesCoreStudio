package componentesGraficos;
import java.awt.*;
import javax.swing.*;
import dominio.Horario;
import interfaces.ComponenteGrafico;


public class PanelHorarios extends JPanel implements ComponenteGrafico {
	private static final long serialVersionUID = 1L;
	private JButton btnSeleccionar, btnLimpiar;
	private MiTablaEstatica reporteHorarios;
	
	public PanelHorarios() {
		HazInterfaz();
	}
	
	private void HazInterfaz() {
		setLayout( ( new GridBagLayout() ) );
		GridBagConstraints gbc = new GridBagConstraints();
	    gbc.fill = GridBagConstraints.BOTH;
	    gbc.anchor = GridBagConstraints.CENTER;
		gbc.insets = new Insets(pxMargen, pxMargen, pxMargen, pxMargen);
		
		String encabezadosClientes[] = { "Fecha", "Hora" };
		reporteHorarios = new MiTablaEstatica( encabezadosClientes, "Horario de la Clase" );
		
		btnSeleccionar = (JButton) TextoAcentuado( new JButton("Ingresar Horario"), colorAcento1 );
		btnLimpiar= (JButton) TextoAcentuado( new JButton("Limpiar"), colorAcento2 );
		
		JComponent comps [][] = {
			{reporteHorarios },
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
	
	public void MostrarHorario( Horario hora ) {
			if( hora == null ) return;
		reiniciar();
		String infoCliente[] = { 
				hora.mostrarFecha(),
				hora.mostrarHora()
		};
		reporteHorarios.agregaRenglon( infoCliente );
	}
	
	public JButton getBtnSeleccionar() {
		return btnSeleccionar;
	}

	public JButton getBtnLimpiar() {
		return btnLimpiar;
	}

	public void reiniciar() {
		reporteHorarios.reiniciar();
	}
	
	@Override
	public void hacerVisible() {}
}