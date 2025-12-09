package vistas;

import java.awt.*;

import javax.swing.*;

import componentesGraficos.JPlantilla;
import componentesGraficos.PanelClases;
import componentesGraficos.PanelClientes;
import componentesGraficos.ReporteReservacion;
import controladoresGraficos.ControladorSelectorClases;
import controladoresGraficos.ControladorSelectorClientes;
import dominio.Reservacion;
import interfaces.ComponenteGrafico;
import interfaces.SelectorClase;
import interfaces.SelectorCliente;
import interfaces.ServicioBusquedaClases;
import interfaces.ServicioBusquedaClientes;
import interfaces.ServicioBusquedaInstructores;

public class VistaReservaciones extends JDialog implements ComponenteGrafico {
	private static final long serialVersionUID = 1L;
	private static final int anchoVent=800, altoVent=800;

	private JButton btnReservar, btnLimpiar, btnSalir;
	private PanelClientes panelClientes;
	private PanelClases panelClases;
	
		public VistaReservaciones() {
			HazInterfaz();
		}
			
		private void HazInterfaz() {
			btnLimpiar = (JButton) TextoAcentuado( new JButton("Limpiar Todo"), colorAcento2  );
			btnReservar = (JButton) TextoAcentuado( new JButton("Reservar"), colorAcento3  );
			btnSalir = (JButton) TextoAcentuado( new JButton("Salir"), colorCancelar  );
			
			JComponent comps [][] = {
				{ TextoPrincipal( EtiquetaCentranda( new JLabel( "Operaciones Disponibles" ) ) ) },
				{ btnSalir, btnLimpiar, btnReservar }
			};
			
			JPlantilla panControl = new JPlantilla( comps );
			
			JPanel pan = new JPanel();
			pan.setLayout( new BoxLayout(pan, BoxLayout.Y_AXIS) );
			
			panelClientes = new PanelClientes();
			panelClientes.setPreferredSize(new Dimension(anchoVent, altoVent / 2));
			pan.add( panelClientes );

			panelClases = new PanelClases();
			panelClases.setPreferredSize(new Dimension(anchoVent, altoVent / 2));
			pan.add( panelClases );

			JScrollPane scroll = new JScrollPane();
			scroll.getViewport().add( pan );
			scroll.setPreferredSize(new Dimension(anchoVent, altoVent));
			scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

			JLabel etiq = (JLabel) TextoAcentuado( EtiquetaCentranda( new JLabel("Modúlo de Reservaciones") ), colorAcento4 );	
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
	    	panelClientes.reiniciar();
	    	panelClases.reiniciar();
	    }
	
	    public void abrirSelectorClientes( ServicioBusquedaClientes servicioClientes, SelectorCliente listener ) {
	        setVisible(false);
	    	VistaSelectorClientes v = new VistaSelectorClientes();
	        new ControladorSelectorClientes( v, servicioClientes, listener );
	        setVisible(true);
	    }
	    
		public void abrirSelectorClases( ServicioBusquedaClases servicioClases, ServicioBusquedaInstructores servicioInstructores, SelectorClase listener ) {
			setVisible(false);
			VistaSelectorClases v = new VistaSelectorClases();
    		new ControladorSelectorClases( v, servicioClases, servicioInstructores, listener);
    		setVisible(true);
		}
		
	    public void mostrarReporte( Reservacion reservacion ) {
	    	setVisible(false);
	    	new ReporteReservacion( reservacion );
	    	setVisible(true);
	    }
    
		
	    public JButton getBtnReservar() {
			return btnReservar;
		}

		public JButton getBtnLimpiar() {
			return btnLimpiar;
		}

		public JButton getBtnSalir() {
			return btnSalir;
		}

		public PanelClientes getPanelClientes() {
			return panelClientes;
		}

		public PanelClases getPanelClases() {
			return panelClases;
		}

}