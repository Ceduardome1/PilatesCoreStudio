package componentes;
import java.awt.*;
import javax.swing.*;
import dominio.Horario;
import graficos.Formato;
import graficos.JPlantilla;
import graficos.MiTablaEstatica;


public class PanelHorarios extends JPanel implements Formato {
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
		
		btnSeleccionar = (JButton) Formato.TextoAcentuado( new JButton("Ingresar Horario"), colorAcento1 );
		btnLimpiar= (JButton) Formato.TextoAcentuado( new JButton("Limpiar"), colorAcento2 );
		
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
		ReiniciarCaptura();
		String infoCliente[] = { 
				hora.mostrarFecha(),
				hora.mostrarHora()
		};
		reporteHorarios.agregaRenglon( infoCliente );
	}

	public void ReiniciarCaptura() {
		reporteHorarios.reiniciarInterfaz();
	}
	
	public JButton getBtnSeleccionar() {
		return btnSeleccionar;
	}

	public JButton getBtnLimpiar() {
		return btnLimpiar;
	}

	public void reiniciarInterfaz() {
		reporteHorarios.reiniciarInterfaz();
	}
}