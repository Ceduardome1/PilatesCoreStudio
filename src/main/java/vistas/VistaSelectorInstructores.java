package vistas;

import java.awt.*;
import java.util.List;
import java.util.LinkedList;
import javax.swing.*;

import actores.Instructor;
import componentesGraficos.JPlantilla;
import componentesGraficos.MiTablaDinamica;
import interfaces.ComponenteGrafico;

public class VistaSelectorInstructores extends JDialog implements ComponenteGrafico {
	
	private static final int anchoVent=800, altoVent=800;
	private static final long serialVersionUID = 1L;
	private static final int nCamposCliente = 4;
	
	private JButton btnConsultar, btnSeleccionar, btnLimpiar, btnCancelar;
	private JTextField camposInstructor[];
	
	private MiTablaDinamica reporteInstructores;
	
	private LinkedList< Integer > colInstructorID;
	private LinkedList< String > colNombre;
	private LinkedList< String > colApPat;
	private LinkedList< String > colApMat;

	private String campos[];

	public VistaSelectorInstructores() {
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
		
		camposInstructor = new JTextField[nCamposCliente];
		campos = new String[nCamposCliente];
		
		setLayout( new GridBagLayout() );
		
		GridBagConstraints gbc = new GridBagConstraints();
	    gbc.fill = GridBagConstraints.BOTH;
	    gbc.anchor = GridBagConstraints.CENTER;
		gbc.insets = new Insets( pxMargen, pxMargen, pxMargen, pxMargen);
	
		btnConsultar = (JButton) TextoAcentuado( new JButton("Buscar"), colorAcento1 );
		btnSeleccionar = (JButton) TextoAcentuado( new JButton("Seleccionar"), colorAcento3 );
		btnLimpiar= (JButton) TextoAcentuado( new JButton("Limpiar"), colorAcento2 );
		btnCancelar= (JButton) TextoAcentuado( new JButton("Cancelar"), colorCancelar );
		
			for( int i=0, n = camposInstructor.length; i<n; i++ ) 
				camposInstructor[i] = (JTextField) TextoContenido( new JTextField() );
		
		String etiquetas[] = { "ID", "Nombre", "Apellido Paterno", "Apellido Materno" };
		JComponent comps [][] = {
			{ TextoPrincipal( EtiquetaCentranda( new JLabel( "Consulta de Instructores" ) ) ) },
			{ 
				TextoSecundario( EtiquetaCentranda( new JLabel( etiquetas[0]+":" ) ) )
				, camposInstructor[0],
				TextoSecundario( EtiquetaCentranda( new JLabel( etiquetas[1]+":" ) ) ) 
			    , camposInstructor[1]
			},
			{ 
				TextoSecundario( EtiquetaCentranda( new JLabel( etiquetas[2]+":" ) ) ) 
				, camposInstructor[2],
				TextoSecundario( EtiquetaCentranda( new JLabel( etiquetas[3]+":" ) ) ) 
				, camposInstructor[3]		
			},		
			{ btnLimpiar, btnSeleccionar },
			{ btnCancelar, btnConsultar }
		};
		
		JPlantilla panControl = new JPlantilla( comps );
		gbc.gridx = 0; gbc.gridy = 0;
		gbc.weightx = 1; gbc.weighty=1;		
		add( panControl, gbc);
		
		LinkedList< List< ? > > columnas = new LinkedList< List< ? > >();
		columnas.add( colInstructorID =  new LinkedList< Integer >() );
		columnas.add( colNombre = new LinkedList< String >() );
		columnas.add( colApPat =  new LinkedList< String >() );
		columnas.add( colApMat = new LinkedList< String >() );
		
		reporteInstructores = new MiTablaDinamica( etiquetas, columnas,"Instructores Encontrados" );
		
		gbc.gridy = 1;
		gbc.weighty=49;
		add( reporteInstructores, gbc );
	}

	public void ActualizarValoresTabla( List<Instructor> listaInstructores ) {
		reporteInstructores.reiniciar();
			if( listaInstructores == null ) return;
			for( Instructor instructor : listaInstructores ) {
				colInstructorID.add( instructor.getIdPersonal() );
				colNombre.add( instructor.getNombre() );
				colApPat.add( instructor.getApellidoPat() );
				colApMat.add( instructor.getApellidoMat() );
			}
		reporteInstructores.RefrescarModelo();
	}
	
	public void LimpiarCampos() {
			for( int i=0, n = camposInstructor.length; i<n; i++ )
				camposInstructor[i].setText("");
		reiniciar();
	}
	
	@Override
	public void reiniciar(){
		 reporteInstructores.reiniciar();
		 reporteInstructores.RefrescarModelo();
	}
	
	public Integer RecuperarIdClienteSeleccionado( int renglon ) {
		Integer id = colInstructorID.get(renglon);
		campos[0] = ""+id;
		campos[1] = colNombre.get(renglon);
		campos[2] = colApPat.get(renglon).toString();
		campos[3] = colApMat.get(renglon);
			for( int i=0; i<nCamposCliente; i++ )
				camposInstructor[i].setText(campos[i]);
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

	public JTextField[] getCamposCliente() {
		return camposInstructor;
	}

	public MiTablaDinamica getReporteClientes() {
		return reporteInstructores;
	}

	public static int getNCamposCliente() {
		return nCamposCliente;
	}

}