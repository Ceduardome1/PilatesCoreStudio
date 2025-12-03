package vistas;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import componentes.PanelCreacionClases;
import componentes.PanelInstructores;
import controladores.ControladorSelectorClientes;
import controladores.SelectorCliente;
import graficos.Formato;
import graficos.JPlantilla;
import servicios.GestionClientes;

public class VistaClases extends JDialog implements Formato {
	private static final long serialVersionUID = 1L;
	private static final int anchoVent=800, altoVent=800;

	private JButton btnGuardar, btnReiniciar, btnSalir;
	private PanelInstructores panelInstructores;
	private PanelCreacionClases panelClases;
	
		public VistaClases() {
			HazInterfaz();
		}
			
		private void HazInterfaz() {
			btnReiniciar = (JButton) Formato.TextoAcentuado( new JButton("Reiniciar Captura"), colorAcento2  );
			btnGuardar = (JButton) Formato.TextoAcentuado( new JButton("Guardar Clase"), colorAcento3  );
			btnSalir = (JButton) Formato.TextoAcentuado( new JButton("Salir"), colorCancelar  );
			
			JComponent comps [][] = {
				{ Formato.TextoPrincipal( Formato.EtiquetaCentranda( new JLabel( "Operaciones Disponibles" ) ) ) },
				{ btnSalir, btnReiniciar, btnGuardar }
			};
			
			JPlantilla panControl = new JPlantilla( comps );
			
			JPanel pan = new JPanel();
			pan.setLayout( new BoxLayout(pan, BoxLayout.Y_AXIS) );
			
			panelInstructores = new PanelInstructores();
			panelInstructores.setPreferredSize(new Dimension(anchoVent, altoVent / 2));
			pan.add( panelInstructores );

			panelClases = new PanelCreacionClases();
			panelClases.setPreferredSize(new Dimension(anchoVent, altoVent / 2));
			pan.add( panelClases );

			JScrollPane scroll = new JScrollPane();
			scroll.getViewport().add( pan );
			scroll.setPreferredSize(new Dimension(anchoVent, altoVent));
			scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

			JLabel etiq = (JLabel) Formato.TextoAcentuado( Formato.EtiquetaCentranda( new JLabel("Modúlo de Reservaciones") ), colorAcento4 );	
			add( etiq, BorderLayout.NORTH );
			add(scroll, BorderLayout.CENTER);
			add(panControl, BorderLayout.SOUTH);
		}
		
	    public void HazVentana() {
			setSize( anchoVent, altoVent );
			setLocationRelativeTo(null);
			setResizable( true ); 
			setDefaultCloseOperation( DISPOSE_ON_CLOSE );
			setModal( true );
			setVisible( true );
	    }
	    
	    public void ReiniciarInterfaz() {
	    	panelInstructores.ReiniciarCaptura();
	    	panelClases.ReiniciarCaptura();
	    }
	
	    public void abrirSelectorClientes( SelectorCliente listener, GestionClientes servicioClientes ) {
	        VistaSelectorClientes v = new VistaSelectorClientes();
	        new ControladorSelectorClientes( servicioClientes, v, listener );
	    }
	    
	    public JButton getBtnReservar() {
			return btnGuardar;
		}

		public JButton getBtnLimpiar() {
			return btnReiniciar;
		}

		public JButton getBtnSalir() {
			return btnSalir;
		}

		public PanelInstructores getPanelInstructores() {
			return panelInstructores;
		}

		public PanelCreacionClases getPanelClases() {
			return panelClases;
		}

}