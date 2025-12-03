package graficos;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class PanelLinea extends JPanel implements Formato {
    private static final long serialVersionUID = 1L;
		private JTextArea textArea;
        public PanelLinea(JTextArea textArea) {
            this.textArea = textArea;
            setBackground( colorSecundario );
            setPreferredSize(new Dimension(40, 0));  
            textArea.getDocument().addDocumentListener(new DocumentListener() {
                @Override
                public void insertUpdate(DocumentEvent e) {
                    repaint();
                }

                @Override
                public void removeUpdate(DocumentEvent e) {
                    repaint();
                }

                @Override
                public void changedUpdate(DocumentEvent e) {
                    repaint();
                }
            });
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;
            g2d.setColor( colorLetraSecundario); 
            g2d.setFont( fontSubtitulo );
            
 		   int lineHeight = g2d.getFontMetrics().getHeight();
           int lineCount = textArea.getLineCount();
           int y = lineHeight;
            
	           for (int i = 0; i <= lineCount; i++) {
	        	   g2d.drawString(String.valueOf(i), 5, y);
	        	   y += lineHeight;
	           }
        }
    }
