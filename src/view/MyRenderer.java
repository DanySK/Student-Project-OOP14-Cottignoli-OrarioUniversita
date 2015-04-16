package view;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

import model.SubjectType;

public class MyRenderer extends DefaultTableCellRenderer {

		/**
	 * 
	 */
	private static final long serialVersionUID = 5194556966515647561L;

		@Override
		public Component getTableCellRendererComponent(final JTable table,
				final Object value, final boolean isSelected, final boolean hasFocus, final int row, final int column) {
			
			final Component comp = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
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
			return comp;
		}
		
}
