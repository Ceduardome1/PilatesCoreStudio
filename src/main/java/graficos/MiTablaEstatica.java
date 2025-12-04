package graficos;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.*;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

public class MiTablaEstatica extends JPanel implements Formato {
    private static final long serialVersionUID = 1L;

    private JTable tabla;
    private JLabel etiqDescripcion;
    private DefaultTableModel modeloTabla;
    private JScrollPane scrollTabla;
    private GridBagConstraints gbc;

    public MiTablaEstatica(String[] titulos, String descripcion, Color colorEncabezado, Color colorFondo) {
        HazInterfaz(titulos, colorFondo, colorEncabezado);
        AgregaEncabezado(descripcion);
        AgregaScroll();
    }

    public MiTablaEstatica(String[] titulos, String descripcion ) {
        HazInterfaz(titulos, colorFondoContenido, colorTerciario );
        AgregaEncabezado(descripcion);
        AgregaScroll();
    }
    
    public MiTablaEstatica(String[] titulos ) {
        HazInterfaz(titulos, colorFondoContenido, colorTerciario );
        AgregaScroll();
    }

    private void HazInterfaz(String[] titulos, Color colorFondo, Color colorEncabezado) {
        setLayout(new GridBagLayout());
        setBackground(colorFondo);

        modeloTabla = new DefaultTableModel(titulos, 0);
        tabla = new JTable(modeloTabla) {
            private static final long serialVersionUID = 1L;

            @Override
            public TableCellRenderer getCellRenderer(int row, int column) {
                return new MultiLineCellRenderer();
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
        encabezado.setBackground(colorEncabezado);
        encabezado.setForeground(colorLetraTerciario);

        DefaultTableCellRenderer headerRenderer = (DefaultTableCellRenderer) encabezado.getDefaultRenderer();
        headerRenderer.setHorizontalAlignment(SwingConstants.CENTER);

        gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(pxMargen, pxMargen, 0, pxMargen);
    }

    private void AgregaEncabezado(String descripcion) {
        etiqDescripcion = new JLabel(descripcion);
        etiqDescripcion.setFont(fontSubtitulo);
        etiqDescripcion.setBackground(colorSecundario);
        etiqDescripcion.setForeground(colorLetraSecundario);
        etiqDescripcion.setBorder(bordeContenedor);
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

    public void agregaRenglon(String[] renglon) {
        modeloTabla.addRow(renglon);
    }
    
    @Override
    public void reiniciarInterfaz() {
        modeloTabla.setRowCount(0);
    }

    private class MultiLineCellRenderer extends JTextPane implements TableCellRenderer {
        private static final long serialVersionUID = 1L;

        public MultiLineCellRenderer() {
            setEditable(false);
            setOpaque(true);
            setFont(fontContenido);
            setForeground(colorLetraContenido);
            setBackground(colorFondoContenido);
            setMargin(new Insets(5, 5, 5, 5));

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

	public JTable getTabla() {
		return tabla;
	}
}