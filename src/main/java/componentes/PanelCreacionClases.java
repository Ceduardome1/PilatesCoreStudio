package componentes;

import java.awt.*;
import javax.swing.*;
import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JTimeChooser;

import graficos.Formato;
import graficos.JPlantilla;


public class PanelCreacionClases extends JPanel implements Formato {
	private static final long serialVersionUID = 1L;
	private static final int anchoVent=1000, altoVent=300;
	private static final int nCamposCon = 5;
	private JTextField camposConexion[];

	private JPasswordField campoPass;
	private JButton btnSeleccionar, btnLimpiar;
	
	public PanelCreacionClases() {
		HazInterfaz();
	}
	
	private void HazInterfaz() {
		camposConexion = new JTextField[ nCamposCon ];
		
			for( int i=0; i<nCamposCon; i++ ) 
				camposConexion[i] = ( JTextField ) Formato.TextoContenido( new JTextField() );

		btnLimpiar = (JButton) Formato.TextoAcentuado( new JButton("Salir"), colorCancelar );
    	btnSeleccionar = (JButton) Formato.TextoAcentuado( new JButton("Ingresar"), colorAcento3 );

		JPanel panCtl = new JPanel( new GridBagLayout() );
		
		GridBagConstraints gbc = new GridBagConstraints();
	    gbc.fill = GridBagConstraints.BOTH;
	    gbc.anchor = GridBagConstraints.CENTER;
		gbc.insets = new Insets( pxMargen, pxMargen, pxMargen, pxMargen);
		
		gbc.gridx = 0; gbc.gridy = 0; gbc.weighty = 1;
		JLabel etiq = (JLabel) Formato.TextoPrincipal( Formato.EtiquetaCentranda( new JLabel( "Horario de la clase" ) ) )
		
		
		
		
		gbc.gridy = 0; gbc.weighty = 6;
		JComponent comps [][] = {
		    { 
				Formato.TextoSecundario( Formato.EtiquetaCentranda( new JLabel( "Fecha:" ) ) ), camposConexion[0],
				Formato.TextoSecundario( Formato.EtiquetaCentranda( new JLabel( "Hora:" ) ) ), camposConexion[1],
			}
		};
		panCtl.add( new JPlantilla( comps ), gbc );
		
		gbc.gridy = 2; gbc.weighty=4;
		panCtl.add( 
			new JPlantilla( 
				new JComponent[][] { { btnSalir, btnConectar } }
			), gbc );
	
		add( panCtl );
				
		setSize( anchoVent, altoVent );
		setLocationRelativeTo(null);
		setResizable( true ); 
		setDefaultCloseOperation( EXIT_ON_CLOSE );
		setVisible( true );
	}
	
	

	public void ReiniciarCaptura() {
		
	}
	
	public JButton getBtnSeleccionar() {
		return btnSeleccionar;
	}

	public JButton getBtnLimpiar() {
		return btnLimpiar;
	}
}
