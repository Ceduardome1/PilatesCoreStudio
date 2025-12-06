package componentesGraficos;

import java.awt.*;

import javax.swing.*;

import actores.Cliente;
import dominio.SaldoClases;
import interfaces.Formato;

public class PanelClientes extends JPanel implements Formato {
	private static final long serialVersionUID = 1L;
	
	private JButton btnSeleccionar, btnLimpiar;
	private MiTablaEstatica reporteCliente;
	private MiTablaEstatica reporteSaldoCliente;
	
	public PanelClientes() {
		HazInterfaz();
	}
	
	private void HazInterfaz() {
		setLayout( ( new GridBagLayout() ) );
		GridBagConstraints gbc = new GridBagConstraints();
	    gbc.fill = GridBagConstraints.BOTH;
	    gbc.anchor = GridBagConstraints.CENTER;
		gbc.insets = new Insets(pxMargen, pxMargen, pxMargen, pxMargen);
		
		String encabezadosClientes[] = { "ID", "Nombre", "Ap.Paterno", "Ap.Materno", "Telefono", "Correo" };
		reporteCliente = new MiTablaEstatica( encabezadosClientes, "Información del cliente" );
		
		String encabezadosSaldo[] = { "Clases Disponibles", "Vigencia" };
		reporteSaldoCliente = new MiTablaEstatica( encabezadosSaldo, "Saldo del cliente" );
	
		btnSeleccionar = (JButton) Formato.TextoAcentuado( new JButton("Buscar Cliente"), colorAcento1 );
		btnLimpiar= (JButton) Formato.TextoAcentuado( new JButton("Limpiar"), colorAcento2 );
		
		JComponent comps [][] = {
			{reporteCliente },
			{reporteSaldoCliente}
		};
		
		JComponent comps1 [][] = {
			{ btnLimpiar, btnSeleccionar }
		};
		
		JLabel etiq = (JLabel) Formato.TextoPrincipal( Formato.EtiquetaCentranda( new JLabel("Selector Clientes") ) );	
		gbc.gridy = 0; gbc.weighty=1;
		add( etiq, gbc );
		
		JPlantilla panTablas = new JPlantilla( comps );	
		gbc.gridx = 0; gbc.gridy = 1;
		gbc.weightx = 1; gbc.weighty=49;
		add( panTablas, gbc );
		
		JPlantilla panBtns = new JPlantilla( comps1 );	
		gbc.gridy = 2; gbc.weighty=1;
		add( panBtns, gbc );
		
	}
	
	public void MostrarCliente( Cliente cliente ) {
		reiniciarInterfaz();
		String infoCliente[] = { 
				""+cliente.getIdCliente(),
				cliente.getNombre(),
				cliente.getApellidoPaterno(),
				cliente.getApellidoMaterno(),
				""+cliente.getTelefono(),
				cliente.getCorreo()
		};
		
		reporteCliente.agregaRenglon( infoCliente );
		
		SaldoClases saldo = cliente.getSaldoClases();
		String infoSaldo[] = { 
				saldo.getClasesDisponibles() + " clases",
				saldo.getVigencia().toString()
		};
		
		reporteSaldoCliente.agregaRenglon( infoSaldo );

		
	}

	@Override
	public void reiniciarInterfaz() {
		reporteCliente.reiniciarInterfaz();
		reporteSaldoCliente.reiniciarInterfaz();
	}
	
	public JButton getBtnSeleccionar() {
		return btnSeleccionar;
	}

	public JButton getBtnLimpiar() {
		return btnLimpiar;
	}

}