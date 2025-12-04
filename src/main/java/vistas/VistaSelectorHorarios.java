package vistas;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.TimePicker;

import graficos.Formato;
import graficos.JPlantilla;

public class VistaSelectorHorarios extends JDialog implements Formato {
	private static final long serialVersionUID = 1L;
	private static final int anchoVent=250, altoVent=200;

	private DatePicker panelFecha;
	private TimePicker panelHora;
	private JButton btnSeleccionar, btnCancelar, btnLimpiar;

	public VistaSelectorHorarios() {
		HazInterfaz();
	}
	
    public void HazVentana() {
		setSize( anchoVent, altoVent );
		setLocationRelativeTo(null);
		setResizable( true ); 
		setDefaultCloseOperation( DISPOSE_ON_CLOSE );
		setModal( true );
		setVisible( true );
    }
    
	private void HazInterfaz() {
		btnLimpiar = (JButton) Formato.TextoAcentuado( new JButton("Limpiar"), colorAcento2 );
		btnCancelar = (JButton) Formato.TextoAcentuado( new JButton("Volver"), colorCancelar );
    	btnSeleccionar = (JButton) Formato.TextoAcentuado( new JButton("Ingresar"), colorAcento3 );

		JPanel panCtl = new JPanel( new GridBagLayout() );
		
		GridBagConstraints gbc = new GridBagConstraints();
	    gbc.fill = GridBagConstraints.BOTH;
	    gbc.anchor = GridBagConstraints.CENTER;
		gbc.insets = new Insets( pxMargen, pxMargen, pxMargen, pxMargen);
		
		
				
		panelFecha = new DatePicker();
		panelHora = new TimePicker();
		
		JComponent comps [][] = {
			{
				Formato.TextoSecundario( Formato.EtiquetaCentranda( new JLabel("Fecha:") ) )
				,panelFecha
			},
		    { 
		    	Formato.TextoSecundario( Formato.EtiquetaCentranda( new JLabel("Hora:") ) )
		    	,panelHora
			}
		};
		
		
		gbc.gridx = 0; gbc.gridy = 0; gbc.weighty = 1;
		JLabel etiq = (JLabel) Formato.TextoPrincipal( Formato.EtiquetaCentranda( new JLabel( "Horario" ) ) );
		panCtl.add( etiq, gbc );
		
		gbc.gridy = 1; gbc.weighty = 2;
		panCtl.add( new JPlantilla( comps ), gbc );
		
		gbc.gridy = 2; gbc.weighty=2;
		panCtl.add( 
			new JPlantilla( 
				new JComponent[][] { { btnCancelar, btnSeleccionar }, {btnLimpiar} }
			), gbc );
	
		add( panCtl );
	}
	
	public JButton getBtnSeleccionar() {
		return btnSeleccionar;
	}

	public JButton getBtnCancelar() {
		return btnCancelar;
	}
	
	public JButton getBtnLimpiar() {
		return btnLimpiar;
	}
	
	public DatePicker getPanelFecha() {
		return panelFecha;
	}

	public TimePicker getPanelHora() {
		return panelHora;
	}
	
	@Override
	public void reiniciarInterfaz() {
		panelFecha.clear();
		panelHora.clear();
	}
	
}