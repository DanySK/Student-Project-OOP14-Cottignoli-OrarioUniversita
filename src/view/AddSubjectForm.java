package view;

import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.GridLayout;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import model.SubjectType;

/**
 * Form per l'inserimento dei dati necessari per creare una nuova materia.
 * 
 * @author Lorenzo Cottignoli
 *
 */
public class AddSubjectForm extends AbstractForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JTextField sub = new JTextField(20);
	private final JTextField teach = new JTextField(20);
	private final JComboBox<SubjectType> type = new JComboBox<>(SubjectType.values());

	/**
	 * 
	 * @param v Frame principale.
	 */
	public AddSubjectForm(final Frame v) {
		super(v);
		final JPanel mainPanel = new JPanel(new GridLayout(3, 2));
		mainPanel.add(sub);
		mainPanel.add(new JLabel("Subject Name"));
		mainPanel.add(teach);
		mainPanel.add(new JLabel("Teacher Name"));
		mainPanel.add(type);
		mainPanel.add(new JLabel("Subject Type"));
		getContentPane().add(mainPanel, BorderLayout.CENTER);
		pack();
	}
	
	@Override
	public void setVisible(final boolean b) {
		super.setVisible(b);
		if (b) {
			sub.setText("");
			teach.setText("");
			type.setSelectedIndex(0);
		}
	}
	
	/**
	 * Metodo per recuperare il nome della nuova materia.
	 * 
	 * @return Nome della materia.
	 */
	public String getSubName() {
		return sub.getText();
	}
	
	/**
	 * Metodo per recuperare il nome del professore che tiene la materia.
	 * 
	 * @return Nome del professore.
	 */
	public String getTeachName() {
		return teach.getText();
	}
	
	/**
	 * Metodo per recuperare la tipologia della materia.
	 * 
	 * @return Tipo di materia.
	 */
	public SubjectType getSubType() {
		return (SubjectType) type.getSelectedItem();
	}
}
