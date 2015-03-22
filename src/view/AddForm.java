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
import model.ISubject;

public class AddForm extends AbstractForm {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JComboBox<ISubject> sub = new JComboBox<>();
	private final JComboBox<Days> days = new JComboBox<>(Days.values());
	private final JComboBox<Classrooms> cls = new JComboBox<>(Classrooms.values());
	private final JComboBox<String> hour = new JComboBox<>(View.PROPS);
	private final JSpinner spin = new JSpinner(new SpinnerNumberModel(1, 1, DailyTime.HOURS, 1));
	
	public AddForm(final Frame v) {
		super(v);
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
		pack();
	}

	@Override
	protected void init() {
		sub.setSelectedIndex(-1);
		days.setSelectedIndex(0);
		cls.setSelectedIndex(0);
		hour.setSelectedIndex(0);
		spin.setValue(1);
	}

	public void setList(final Set<ISubject> s) {
		sub.removeAllItems();
		for (final ISubject su : s) {
			sub.addItem(su);
		}
	}
	
	public ISubject getSubject() {
		return (ISubject) sub.getSelectedItem();
	}
	
	public Days getDay() {
		return (Days) days.getSelectedItem();
	}
	
	public Classrooms getClassroom() {
		return (Classrooms) cls.getSelectedItem();
	}
	
	public int getHour() {
		return DailyTime.FIRST_HOUR + hour.getSelectedIndex();
	}
	
	public int getNumberHours() {
		return (int) spin.getValue();
	}
}
