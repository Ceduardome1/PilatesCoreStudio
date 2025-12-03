package acceso;

import java.awt.event.*;
import javax.swing.*;

import extras.Rutinas;
import graficos.Formato;
import graficos.JPlantilla;

public class AppAcceso extends JFrame implements Formato, ActionListener {
	private static final long serialVersionUID = 1L;
	private static final int anchoVent=500, altoVent=200;
	private static final String usuarioDefault = "";

	private JButton btnConectar, btnConfig;
	private JTextField campoUser;

	private JPasswordField campoPass;
	
		private AppAcceso() {
			super( "Autenticación" );
			HazInterfaz();
			HazEscuchas();
		}
		
		private void HazInterfaz() {
			campoUser = ( JTextField ) Formato.TextoContenido( new JTextField() );
			campoPass = ( JPasswordField ) Formato.TextoContenido( new JPasswordField() );
			campoPass.setEchoChar('*');
				
	    	btnConfig = (JButton) Formato.TextoAcentuado( new JButton("Salir") );
			btnConectar = (JButton) Formato.TextoAcentuado( new JButton("Ingresar") );
		
			JComponent comps [][] = {
				{ Formato.TextoPrincipal( Formato.EtiquetaCentranda( new JLabel( "Pilates Core Strong" ) ) ) },
				{ Formato.TextoSecundario( Formato.EtiquetaCentranda( new JLabel( "Usuario:" ) ) ), campoUser },
				{ Formato.TextoSecundario( Formato.EtiquetaCentranda( new JLabel( "Contraseña:" ) ) ), campoPass },
				{  btnConfig, btnConectar }
			};
			add( new JPlantilla( comps) );
			Limpiar();
			
			setSize( anchoVent, altoVent );
			setLocationRelativeTo(null);
			setResizable( true ); 
			setDefaultCloseOperation( EXIT_ON_CLOSE );
			setVisible( true );
		}
		
	    private void HazEscuchas() {
	    	btnConfig.addActionListener( this );
	    	btnConectar.addActionListener( this );
		}

		public void actionPerformed(ActionEvent e) {

				if( e.getSource() == btnConectar ) {
					Conectar();
					return;
				}
				
				if( e.getSource() == btnConfig) {
					setVisible( false );
					
					
					
					setVisible( true );
					return;
				}
				
		}

		private void Conectar() {
			String user = campoUser.getText(); 
			String pass = String.copyValueOf( campoPass.getPassword() );

				if( user.isBlank() || pass.isBlank() ) {
					Rutinas.MensajeError( "No se aceptan campos vacíos." );
					return;
				}
				   

			Limpiar();
		}
		
		private void Limpiar() {
			campoUser.setText(usuarioDefault);
			campoPass.setText("");
		}

		public static void main( String args[] ) {
			new AppAcceso();
		}
		
}