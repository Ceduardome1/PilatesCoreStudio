package vistas;

import javax.swing.*;

import controladores.ControladorNavegacion;
import dominio.Direccion;
import dominio.Sucursal;
import graficos.Formato;
import graficos.JPlantilla;
import personal.*;

public class VistaNavegacion extends JFrame implements Formato {

	private static final long serialVersionUID = 1L;
	private static final int anchoVent=500, altoVent=200;
	
	private JComboBox< Permiso > cmbOperaciones;
	private JButton btnIngresar, btnVolver;
	
	private Personal personal;
	
	VistaNavegacion( Personal personal ){
		super( "Operaciones" );
		this.personal = personal;
		HazInterfaz();
	}
	
	@SuppressWarnings("unchecked")
	private void HazInterfaz() {
		
		cmbOperaciones = ( JComboBox<Permiso> ) 
				( Formato.TextoContenido( new JComboBox< Permiso >( personal.getPermisos() ) ) );

		btnIngresar = (JButton) Formato.TextoAcentuado( new JButton("Ingresar") );
		btnVolver = (JButton) Formato.TextoAcentuado( new JButton("Volver") );
		
		String puesto = personal.getTipoPesonal().toString();
		
		JComponent comps [][] = {
			{ Formato.TextoPrincipal( Formato.EtiquetaCentranda( new JLabel( "Pilates Core Strong" ) ) ) },
			{ 
				Formato.TextoSecundario( Formato.EtiquetaCentranda(  new JLabel( puesto+": " ) ) ),
				Formato.TextoContenido( Formato.EtiquetaCentranda(  new JLabel( personal.toString() ) ) )
			},
			{ Formato.TextoTerciario( Formato.EtiquetaCentranda( new JLabel( "Operaciones:" ) ) ), cmbOperaciones },
			{  btnVolver, btnIngresar }
		};
		
		Limpiar();
		add( new JPlantilla( comps) );
		setSize( anchoVent, altoVent );
		setLocationRelativeTo(null);
		setResizable( false ); 
		setDefaultCloseOperation( EXIT_ON_CLOSE );
		setVisible( true );
	}
	
	public void Limpiar() {
		cmbOperaciones.setSelectedItem( null );
	}

	public JComboBox<Permiso> getCmbOperaciones() {
		return cmbOperaciones;
	}

	public JButton getBtnIngresar() {
		return btnIngresar;
	}

	public JButton getBtnVolver() {
		return btnVolver;
	}
	
	public static void main( String args[] ) {
		Direccion dir = new Direccion( 80000, ( short )219, "Centro", "Cristobal Colon" );
		Sucursal suc = new Sucursal( 1, dir );
		Recepcionista recepcionista = new Recepcionista( "MEEC040925", "César", "Meza", "Escobar", suc );
		
		new ControladorNavegacion( new VistaNavegacion( recepcionista ) );
	}

	@Override
	public void reiniciarInterfaz() {
		// TODO Auto-generated method stub
		
	}

}