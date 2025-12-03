/*
 * DOCENTE: DR.CLEMENTE GARCIA GERARDO. 	
 * ENTREGA: 24/09/25. 
 * DESCRIPCION: MI JTABLE PERSONALIZADA.
 */
package graficos;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.*;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.util.List;

public class MiTablaDinamica extends JPanel implements Formato {
    private static final long serialVersionUID = 1L;

    private JTable tabla;
    private JLabel etiqDescripcion;
    private MiModelo modeloTabla;
    private JScrollPane scrollTabla;
    private GridBagConstraints gbc;

    public MiTablaDinamica(String[] titulos, List< List<?> > datos, String descripcion) {
        HazInterfaz( titulos, datos );
        AgregaEncabezado(descripcion);
        AgregaScroll();
    }

    public MiTablaDinamica(String[] titulos, List< List<?> > datos) {
        HazInterfaz(titulos, datos);
        AgregaScroll();
    }

    private void HazInterfaz(String[] titulos, List< List<?> > datos ) {
        setLayout(new GridBagLayout());

        modeloTabla = new MiModelo( titulos, datos );
        tabla = new JTable(modeloTabla) {
            private static final long serialVersionUID = 1L;

            @Override
            public TableCellRenderer getCellRenderer(int row, int column) {
                return new MiCellRenderer();
            }
        };
        
        tabla.setFont(fontContenido);
        tabla.setForeground(colorLetraContenido);
        tabla.setBackground(colorFondoContenido);
        tabla.setRowHeight(30);
        tabla.setDefaultEditor(Object.class, null);

        JTableHeader encabezado = tabla.getTableHeader();
        encabezado.setPreferredSize(new Dimension(encabezado.getWidth(), 35));
        encabezado.setFont(fontSubtitulo);
        encabezado.setBackground( colorSecundario );
        encabezado.setForeground(colorLetraSecundario);

        DefaultTableCellRenderer headerRenderer = (DefaultTableCellRenderer) encabezado.getDefaultRenderer();
        headerRenderer.setHorizontalAlignment(SwingConstants.CENTER);

        gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(pxMargen, pxMargen, 0, pxMargen);
    }

    private void AgregaEncabezado(String descripcion) {
        etiqDescripcion = new JLabel(descripcion);
        etiqDescripcion.setFont(fontTitulo);
        etiqDescripcion.setBackground(colorTerciario);
        etiqDescripcion.setForeground(colorLetraPrimario);
        etiqDescripcion.setBorder( bordeContenedor );
        etiqDescripcion.setOpaque(true);
        etiqDescripcion.setHorizontalAlignment(SwingConstants.CENTER);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1;
        gbc.weighty = 1;
        add(etiqDescripcion, gbc);
    }

    private void AgregaScroll() {
        scrollTabla = new JScrollPane(tabla);
        scrollTabla.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollTabla.setBorder(bordeContenedor);
        scrollTabla.getViewport().setBackground(Color.WHITE);

        gbc.insets = new Insets(pxMargen, pxMargen, pxMargen, pxMargen);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 1;
        gbc.weighty = 9;
        add(scrollTabla, gbc);
    }

    public void AgregarRenglon( Object[] renglon) {
        modeloTabla.AgregarRenglon( renglon );
    }

	public void EliminarRenglon(int indice) {
		 modeloTabla.EliminarRenglon( indice );
	}
	
    public void Reiniciar() {
        modeloTabla.Limpiar();
    }

	public JTable getJTable() {
		return tabla;
	}
	
	public List<?> getColumna( int i ) {
		return modeloTabla.getColumna( i );
	}
	
	public MiModelo getModelo() { return modeloTabla; };

	public void RefrescarModelo() {
		modeloTabla.fireTableDataChanged();

	}
		
	private class MiModelo extends AbstractTableModel {
	    private static final long serialVersionUID = 1L;
		private final String[] titulos;
	    private final List< List< ? > > columnas;

	    private MiModelo( String[] titulos, List< List< ? > > columnas ) {
	        	if( columnas.size() != titulos.length ) 
	        		throw new IllegalArgumentException( "La lista proporcionada no corresponde al numero de campos" );
	    	this.titulos = titulos;
	        this.columnas = columnas;
	    }

	    @SuppressWarnings({ "rawtypes", "unchecked" })
		public void AgregarRenglon( Object[] registro ) {
		        if ( registro.length != titulos.length )
		            throw new IllegalArgumentException("Número de valores no coincide con columnas");
		        
		        for (int i = 0; i < registro.length; i++)
		            ( (List) columnas.get(i) ).add( registro[i] );
		        
	    }

	    public void EliminarRenglon( int renglon ) {
	        for( List<?> col : columnas )
	        	col.remove(renglon);
	    }
	    
	    public void Limpiar() {
	        for (List<?> col : columnas)
	            col.clear();
	        fireTableDataChanged();
	    }

		public List<?> getColumna( int index ) {
	        return columnas.get(index);
	    }
	
	//METODOS ABSTRACTOS:
	    @Override
	    public int getRowCount() {
	        return columnas.isEmpty() ? 0 : columnas.get(0).size();
	    }
	
	    @Override
	    public int getColumnCount() {
	        return titulos.length;
	    }
	
	    @Override
	    public String getColumnName(int column) {
	        return titulos[column];
	    }
	
	    @Override
	    public Object getValueAt(int rowIndex, int columnIndex) {
	        return columnas.get(columnIndex).get(rowIndex);
	    }

		public List<List<?>> getDatos() {
			return columnas;
		}
	}
	
	
    private class MiCellRenderer extends JTextPane implements TableCellRenderer {
        private static final long serialVersionUID = 1L;

        private MiCellRenderer() {
            setEditable(false);
            setOpaque(true);
            setFont(fontContenido);
            setForeground(colorLetraContenido);
            setBackground(colorFondoContenido);
            setMargin( new Insets(5, 5, 5, 5) );

            StyledDocument doc = getStyledDocument();
            SimpleAttributeSet center = new SimpleAttributeSet();
            StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
            doc.setParagraphAttributes(0, doc.getLength(), center, false);
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value,
         boolean isSelected, boolean hasFocus,int row, int column) {
            setText(value == null ? "" : value.toString());

            StyledDocument doc = getStyledDocument();
            SimpleAttributeSet center = new SimpleAttributeSet();
            StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
            doc.setParagraphAttributes(0, doc.getLength(), center, false);

            setBackground( isSelected ? colorSeleccionado : colorFondoContenido );
			setForeground( isSelected ? letraSeleccionado: colorLetraContenido );
            
            int colWidth = table.getColumnModel().getColumn(column).getWidth();
            setSize(colWidth, Short.MAX_VALUE);
            int preferredHeight = getPreferredSize().height;

            if (table.getRowHeight(row) != preferredHeight) {
                table.setRowHeight(row, preferredHeight);
            }
            
            return this;
        }
    }


	public  List<List<?>> getColumnas() {
		return modeloTabla.getDatos();
	}
    
}