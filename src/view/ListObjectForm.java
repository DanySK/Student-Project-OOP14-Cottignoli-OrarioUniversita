package view;

import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.GridLayout;
import java.util.Set;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ListObjectForm extends AbstractForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JComboBox<Object> sub = new JComboBox<>();
	private final JLabel lsub = new JLabel();
	
	protected ListObjectForm(final Frame v) {
		super(v);
		final JPanel mainPanel = new JPanel(new GridLayout(1, 2));
		mainPanel.add(sub);
		mainPanel.add(lsub);
		add(mainPanel, BorderLayout.CENTER);
		pack();
	}

	@Override
	protected void init() {
		sub.setSelectedIndex(0);
	}
	
	public void setList(final Set<? extends Object> s, final String objectName) {
		sub.removeAllItems();
		lsub.setText(objectName);
		for (final Object obj : s) {
			sub.addItem(obj);
		}
	}
	
	public Object getSelectedObject() {
		return sub.getSelectedItem();
	}

}
