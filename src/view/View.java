package view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableModel;

import controller.IController;
import model.Model;

public class View extends JFrame implements IView{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final String WINDOW_TITLE = "University Time";
	protected static final String[] PROPS = new String[] { "9-10", "10-11", "11-12", "12-13", "13-14", "14-15", "15-16", "16-17", "17-18" };
	private static final Object[][] INIT_DATA = new Object[][] {};
	private final DefaultTableModel tm = new DefaultTableModel(INIT_DATA, PROPS);
	private final JButton bAdd = new JButton("Add");
	private final JButton bRemove = new JButton("Remove");
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private final JRadioButton rdbtnFirst = new JRadioButton("Primo Semestre");
	private final JRadioButton rdbtnSecond = new JRadioButton("Secondo Semestre");

	private final JMenuBar menuBar = new JMenuBar();
	private final JMenu menuFile = new JMenu("File");
	private final JMenu menuModifica = new JMenu("Modifica");
	private final JMenu menuViste = new JMenu("Viste");
	private final JMenuItem itemSave = new JMenuItem("save");
	private final JMenuItem itemLoad = new JMenuItem("load");
	private final JMenuItem itemExit = new JMenuItem("exit");
	private final JMenuItem itemBack = new JMenuItem("Annullare ultima modifica");
	private final JMenuItem itemNext = new JMenuItem("Ripristinare ultima modifica");
	private final JMenuItem itemAddSub = new JMenuItem("Add new subjcet");
	private final JMenuItem itemRemoveSub = new JMenuItem("Remove subjcet from list");
	private final ButtonGroup btngrp = new ButtonGroup();
	private final JRadioButtonMenuItem itemViewTot = new JRadioButtonMenuItem("Totale");
	private final JRadioButtonMenuItem itemViewYear = new JRadioButtonMenuItem("Anno");
	
	private final AddSubjectForm fAddSub = new AddSubjectForm(this);
	private final RemoveSubjectForm fRemoveSub = new RemoveSubjectForm(this);
	private final AddForm fAdd = new AddForm(this);
	private final RemoveForm fRemove = new RemoveForm(this);
	
	private IController controller;
	
	public View() {
		super(WINDOW_TITLE);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		setResizable(false);
		buildLayout();
		setHandlers();
		pack();
		setLocationByPlatform(true);
		setVisible(true);
	}
	
	@Override
	public void setController(final IController ctrl) {
		this.controller = ctrl;
	}
	
	private void buildLayout() {
		this.setJMenuBar(menuBar);
		menuBar.add(menuFile);
		menuBar.add(menuModifica);
		menuBar.add(menuViste);
		menuFile.add(itemSave);
		menuFile.add(itemLoad);
		menuFile.addSeparator();
		menuFile.add(itemExit);
		menuModifica.add(itemBack);
		menuModifica.add(itemNext);
		menuModifica.addSeparator();
		menuModifica.add(itemAddSub);
		menuModifica.add(itemRemoveSub);
		btngrp.add(itemViewTot);
		btngrp.add(itemViewYear);
		menuViste.add(itemViewTot);
		itemViewTot.setSelected(true);
		menuViste.add(itemViewYear);
		
		final JTable table = new JTable(tm);
		final JScrollPane scroll = new JScrollPane(table);
		table.setFillsViewportHeight(true);
		scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		this.setLayout(new BorderLayout());
		this.add(scroll, BorderLayout.WEST);
		final JPanel p = new JPanel(new GridLayout(6, 1));
		p.add(bAdd);
		p.add(bRemove);
		buttonGroup.add(rdbtnFirst);
		rdbtnFirst.setSelected(true);
		p.add(rdbtnFirst);
		buttonGroup.add(rdbtnSecond);
		p.add(rdbtnSecond);
		this.add(p, BorderLayout.EAST);
	}
	
	private void setHandlers() {
		
		this.itemExit.addActionListener(e -> {
			final int n = JOptionPane.showConfirmDialog(this, "Do you really want to quit?", "Quitting..", JOptionPane.YES_NO_OPTION);
			if (n == JOptionPane.YES_OPTION) {
				System.exit(0);
			}
		});
		
		this.itemAddSub.addActionListener(e -> {
			setEnabled(false);
			fAddSub.reinit();
			fAddSub.setVisible(true);
		});
		
		this.itemRemoveSub.addActionListener(e -> {
			setEnabled(false);
			fRemoveSub.setList(controller.getSubjectsList());
			fRemoveSub.reinit();
			fRemoveSub.setVisible(true);
		});
		
		itemSave.addActionListener(e -> {
			final String s = JOptionPane.showInputDialog(this, "Insert the name of file", "Saving..", JOptionPane.OK_CANCEL_OPTION);
			if (s != null) {
				controller.commandSave(s);			
			}
		});
		
		itemLoad.addActionListener(e -> {
			final String s = JOptionPane.showInputDialog(this, "Insert the name of file", "Loading..", JOptionPane.OK_CANCEL_OPTION);
			if (s != null) {
				controller.commandLoad(s);
			}
		});
		
		itemBack.addActionListener(e -> {
			controller.commandBack();
		});
		
		itemNext.addActionListener(e -> {
			controller.commandNext();
		});
		
		itemViewTot.addActionListener(e -> {
			//controller.viewTot
			controller.test();
		});
		
		itemViewYear.addActionListener(e -> {
			//controller.viewYear
		});
		
		bAdd.addActionListener(e -> {
			setEnabled(false);
			fAdd.setList(controller.getSubjectsList());
			fAdd.reinit();
			fAdd.setVisible(true);
		});
		
		bRemove.addActionListener(e -> {
			setEnabled(false);
			fRemove.reinit();
			fRemove.setVisible(true);
		});
		
		fAddSub.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentHidden(final ComponentEvent e) {
				if (fAddSub.isOk()) {
					controller.commandAddSubject(fAddSub.getSubName(), fAddSub.getTeachName(), fAddSub.getSubType());
				}
			}
		});
		
		fRemoveSub.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentHidden(final ComponentEvent e) {
				if (fRemoveSub.isOk()) {
					controller.commandRemoveSubject(fRemoveSub.getRemovedSubject()); 
				}
			}
		});
		
		fAdd.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentHidden(final ComponentEvent e) {
				if (fAdd.isOk()) {
					controller.commandAdd(getSelectedSem(), fAdd.getDay(), fAdd.getSubject(), fAdd.getClassroom(), fAdd.getHour(), fAdd.getNumberHours());
				}
			}
		});
		
		fRemove.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentHidden(final ComponentEvent e) {
				if (fRemove.isOk()) {
					controller.commandRemove(getSelectedSem(), fRemove.getDay(), fRemove.getClassroom(), fRemove.getHour(), 1);
				}
			}
		});
	}
	
	private int getSelectedSem() {
		return rdbtnFirst.isSelected() ? Model.FIRST_SEM : Model.SEC_SEM;
	}
	
	@Override
	public void commandFailed(final String message) {
		JOptionPane.showMessageDialog(this, message, "An error occurred", JOptionPane.ERROR_MESSAGE);
	}
	
/*	private void test() {
		//tm.addRow(new Object[]{ "prova1", "prova2", "prova3", "prova4"});
		System.out.println("Materie presenti nel set: ");
		for (final Subject s : u.getSubjects()) {
			System.out.println(s.toString());
		}
		System.out.println(u.getSubject(getSelectedSem(), Days.MONDAY, Classrooms.A, 12).toString());
		System.out.println(u.getSubject(getSelectedSem(), Days.FRIDAY, Classrooms.B, 9).toString());
	}*/
}
