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

import componentesGraficos.JPlantilla;
import componentesGraficos.PanelHorarios;
import componentesGraficos.PanelInstructores;
import controladoresGraficos.ControladorSelectorHorarios;
import controladoresGraficos.ControladorSelectorInstructores;
import interfaces.ComponenteGrafico;
import interfaces.SelectorHorario;
import interfaces.SelectorInstructor;
import interfaces.ServicioBusquedaInstructores;

public class VistaClases extends JDialog implements ComponenteGrafico {
	private static final long serialVersionUID = 1L;
	private static final int anchoVent=600, altoVent=600;

	private JButton btnGuardar, btnReiniciar, btnSalir;
	private PanelInstructores panelInstructores;
	private PanelHorarios panelHorarios;
	
		public VistaClases() {
			HazInterfaz();
		}
			
		private void HazInterfaz() {
			btnReiniciar = (JButton) TextoAcentuado( new JButton("Reiniciar Captura"), colorAcento2  );
			btnGuardar = (JButton) TextoAcentuado( new JButton("Guardar Clase"), colorAcento3  );
			btnSalir = (JButton) TextoAcentuado( new JButton("Salir"), colorCancelar  );
			
			JComponent comps [][] = {
				{ TextoPrincipal( EtiquetaCentranda( new JLabel( "Operaciones Disponibles" ) ) ) },
				{ btnSalir, btnReiniciar, btnGuardar }
			};
			
			JPlantilla panControl = new JPlantilla( comps );
			
			JPanel pan = new JPanel();
			pan.setLayout( new BoxLayout(pan, BoxLayout.Y_AXIS) );
			
			panelHorarios = new PanelHorarios();
			panelHorarios.setPreferredSize(new Dimension(anchoVent, altoVent / 2));
			pan.add( panelHorarios );
			
			panelInstructores = new PanelInstructores();
			panelInstructores.setPreferredSize(new Dimension(anchoVent, altoVent / 2));
			pan.add( panelInstructores );

			JScrollPane scroll = new JScrollPane();
			scroll.getViewport().add( pan );
			scroll.setPreferredSize(new Dimension(anchoVent, altoVent));
			scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

			JLabel etiq = (JLabel) TextoAcentuado( EtiquetaCentranda( new JLabel("Modúlo de Clases") ), colorAcento4 );	
			add( etiq, BorderLayout.NORTH );
			add(scroll, BorderLayout.CENTER);
			add(panControl, BorderLayout.SOUTH);
		}
		
	    public void hacerVisible() {
			setSize( anchoVent, altoVent );
			setLocationRelativeTo(null);
			setResizable( true ); 
			setDefaultCloseOperation( DISPOSE_ON_CLOSE );
			setModal( true );
			setVisible( true );
	    }
	    
	    public void reiniciar() {
	    	panelInstructores.reiniciar();
	    	panelHorarios.reiniciar();
	    }
	
	    public void abrirSelectorHorario( SelectorHorario listener ) {
	    	setVisible(false);
	    	VistaSelectorHorarios v = new VistaSelectorHorarios();
	        new ControladorSelectorHorarios( v, listener );
	        setVisible(true);
	    }
	    
	    public void abrirSelectorInstructor( ServicioBusquedaInstructores servicioInstructores, SelectorInstructor listener ) {
	    	setVisible(false);
	    	VistaSelectorInstructores v = new VistaSelectorInstructores();
	        new ControladorSelectorInstructores( v, servicioInstructores, listener );
	        setVisible(true);
		}
	
	    public JButton getBtnGuardar() {
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

		public PanelHorarios getPanelHorarios() {
			return panelHorarios;
		}

}