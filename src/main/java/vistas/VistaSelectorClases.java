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
import controladoresGraficos.ControladorSelectorHorarios;
import controladoresGraficos.ControladorSelectorInstructores;
import dominio.Clase;
import dominio.Horario;
import interfaces.ComponenteGrafico;
import interfaces.SelectorHorario;
import interfaces.SelectorInstructor;
import interfaces.ServicioBusquedaInstructores;

public class VistaSelectorClases extends JDialog implements ComponenteGrafico {
	
	private static final int anchoVent=800, altoVent=800;
	private static final long serialVersionUID = 1L;
	private static final int nCamposCliente = 6;
	
	private JLabel campoClase;
	private JButton btnConsultar, btnSeleccionar, btnLimpiar, btnCancelar;

	private PanelInstructores panelInstructores;
	private PanelHorarios panelHorarios;
	private MiTablaDinamica reporteClases;
	
	public MiTablaDinamica getReporteClases() {
		return reporteClases;
	}

	private LinkedList< Integer > colClaseID;
	private LinkedList< String > colFecha;
	private LinkedList< String > colHora;
	private LinkedList< String > colInstructor;

	public VistaSelectorClases() {
		HazInterfaz();
	}

    public void hacerVisible() {
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
	
		btnConsultar = (JButton) ComponenteGrafico.TextoAcentuado( new JButton("Buscar"), colorAcento1 );
		btnSeleccionar = (JButton) ComponenteGrafico.TextoAcentuado( new JButton("Seleccionar"), colorAcento3 );
		btnLimpiar= (JButton) ComponenteGrafico.TextoAcentuado( new JButton("Limpiar"), colorAcento2 );
		btnCancelar= (JButton) ComponenteGrafico.TextoAcentuado( new JButton("Cancelar"), colorCancelar );
		
		campoClase = (JLabel) ComponenteGrafico.TextoContenido( new JLabel() );
		
		panelInstructores = new PanelInstructores();
		panelHorarios = new PanelHorarios();
		
		JComponent comps [][] = {
			{ panelHorarios, panelInstructores },
		};
		
		JComponent comps1 [][] = {
				{ 
					ComponenteGrafico.TextoSecundario( ComponenteGrafico.EtiquetaCentranda( new JLabel( "ID Clase Seleccionada:" ) ) ) 
					, campoClase, btnSeleccionar 
				},
			{ btnCancelar, btnLimpiar, btnConsultar }
		};
		
		JLabel etiq = (JLabel) ComponenteGrafico.TextoPrincipal( ComponenteGrafico.EtiquetaCentranda( new JLabel( "Filtrar Clases Disponibles" ) ) ); 
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
					
		reporteClases.reiniciar();

			if( listaClases == null ) return;

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
		ComponenteGrafico.CambiarTextoEtiqueta( campoClase, "" );
		reiniciar();
	}
	
	@Override
	public void reiniciar(){
		 ComponenteGrafico.CambiarTextoEtiqueta( campoClase, "" );
		 panelInstructores.reiniciar();
		 panelHorarios.reiniciar();
		 reporteClases.reiniciar();
		 reporteClases.RefrescarModelo();
	}
	
	public Integer RecuperarIdClienteSeleccionado( int renglon ) {
		Integer id = colClaseID.get(renglon);
		ComponenteGrafico.CambiarTextoEtiqueta( campoClase, ""+id );
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
	
	public void abrirSelectorInstructor( ServicioBusquedaInstructores servicioInstructores,
	SelectorInstructor listener ) {
		 	setVisible(false);
	    	VistaSelectorInstructores v = new VistaSelectorInstructores();
	        new ControladorSelectorInstructores( v, servicioInstructores, listener );
	        setVisible(true);
	}

	public void abrirSelectorHorario( SelectorHorario listener ) {
		setVisible(false);
    	VistaSelectorHorarios v = new VistaSelectorHorarios();
        new ControladorSelectorHorarios( v, listener );
        setVisible(true);
	}
	
}