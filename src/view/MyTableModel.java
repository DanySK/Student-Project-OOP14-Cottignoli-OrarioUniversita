package view;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

/**
 * Classe che estende {@link AbstractTableModel} in modo da gestire il modello di una tabella tramite una {@link List}.
 * 
 * @author Lorenzo Cottignoli
 *
 */
public class MyTableModel extends AbstractTableModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final int COL_NUM = 10;
	
	private List<Object> list = new ArrayList<>();
	
	@Override
	public int getRowCount() {
		return list.size() / COL_NUM;
	}

	@Override
	public int getColumnCount() {
		return COL_NUM;
	}

	@Override
	public Object getValueAt(final int rowIndex, final int columnIndex) {
		return list.get(COL_NUM * rowIndex + columnIndex);
	}
	
	@Override
	public boolean isCellEditable(final int row, final int col) {
		return false;
	}
	
	/**
	 * Metodo per settare come modello della tabella la {@link List} passata come parametro.
	 * 
	 * @param l Nuovo modello della tabella.
	 */
	public void setModel(final List<Object> l) {
		if (l == null) {
			throw new IllegalArgumentException("The TableModel can't be null!");
		}
		list = l;
		fireTableDataChanged();
	}
	
	
}
