package interfaces;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

import java.awt.*;

public interface ComponenteGrafico {

	public abstract void reiniciar();
	public abstract void hacerVisible();
	
	public static final Font fontTitulo = new Font("CodeNewRoman Nerd Font",Font.BOLD,22)
	, fontSubtitulo = new Font("CodeNewRoman Nerd Font", Font.PLAIN, 18)
	, fontContenido = new Font("CodeNewRoman Nerd Font", Font.PLAIN, 18)
	, fontBoton = new Font("CodeNewRoman Nerd Font",Font.PLAIN,20);

	public static final Color colorPrimario = new Color(0, 77, 64)//(20, 70, 110)
	, colorAcento1 = new Color(165, 130, 55)	//(0, 125, 110)
	, colorAcento2 = new Color(25, 105, 160)
	, colorAcento3 = new Color(40, 140, 80)
	, colorAcento4 = new Color(58, 15, 92)//(0, 135, 155)
	, colorCancelar = new Color(180, 50, 60)
	
	, colorSecundario = new Color(20, 70, 110)//(0, 90, 100)
	, colorTerciario = new Color(6, 74, 78)//(90, 140, 145)
	, colorFondoContenido = Color.WHITE
	, colorLetraPrimario = Color.WHITE
	, colorLetraTerciario = Color.WHITE
	, colorLetraAcentos = Color.WHITE
	, colorLetraSecundario = Color.WHITE
	, colorLetraContenido = Color.BLACK
	, colorSeleccionado =  new Color(255, 255, 180)
	, letraSeleccionado = Color.BLACK;
		
	public static final int pxMargen=2;
	public static final LineBorder bordeContenedor = new LineBorder(Color.GRAY, pxMargen);
	public static Border bordeComps = BorderFactory.createLineBorder( Color.BLACK, pxMargen )
		, bordePanel = BorderFactory.createMatteBorder( 3, 3, 0, 3, Color.BLACK )
		, bordePanelFinal = BorderFactory.createMatteBorder( 3, 3, 3, 3, Color.BLACK )
		, bordeVentana = BorderFactory.createEmptyBorder(0, 0, 0, 10);
		
		public default JLabel EtiquetaCentranda( JLabel comp ) {
		    String htmlTexto = "<html><div style='text-align: center;'>" +
		    comp.getText().replace("\n", "<br>") +
		    "</div></html>";
		    comp.setText( htmlTexto );
		    comp.setVerticalAlignment(SwingConstants.CENTER);
		    comp.setHorizontalAlignment(SwingConstants.CENTER);
			return comp;
		}
		
		public default JLabel CambiarTextoEtiqueta( JLabel comp, String txt ) {
		    String htmlTexto = "<html><div style='text-align: center;'>" +
		    txt.replace("\n", "<br>") +
		    "</div></html>";
		    comp.setText( htmlTexto );
			return comp;
		}
	
		public default JComponent TextoAcentuado( JComponent comp ) {
			return TextoAcentuado( comp, colorAcento1 );
		}
		
		public default JComponent TextoAcentuado( JComponent comp, Color color ) {
			comp.setFont( fontBoton );
			comp.setBackground(color);
			comp.setForeground(colorLetraAcentos);
			comp.setBorder( bordeComps );
			comp.setOpaque(true);
			return comp;
		}
		
		public default JComponent TextoPrincipal( JComponent comp ) {
			comp.setFont( fontTitulo );
			comp.setBackground(colorPrimario);
			comp.setForeground(colorLetraPrimario);
			comp.setBorder( bordeComps );
			comp.setOpaque(true);
			return comp;
		}
		
		public default JComponent TextoSecundario( JComponent comp ) {
			comp.setFont( fontSubtitulo );
			comp.setBackground(colorSecundario);
			comp.setForeground(colorLetraSecundario);
			comp.setBorder( bordeComps );
			comp.setOpaque(true);
			return comp;
		}
		
		public default JComponent TextoTerciario( JComponent comp ) {
			comp.setFont( fontSubtitulo );
			comp.setBackground(colorTerciario);
			comp.setForeground(colorLetraTerciario);
			comp.setBorder( bordeComps );
			comp.setOpaque(true);
			return comp;
		}
		
		public default JComponent TextoContenido( JComponent comp ) {
			comp.setFont( fontContenido );
			comp.setBackground(colorFondoContenido);
			comp.setForeground(colorLetraContenido);
			comp.setOpaque(true);
			comp.setBorder( bordeComps );
			return comp;
		}
		
	}