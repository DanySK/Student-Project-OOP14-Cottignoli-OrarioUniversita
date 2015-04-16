package view;

import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.GridLayout;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

import model.Classrooms;
import model.DailyTime;
import model.Days;
import model.IDailyTime;

public class RemoveForm extends AbstractForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JComboBox<Days> days = new JComboBox<>(Days.values());
	private final JComboBox<String> hour = new JComboBox<>();
	private final JComboBox<Classrooms> cls = new JComboBox<>(Classrooms.values());
	private final JSpinner spin = new JSpinner(new SpinnerNumberModel(1, 1, DailyTime.HOURS, 1));
	
	protected RemoveForm(final Frame v) {
		super(v);
		for (int i = IDailyTime.FIRST_HOUR; i < (IDailyTime.FIRST_HOUR + IDailyTime.HOURS); i++) {
			hour.addItem(i + "-" + (i + 1));
		}
		final JPanel mainPanel = new JPanel(new GridLayout(4, 2));
		mainPanel.add(days);
		mainPanel.add(new JLabel("Day"));
		mainPanel.add(cls);
		mainPanel.add(new JLabel("Classroom"));
		mainPanel.add(hour);
		mainPanel.add(new JLabel("hour"));
		mainPanel.add(spin);
		mainPanel.add(new JLabel("N. Hours"));
		getContentPane().add(mainPanel, BorderLayout.CENTER);
		pack();
	}

	@Override
	protected void init() {
		days.setSelectedIndex(0);
		hour.setSelectedIndex(0);
		cls.setSelectedIndex(0);
		spin.setValue(1);
	}
	
	public Days getDay() {
		return (Days) days.getSelectedItem();
	}
	
	public int getHour() {
		return DailyTime.FIRST_HOUR + hour.getSelectedIndex();
	}
	
	public Classrooms getClassroom() {
		return (Classrooms) cls.getSelectedItem();
	}
	
	public int getNumberHours() {
		return (int) spin.getValue();
	}

}
