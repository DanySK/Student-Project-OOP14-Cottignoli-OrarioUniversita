package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.util.Set;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Form in cui è presente un solo JComboBox, utilizzabile sia nella rimozione di una materia dalla lista
 * delle materie disponibili sia per la gestione delle viste.
 * 
 * @author Lorenzo Cottignoli
 *
 */
public class ListObjectForm extends AbstractForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final int PREF_HEIGHT = 100;
	
	private final JComboBox<Object> sub = new JComboBox<>();
	private final JLabel lsub = new JLabel();
	
	/**
	 * 
	 * @param v Frame principale.
	 */
	public ListObjectForm(final Frame v) {
		super(v);
		final JPanel mainPanel = new JPanel(new FlowLayout());
		mainPanel.add(sub);
		mainPanel.add(lsub);
		add(mainPanel, BorderLayout.CENTER);
	}
	
	/**
	 * Metodo per settare la lista di oggetti selezionabili e un nome rappresentativo di questi oggetti.
	 * 
	 * @param s Lista degli oggetti da aggiiungere al JComboBox.
	 * @param objectName Nome degli oggetti.
	 */
	public void setList(final Set<? extends Object> s, final String objectName) {
		sub.removeAllItems();
		lsub.setText(objectName);
		for (final Object obj : s) {
			sub.addItem(obj);
		}
		sub.setSelectedIndex(0);
		setSize(sub.getPreferredSize().width + lsub.getPreferredSize().width * 2, PREF_HEIGHT);
	}
	
	/**
	 * Metodo per recuperare l'oggetto selezionato.
	 * 
	 * @return Oggetto selezionato.
	 */
	public Object getSelectedObject() {
		return sub.getSelectedItem();
	}

}
