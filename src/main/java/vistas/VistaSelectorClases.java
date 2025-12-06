package vistas;

import java.awt.*;
import java.util.LinkedList;
import java.util.List;
import javax.swing.*;

import actores.Instructor;
import componentesGraficos.JPlantilla;
import componentesGraficos.MiTablaDinamica;
import componentesGraficos.PanelHorarios;
import componentesGraficos.PanelInstructores;
import dominio.Clase;
import dominio.Horario;
import interfaces.Formato;

public class VistaSelectorClases extends JDialog implements Formato {
	
	private static final int anchoVent=800, altoVent=800;
	private static final long serialVersionUID = 1L;
	private static final int nCamposCliente = 6;
	
	private JLabel campoClase;
	private JButton btnConsultar, btnSeleccionar, btnLimpiar, btnCancelar;

	private PanelInstructores panelInstructores;
	private PanelHorarios panelHorarios;
	private MiTablaDinamica reporteClases;
	
	private LinkedList< Integer > colClaseID;
	private LinkedList< String > colFecha;
	private LinkedList< String > colHora;
	private LinkedList< String > colInstructor;

	public VistaSelectorClases() {
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
		
		setLayout( new GridBagLayout() );
		
		GridBagConstraints gbc = new GridBagConstraints();
	    gbc.fill = GridBagConstraints.BOTH;
	    gbc.anchor = GridBagConstraints.CENTER;
		gbc.insets = new Insets( pxMargen, pxMargen, pxMargen, pxMargen);
	
		btnConsultar = (JButton) Formato.TextoAcentuado( new JButton("Buscar"), colorAcento1 );
		btnSeleccionar = (JButton) Formato.TextoAcentuado( new JButton("Seleccionar"), colorAcento3 );
		btnLimpiar= (JButton) Formato.TextoAcentuado( new JButton("Limpiar"), colorAcento2 );
		btnCancelar= (JButton) Formato.TextoAcentuado( new JButton("Cancelar"), colorCancelar );
		
		campoClase = (JLabel) Formato.TextoContenido( new JLabel() );
		
		panelInstructores = new PanelInstructores();
		panelHorarios = new PanelHorarios();
		
		JComponent comps [][] = {
			{ panelHorarios, panelInstructores },
		};
		
		JComponent comps1 [][] = {
				{ 
					Formato.TextoSecundario( Formato.EtiquetaCentranda( new JLabel( "ID Clase Seleccionada:" ) ) ) 
					, campoClase, btnSeleccionar 
				},
			{ btnCancelar, btnLimpiar, btnConsultar }
		};
		
		JLabel etiq = (JLabel) Formato.TextoPrincipal( Formato.EtiquetaCentranda( new JLabel( "Filtrar Clases Disponibles" ) ) ); 
		gbc.gridx = 0; gbc.gridy = 0;
		gbc.weightx = 1; gbc.weighty=1;		
		add( etiq, gbc);
		
		JPlantilla panControl = new JPlantilla( comps );
		gbc.gridx = 0; gbc.gridy = 1;
		gbc.weightx = 1; gbc.weighty=8;		
		add( panControl, gbc);
	
		panControl = new JPlantilla( comps1 );
		gbc.gridx = 0; gbc.gridy = 2;
		gbc.weightx = 1; gbc.weighty=1;		
		add( panControl, gbc);
		
		LinkedList< List< ? > > columnas = new LinkedList< List< ? > >();
		columnas.add( colClaseID =  new LinkedList< Integer >() );
		columnas.add( colFecha = new LinkedList< String >() );
		columnas.add( colHora =  new LinkedList< String >() );
		columnas.add( colInstructor = new LinkedList< String >() );
		String [] titulos = { "ID", "Fecha", "Hora", "Instructor" };
		reporteClases = new MiTablaDinamica( titulos, columnas,"Clases Encontradas" );
	
		gbc.gridy = 3;
		gbc.weighty=45;
		add( reporteClases, gbc );
	}

	public void ActualizarValoresTabla( List<Clase> listaClases ) {
		reporteClases.reiniciarInterfaz();
			for( Clase clase : listaClases ) {
				colClaseID.add( clase.getIdClase() );
			Horario horario = clase.getHorario();
				colFecha.add( horario.mostrarFecha() );
				colHora.add( horario.mostrarHora() );
			Instructor instructor = clase.getInstructor();
				colInstructor.add( instructor.toString() );
			}
		reporteClases.RefrescarModelo();
	}
	
	public void LimpiarCampos() {
		Formato.CambiarTextoEtiqueta( campoClase, "" );
		reiniciarInterfaz();
	}
	
	@Override
	public void reiniciarInterfaz(){
		 reporteClases.reiniciarInterfaz();
		 reporteClases.RefrescarModelo();
	}
	
	public Integer RecuperarIdClienteSeleccionado( int renglon ) {
		Integer id = colClaseID.get(renglon);
		Formato.CambiarTextoEtiqueta( campoClase, ""+id );
		return id;
	}

	public JButton getBtnConsultar() {
		return btnConsultar;
	}

	public JButton getBtnSeleccionar() {
		return btnSeleccionar;
	}
	
	public JButton getBtnLimpiar() {
		return btnLimpiar;
	}

	public JButton getBtnCancelar() {
		return btnCancelar;
	}

	public MiTablaDinamica getReporteClientes() {
		return reporteClases;
	}

	public static int getNCamposCliente() {
		return nCamposCliente;
	}

	public PanelInstructores getPanelInstructores() {
		return panelInstructores;
	}

	public PanelHorarios getPanelHorarios() {
		return panelHorarios;
	}
	
	public static void main( String args[] ) {
		VistaSelectorClases vista = new VistaSelectorClases();
		vista.HazVentana();
	}
	
	
}
