package view;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextPane;
import javax.swing.table.DefaultTableCellRenderer;

import model.SubjectType;

/**
 * Redifinizione della classe {@link DefaultTableCellRenderer} in modo da permettere di modificare la larghezza e l'altezza delle celle
 * e di ricolorarle in base al loro contenuto.
 * 
 * @author Lorenzo Cottignoli
 *
 */
public class MyRenderer extends DefaultTableCellRenderer {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5194556966515647561L;
	
	private final JTextPane comp;
	private final JScrollPane scroll;

	/**
	 * Costruttore che inizializza i vari campi di questa classe.
	 */
	public MyRenderer() {
		super();
		comp = new JTextPane();
        scroll = new JScrollPane(comp);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        scroll.setBorder(null);
	}
	
	@Override
	public Component getTableCellRendererComponent(final JTable table,
			final Object value, final boolean isSelected, final boolean hasFocus, final int row, final int column) {
		
		boolean colored = false;
		SubjectType st;
		for (int i = 0; i < SubjectType.values().length && !colored; i++) {
			st = SubjectType.values()[i];
			if (value.toString().contains(st.toString())) {
				colored = true;
				comp.setBackground(st.getColor());
			}
		}
		
		if (!colored) {
			comp.setBackground(Color.WHITE);
		}
		
		comp.setText(value.toString());
		final int h = scroll.getPreferredSize().height;
		final int w = scroll.getPreferredSize().width;
		
		if (table.getColumnModel().getColumn(column).getWidth() < w) {
			table.getColumnModel().getColumn(column).setPreferredWidth(w + Character.SIZE);
		}
		
		if (table.getRowHeight(row) < h) { 
			table.setRowHeight(row, h);
		}
  
		return scroll;
	}
		
}
