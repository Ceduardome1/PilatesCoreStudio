package componentesGraficos;

import java.awt.*;
import javax.swing.*;

import actores.Instructor;
import dominio.Clase;
import dominio.Horario;
import dominio.Reservacion;
import dominio.Sala;
import interfaces.ComponenteGrafico;

public class ReporteReservacion extends JDialog implements ComponenteGrafico {

	private static final long serialVersionUID = 1L;
	private static final int anchoVent=800, altoVent=500;
	private MiTablaEstatica reporteReservacion;
	private MiTablaEstatica reporteClase;
	private MiTablaEstatica reporteSala;
	
	public ReporteReservacion( Reservacion reservacion ) {
		hazInterfaz( reservacion );
		hacerVisible(); 
	}
	
	
	private void hazInterfaz( Reservacion reservacion ) {
		setLayout( ( new GridBagLayout() ) );
		GridBagConstraints gbc = new GridBagConstraints();
	    gbc.fill = GridBagConstraints.BOTH;
	    gbc.anchor = GridBagConstraints.CENTER;
		gbc.insets = new Insets(pxMargen, pxMargen, pxMargen, pxMargen);
		
		String encabezadosClases[] = { "Fecha", "Hora", "Instructor" };
		reporteClase = new MiTablaEstatica( encabezadosClases, "Información de la Clase" );
		
		String encabezadosSala[] = { "ID", "Camas Disponibles" };
		reporteSala = new MiTablaEstatica( encabezadosSala, "Información de la Sala" );

		String encabezadosReservacion[] = { "ID", "Cliente", "Atendio", "Cama Asignada" };
		reporteReservacion = new MiTablaEstatica( encabezadosReservacion, "Resumen de la Reservacion" );

		JComponent comps [][] = {
			{ reporteReservacion },
			{reporteClase, reporteSala }
		};
		
		JLabel etiq = (JLabel) TextoPrincipal( EtiquetaCentranda( new JLabel("Reservacion Registrada:") ) );	
		gbc.gridy = 0; gbc.weighty=1;
		add( etiq, gbc );
		
		JPlantilla panTablas = new JPlantilla( comps );	
		gbc.gridx = 0; gbc.gridy = 1;
		gbc.weightx = 1; gbc.weighty=49;
		add( panTablas, gbc );
		
		mostrarInfoReservacion( reservacion );
	}
	
	public void mostrarInfoReservacion( Reservacion reservacion ) {
			if( reservacion == null ) return;
			
		Clase clase = reservacion.getClase();
		reiniciar();
		Horario hora = clase.getHorario();
		Sala sala = clase.getSala();
		Instructor inst = clase.getInstructor();
		
		String infoClase[] = { 
			hora.mostrarFecha(),
			hora.mostrarHora(),
			inst.toString()
		};
		reporteClase.agregaRenglon( infoClase );
		
		String infoSala[] = { 
			""+sala.getIdSala(),
			""+sala.getCamasDisponibles()+" camas"
		};
		reporteSala.agregaRenglon( infoSala );
		
		String infoRev[] = { 
			""+reservacion.getIdReservacion(),
			reservacion.getCliente().toString(),
			reservacion.getEmisor().toString(),
			""+reservacion.getIdCamaAsignada()
		};
		
		reporteReservacion.agregaRenglon( infoRev );
		
	}
	
	@Override
	public void reiniciar() {}
	
	@Override
	public void hacerVisible() {
		setSize( anchoVent, altoVent );
		setLocationRelativeTo(null);
		setResizable( true ); 
		setDefaultCloseOperation( DISPOSE_ON_CLOSE );
		setModal( true );
		setVisible( true );
	}
	
}
