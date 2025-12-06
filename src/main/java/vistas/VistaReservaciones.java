package vistas;

import java.awt.*;

import javax.swing.*;

import componentesGraficos.JPlantilla;
import componentesGraficos.PanelClases;
import componentesGraficos.PanelClientes;
import controladoresGraficos.ControladorSelectorClases;
import controladoresGraficos.ControladorSelectorClientes;
import interfaces.Formato;
import interfaces.SelectorClase;
import interfaces.SelectorCliente;
import servicios.ServicioClases;
import servicios.ServicioClientes;
import servicios.ServicioInstructores;

public class VistaReservaciones extends JDialog implements Formato {
	private static final long serialVersionUID = 1L;
	private static final int anchoVent=800, altoVent=800;

	private JButton btnReservar, btnLimpiar, btnSalir;
	private PanelClientes panelClientes;
	private PanelClases panelClases;
	
		public VistaReservaciones() {
			HazInterfaz();
		}
			
		private void HazInterfaz() {
			btnLimpiar = (JButton) Formato.TextoAcentuado( new JButton("Limpiar Todo"), colorAcento2  );
			btnReservar = (JButton) Formato.TextoAcentuado( new JButton("Reservar"), colorAcento3  );
			btnSalir = (JButton) Formato.TextoAcentuado( new JButton("Salir"), colorCancelar  );
			
			JComponent comps [][] = {
				{ Formato.TextoPrincipal( Formato.EtiquetaCentranda( new JLabel( "Operaciones Disponibles" ) ) ) },
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
	    
	    public void reiniciarInterfaz() {
	    	panelClientes.reiniciarInterfaz();
	    	panelClases.reiniciarInterfaz();
	    }
	
	    public void abrirSelectorClientes( SelectorCliente listener, ServicioClientes servicioClientes ) {
	        setVisible(false);
	    	VistaSelectorClientes v = new VistaSelectorClientes();
	        new ControladorSelectorClientes( servicioClientes, v, listener );
	        setVisible(true);
	    }
	    

		public void abrirSelectorClases( SelectorClase listener, ServicioClases servicioClases,
		ServicioInstructores servicioInstructores) {
			setVisible(false);
			VistaSelectorClases v = new VistaSelectorClases();
    		new ControladorSelectorClases(servicioClases, servicioInstructores, v, listener);
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