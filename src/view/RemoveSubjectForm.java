package view;

import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.GridLayout;
import java.util.Set;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.ISubject;

public class RemoveSubjectForm extends AbstractForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JComboBox<ISubject> sub = new JComboBox<>();
	private final JLabel lsub = new JLabel("Subject");
	
	protected RemoveSubjectForm(final Frame v) {
		super(v);
		final JPanel mainPanel = new JPanel(new GridLayout(1, 2));
		mainPanel.add(sub);
		mainPanel.add(lsub);
		add(mainPanel, BorderLayout.CENTER);
		pack();
	}

	@Override
	protected void init() {
		sub.setSelectedIndex(-1);
	}
	
	public void setList(final Set<ISubject> s) {
		sub.removeAllItems();
		for (final ISubject su : s) {
			sub.addItem(su);
		}
	}
	
	public ISubject getRemovedSubject() {
		return (ISubject) sub.getSelectedItem();
	}

}
