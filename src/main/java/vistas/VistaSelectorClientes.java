package vistas;

import java.awt.*;
import java.util.List;
import java.util.LinkedList;
import javax.swing.*;
import clientes.Cliente;
import graficos.Formato;
import graficos.JPlantilla;
import graficos.MiTablaDinamica;

public class VistaSelectorClientes extends JDialog implements Formato {
	
	private static final int anchoVent=800, altoVent=800;
	private static final long serialVersionUID = 1L;
	private static final int nCamposCliente = 6;
	
	private JButton btnConsultar, btnSeleccionar, btnLimpiar, btnCancelar;
	private JTextField camposCliente[];
	
	private MiTablaDinamica reporteClientes;
	
	private LinkedList< Integer > clienteID;
	private LinkedList< String > nombre;
	private LinkedList< String > apPat;
	private LinkedList< String > apMat;
	private LinkedList< String > correo;
	private LinkedList< Long > telefono;
	private String campos[];

	public VistaSelectorClientes() {
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
		
		camposCliente = new JTextField[nCamposCliente];
		campos = new String[nCamposCliente];
		
		setLayout( new GridBagLayout() );
		
		GridBagConstraints gbc = new GridBagConstraints();
	    gbc.fill = GridBagConstraints.BOTH;
	    gbc.anchor = GridBagConstraints.CENTER;
		gbc.insets = new Insets( pxMargen, pxMargen, pxMargen, pxMargen);
	
		btnConsultar = (JButton) Formato.TextoAcentuado( new JButton("Buscar"), colorAcento1 );
		btnSeleccionar = (JButton) Formato.TextoAcentuado( new JButton("Seleccionar"), colorAcento3 );
		btnLimpiar= (JButton) Formato.TextoAcentuado( new JButton("Limpiar"), colorAcento2 );
		btnCancelar= (JButton) Formato.TextoAcentuado( new JButton("Cancelar"), colorCancelar );
		
			for( int i=0, n = camposCliente.length; i<n; i++ ) 
				camposCliente[i] = (JTextField) Formato.TextoContenido( new JTextField() );
		
		String etiquetas[] = { "ID", "Nombre", "Apellido Paterno", "Apellido Materno", "Telefono", "Correo" };
		JComponent comps [][] = {
			{ Formato.TextoPrincipal( Formato.EtiquetaCentranda( new JLabel( "Consulta de Clientes" ) ) ) },
			{ 
				Formato.TextoSecundario( Formato.EtiquetaCentranda( new JLabel( etiquetas[0]+":" ) ) )
				, camposCliente[0],
				Formato.TextoSecundario( Formato.EtiquetaCentranda( new JLabel( etiquetas[1]+":" ) ) ) 
			    , camposCliente[1]
			},
			{ 
				Formato.TextoSecundario( Formato.EtiquetaCentranda( new JLabel( etiquetas[2]+":" ) ) ) 
				, camposCliente[2],
				Formato.TextoSecundario( Formato.EtiquetaCentranda( new JLabel( etiquetas[3]+":" ) ) ) 
				, camposCliente[3]		
			},		
			{ 		
				Formato.TextoSecundario( Formato.EtiquetaCentranda( new JLabel( etiquetas[4]+":" ) ) ) 
				, camposCliente[4]	
				, Formato.TextoSecundario( Formato.EtiquetaCentranda( new JLabel( etiquetas[5]+":" ) ) ) 
				, camposCliente[5]	
			},	
			{ btnLimpiar, btnSeleccionar },
			{ btnCancelar, btnConsultar }
		};
		
		JPlantilla panControl = new JPlantilla( comps );
		gbc.gridx = 0; gbc.gridy = 0;
		gbc.weightx = 1; gbc.weighty=1;		
		add( panControl, gbc);
		
		LinkedList< List< ? > > columnas = new LinkedList< List< ? > >();
		columnas.add( clienteID =  new LinkedList< Integer >() );
		columnas.add( nombre = new LinkedList< String >() );
		columnas.add( apPat =  new LinkedList< String >() );
		columnas.add( apMat = new LinkedList< String >() );
		columnas.add( telefono = new LinkedList< Long >() );
		columnas.add( correo = new LinkedList< String >() );
		

		reporteClientes = new MiTablaDinamica( etiquetas, columnas,"Clientes Encontrados" );
		
		gbc.gridy = 1;
		gbc.weighty=49;
		add( reporteClientes, gbc );
	}

	public void ActualizarValoresTabla( List<Cliente> listaClientes ) {
		reporteClientes.Reiniciar();
			for( Cliente cliente : listaClientes ) {
				clienteID.add( cliente.getIdCliente() );
				nombre.add( cliente.getNombre() );
				apPat.add( cliente.getApellidoPaterno() );
				apMat.add( cliente.getApellidoMaterno() );
				correo.add( cliente.getCorreo() );
				telefono.add( cliente.getTelefono() );
			}
		reporteClientes.RefrescarModelo();
	}
	
	public void LimpiarCampos() {
			for( int i=0, n = camposCliente.length; i<n; i++ )
				camposCliente[i].setText("");
		ReiniciarCaptura();
	}
	
	private void ReiniciarCaptura(){
		 reporteClientes.Reiniciar();
		 reporteClientes.RefrescarModelo();
	}
	
	public Integer RecuperarIdClienteSeleccionado( int renglon ) {
		Integer id = clienteID.get(renglon);
		campos[0] = ""+id;
		campos[1] = nombre.get(renglon);
		campos[2] = apPat.get(renglon).toString();
		campos[3] = apMat.get(renglon);
		campos[4] = ""+telefono.get(renglon);
		campos[5] = correo.get(renglon);
			for( int i=0; i<nCamposCliente; i++ )
				camposCliente[i].setText(campos[i]);
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
		return camposCliente;
	}

	public MiTablaDinamica getReporteClientes() {
		return reporteClientes;
	}

	public static int getNCamposCliente() {
		return nCamposCliente;
	}

}