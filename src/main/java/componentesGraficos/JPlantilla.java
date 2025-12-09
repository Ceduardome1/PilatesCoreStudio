/*
 * DOCENTE: DR.CLEMENTE GARCIA GERARDO. 	
 * ENTREGA: 24/09/25. 
 * DESCRIPCION: COMPONENTE PARA FORMATEAR COMPONENTES.
 */
package componentesGraficos;

import javax.swing.*;

import interfaces.ComponenteGrafico;

import java.awt.*;

public class JPlantilla extends JPanel implements ComponenteGrafico {
private static final long serialVersionUID = 1L;

	public JPlantilla( JComponent [][] mapa ) {
		HazInterfaz( mapa );
	}

	private void HazInterfaz( JComponent [][] comps ) {
		setLayout( ( new GridBagLayout() ) );
		GridBagConstraints gbc = new GridBagConstraints();
	    gbc.fill = GridBagConstraints.BOTH;
	    gbc.anchor = GridBagConstraints.CENTER;
		gbc.insets = new Insets(pxMargen, pxMargen, pxMargen, pxMargen);
		gbc.gridx = 0; gbc.gridy = 0;
		gbc.weightx = 1; gbc.weighty=1;
			for( int r=0, nRengs = comps.length; r<nRengs; r++ ) {
				JPanel pan = new JPanel( new GridBagLayout() );
					for( int c=0, nCols = comps[r].length; c<nCols; c++ ) {
						gbc.gridx = c;
						pan.add( comps[r][c], gbc );
					}
				gbc.gridx = 0; 	gbc.gridy = r;
				add( pan, gbc );
			}
	}

	@Override
	public void reiniciar() {}

	@Override
	public void hacerVisible() {}
	
}