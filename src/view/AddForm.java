package view;

import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.GridLayout;
import java.util.Set;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

import model.Classrooms;
import model.DailyTime;
import model.Days;
import model.IDailyTime;
import model.ISubject;

/**
 * Form per l'inserimento dei dati necessari per aggiungere una materia nell'orario.
 * 
 * @author Lorenzo Cottignoli
 *
 */
public class AddForm extends AbstractForm {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final int PREF_HEIGHT = 200;
	
	private final JComboBox<ISubject> sub = new JComboBox<>();
	private final JComboBox<Days> days = new JComboBox<>(Days.values());
	private final JComboBox<Classrooms> cls = new JComboBox<>(Classrooms.values());
	private final JComboBox<String> hour = new JComboBox<>();
	private final JSpinner spin = new JSpinner(new SpinnerNumberModel(1, 1, DailyTime.HOURS, 1));
	
	/**
	 * 
	 * @param v Frame principale.
	 */
	public AddForm(final Frame v) {
		super(v);
		for (int i = IDailyTime.FIRST_HOUR; i < (IDailyTime.FIRST_HOUR + IDailyTime.HOURS); i++) {
			hour.addItem(i + "-" + (i + 1));
		}
		final JPanel mainPanel = new JPanel(new GridLayout(5, 2));
		mainPanel.add(sub);
		mainPanel.add(new JLabel("Subject"));
		mainPanel.add(days);
		mainPanel.add(new JLabel("Day"));
		mainPanel.add(cls);
		mainPanel.add(new JLabel("Classroom"));
		mainPanel.add(hour);
		mainPanel.add(new JLabel("Hour"));
		mainPanel.add(spin);
		mainPanel.add(new JLabel("N. Hours"));
		add(mainPanel, BorderLayout.CENTER);
	}

	/**
	 * Metodo per inserire la lista delle materie disponibili, in modo che l'untente possa selezionare
	 * quale aggiungere.
	 * 
	 * @param s Set delle materie disponibili.
	 */
	public void setList(final Set<ISubject> s) {
		sub.removeAllItems();
		for (final ISubject su : s) {
			sub.addItem(su);
		}
		
		sub.setSelectedIndex(0);
		days.setSelectedIndex(0);
		cls.setSelectedIndex(0);
		hour.setSelectedIndex(0);
		spin.setValue(1);
		setSize(sub.getPreferredSize().width * 2, PREF_HEIGHT);
	}
	
	/**
	 * Metodo per recuperare la materia selezionata.
	 * 
	 * @return Materia selezionata.
	 */
	public ISubject getSubject() {
		return (ISubject) sub.getSelectedItem();
	}
	
	/**
	 * Metodo per recuperare il giorno selezionato.
	 * 
	 * @return Giorno selezionato.
	 */
	public Days getDay() {
		return (Days) days.getSelectedItem();
	}
	
	/**
	 * Metodo per recuperare l'aula selezionata.
	 * 
	 * @return Aula selezionata.
	 */
	public Classrooms getClassroom() {
		return (Classrooms) cls.getSelectedItem();
	}
	
	/**
	 * Metodo per recuperare l'ora di inizio della materia.
	 * 
	 * @return Ora d'inizio della lezione selezionata.
	 */
	public int getHour() {
		return DailyTime.FIRST_HOUR + hour.getSelectedIndex();
	}
	
	/**
	 * Metodo per recuperare il numero di ore consecutive.
	 * 
	 * @return Ore consecutive in cui inserire la materia.
	 */
	public int getNumberHours() {
		return (int) spin.getValue();
	}
}
