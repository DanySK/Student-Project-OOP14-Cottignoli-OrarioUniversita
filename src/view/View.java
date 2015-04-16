package view;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.net.URL;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
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
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

import model.Classrooms;
import model.Days;
import model.Model;
import model.SubjectType;
import model.ViewsType;
import controller.IController;

public class View extends JFrame implements IView {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static final String SEPARATOR = System.getProperty("file.separator");
	private static final String MY_EX = "ctg";
	
	private static final String WINDOW_TITLE = "University Time";
	private static final String[] PROPS = new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10" };
	private static final Object[][] INIT_DATA = new Object[][] {};
	private final DefaultTableModel tm = new DefaultTableModel(INIT_DATA, PROPS) {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public boolean isCellEditable(final int row, final int col) {
			return false;
		}
	};
	private final JButton bAdd = new JButton("Add");
	private final JButton bRemove = new JButton("Remove");
	private final JRadioButton rdbtnFirst = new JRadioButton("Primo Semestre");
	private final JRadioButton rdbtnSecond = new JRadioButton("Secondo Semestre");

	private final JMenuItem itemNew = new JMenuItem("New", new ImageIcon(getClass().getResource("/new.gif")));
	private final JMenuItem itemLoad = new JMenuItem("Load", new ImageIcon(getClass().getResource("/load.gif")));
	private final JMenuItem itemSave = new JMenuItem("Save", new ImageIcon(getClass().getResource("/save.gif")));
	private final JMenuItem itemImportSub = new JMenuItem("Import subject list", new ImageIcon(getClass().getResource("/import.gif")));
	private final JMenuItem itemExportSub = new JMenuItem("Export subject list", new ImageIcon(getClass().getResource("/export.gif")));
	private final JMenuItem itemExit = new JMenuItem("Exit");
	private final JMenuItem itemUndo = new JMenuItem("Undo", new ImageIcon(getClass().getResource("/undo.gif")));
	private final JMenuItem itemRedo = new JMenuItem("Redo", new ImageIcon(getClass().getResource("/redo.gif")));
	private final JMenuItem itemAddSub = new JMenuItem("Add new subjcet to list");
	private final JMenuItem itemRemoveSub = new JMenuItem("Remove subjcet from list");
	private final JRadioButtonMenuItem[] itemsView = new JRadioButtonMenuItem[ViewsType.values().length];
	
	private final AddSubjectForm fAddSub = new AddSubjectForm(this);
	private final RemoveSubjectForm fRemoveSub = new RemoveSubjectForm(this);
	private final AddForm fAdd = new AddForm(this);
	private final RemoveForm fRemove = new RemoveForm(this);
	
	private IController controller;
	
	
	private final ListObjectForm prova = new ListObjectForm(this);
	
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
		
		final JMenuBar menuBar = new JMenuBar();
			
		
		setJMenuBar(menuBar);
		
		final JMenu menuFile = new JMenu("File");
		menuBar.add(menuFile);
		menuFile.add(itemNew);
		menuFile.add(itemLoad);
		menuFile.add(itemSave);
		menuFile.addSeparator();
		menuFile.add(itemImportSub);
		menuFile.add(itemExportSub);
		menuFile.addSeparator();
		menuFile.add(itemExit);
		
		final JMenu menuModify = new JMenu("Modify");
		menuBar.add(menuModify);
		menuModify.add(itemUndo);
		itemUndo.setEnabled(false);
		menuModify.add(itemRedo);
		itemRedo.setEnabled(false);
		menuModify.addSeparator();
		menuModify.add(itemAddSub);
		menuModify.add(itemRemoveSub);
		
		
		final JMenu menuViews = new JMenu("Views");
		menuBar.add(menuViews);
		final ButtonGroup btngrp = new ButtonGroup();
		for (int i = 0; i < itemsView.length; i++) {
			itemsView[i] = new JRadioButtonMenuItem(ViewsType.values()[i].getDescription());
			//itemsView[i].setActionCommand(ViewsType.values()[i].getDescription());
			menuViews.add(itemsView[i]);
			btngrp.add(itemsView[i]);
			if (ViewsType.values()[i] == ViewsType.TOT) {
				itemsView[i].setSelected(true);
			}
		}
		
		final JTable table = new JTable(tm);
		final JScrollPane scroll = new JScrollPane(table);
		table.setDefaultRenderer(Object.class, new MyRenderer());
		table.setTableHeader(null);
		table.setFillsViewportHeight(true);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);  //serve altro......ma non so cosaaaaaaaaaaaaaa!!!!
		scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		setLayout(new BorderLayout());
		add(scroll, BorderLayout.CENTER);
		
		final JPanel eastPanel = new JPanel(new GridBagLayout());
		GridBagConstraints cnst = new GridBagConstraints();
		cnst.gridy = 0;
		cnst.fill = GridBagConstraints.BOTH;
		eastPanel.add(bAdd, cnst);
		cnst.gridy++;
		eastPanel.add(bRemove, cnst);
		cnst.gridy++;
		
		final JPanel semPanel = new JPanel(new GridBagLayout());
		semPanel.setBorder(new TitledBorder("SEMESTER"));
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;
		c.gridy = 0;
		final ButtonGroup buttonGroup = new ButtonGroup();
		buttonGroup.add(rdbtnFirst);
		rdbtnFirst.setSelected(true);
		semPanel.add(rdbtnFirst, c);
		c.gridy++;
		buttonGroup.add(rdbtnSecond);
		semPanel.add(rdbtnSecond, c);
		
		eastPanel.add(semPanel, cnst);
		cnst.gridy++;

		final JPanel colorPanel = new JPanel(new GridBagLayout());
		c.gridy = 0;
		colorPanel.setBorder(new TitledBorder("TABLE LEGEND"));
		for (final SubjectType st : SubjectType.values()) {
			c.gridx = 0;
			final JLabel rect = new JLabel("   ");
			rect.setOpaque(true);
			rect.setBackground(st.getColor());
			colorPanel.add(rect, c);
			c.gridx++;
			colorPanel.add(new JLabel(st.getDescription()), c);
			c.gridy++;
		}
		eastPanel.add(colorPanel, cnst);
		
		add(eastPanel, BorderLayout.EAST);
	}
	
	private void setHandlers() {
		
		itemNew.addActionListener(e -> {
			final int n = JOptionPane.showConfirmDialog(this, "Do you wanna save before create a new model?", "Saving?", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);
			if (n == JOptionPane.YES_OPTION || n == JOptionPane.NO_OPTION) {
				if (n == JOptionPane.YES_OPTION) {
					final String returnVal = fileChooser(JFileChooser.SAVE_DIALOG);
					if (returnVal != null) {
						controller.commandSave(returnVal + "." + MY_EX);
					}
				}
				controller.commandNew();
			}
		});
		
		itemLoad.addActionListener(e -> {
			final String returnVal = fileChooser(JFileChooser.OPEN_DIALOG);
			if (returnVal != null) {
				controller.commandLoad(returnVal);
			}
		});
		
		itemSave.addActionListener(e -> {
			final String returnVal = fileChooser(JFileChooser.SAVE_DIALOG);
			if (returnVal != null) {
				controller.commandSave(returnVal + "." + MY_EX);
			}
		});
		
		itemImportSub.addActionListener(e -> {
			final String returnVal = fileChooser(JFileChooser.OPEN_DIALOG);
			if (returnVal != null) {
				controller.commandImportSubjectList(returnVal);
			}
		});
		
		itemExportSub.addActionListener(e -> {
			final String returnVal = fileChooser(JFileChooser.SAVE_DIALOG);
			if (returnVal != null) {
				controller.commandExportSubjectList(returnVal + "." + MY_EX);
			}
		});
		
		this.itemExit.addActionListener(e -> {
			final int n = JOptionPane.showConfirmDialog(this, "Do you wanna save before quit?", "Quitting..", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);
			if (n == JOptionPane.YES_OPTION) {
				final String returnVal = fileChooser(JFileChooser.SAVE_DIALOG);
				if (returnVal != null) {
					controller.commandSave(returnVal + "." + MY_EX);
					System.exit(0);
				}	
			}
			if (n == JOptionPane.NO_OPTION) {
				System.exit(0);
			}
		});
		
		
		itemUndo.addActionListener(e -> {
			controller.commandUndo();
		});
		
		itemRedo.addActionListener(e -> {
			controller.commandRedo();
		});
		
		itemAddSub.addActionListener(e -> {
			setEnabled(false);
			fAddSub.reinit();
			fAddSub.setVisible(true);
		});
		
		itemRemoveSub.addActionListener(e -> {
			if (controller.getSubjectsList().isEmpty()) {
				commandFailed("There are no subject in the list!");
			} else {
				setEnabled(false);
				fRemoveSub.setList(controller.getSubjectsList());
				fRemoveSub.reinit();
				fRemoveSub.setVisible(true);
			}
		});
		
		//provaaaaaaaaaaaaaaaa
		itemsView[0].addActionListener(e -> {
			controller.setViews(null);
		});
		
		itemsView[1].addActionListener(e -> {
			setEnabled(false);
			prova.setList(controller.getTeachersList(), "Teacher");
			prova.reinit();
			prova.setVisible(true);
		});
		
		itemsView[2].addActionListener(e -> {
			setEnabled(false);
			prova.setList(controller.getSubjectsList(), "Subject");
			prova.reinit();
			prova.setVisible(true);
		});
		
		itemsView[3].addActionListener(e -> {
			setEnabled(false);
			prova.setList(Classrooms.getClassroomsValues(), "Classroom");
			prova.reinit();
			prova.setVisible(true);
		});
		
		itemsView[4].addActionListener(e -> {
			setEnabled(false);
			prova.setList(Days.getDaysValues(), "Day");
			prova.reinit();
			prova.setVisible(true);
		});
		
		prova.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentHidden(final ComponentEvent e) {
				if (prova.isOk()) {
					controller.setViews(prova.getSelectedObject());
				}
			}
		});

		bAdd.addActionListener(e -> {
			if (controller.getSubjectsList().isEmpty()) {
				commandFailed("There are no subject in the list!");
			} else {
				setEnabled(false);
				fAdd.setList(controller.getSubjectsList());
				fAdd.reinit();
				fAdd.setVisible(true);
			}
		});
		
		bRemove.addActionListener(e -> {
			if (controller.getSubjectsList().isEmpty()) {
				commandFailed("There are no subject in the list!");
			} else {
				setEnabled(false);
				fRemove.reinit();
				fRemove.setVisible(true);
			}
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
		
		rdbtnFirst.addActionListener(e -> controller.setViews(null));
		
		rdbtnSecond.addActionListener(e -> controller.setViews(null));
	}
	
	@Override
	public int getSelectedSem() {
		return rdbtnFirst.isSelected() ? Model.FIRST_SEM : Model.SEC_SEM;
	}
	
	@Override
	public void commandFailed(final String message) {
		JOptionPane.showMessageDialog(this, message, "An error occurred", JOptionPane.ERROR_MESSAGE);
	}
	
	@Override
	public void addData(final List<Object> list) {
		Object[] row;
		for (int i = 0; i < (list.size() / tm.getColumnCount()); i++) {
			row = new Object[tm.getColumnCount()];
			for (int j = 0; j < tm.getColumnCount(); j++) {
				row[j] = list.get(i * tm.getColumnCount() + j);
			}
			tm.addRow(row);
		}
	}

	@Override
	public void setEnabledCommandUndo(final boolean bool) {
		itemUndo.setEnabled(bool);
	}

	@Override
	public void setEnabledCommandRedo(final boolean bool) {
		itemRedo.setEnabled(bool);
	}

	@Override
	public void clearData() {
		tm.setDataVector(INIT_DATA, PROPS);
	}
	
	private String fileChooser(final int type) {
		if (type == JFileChooser.OPEN_DIALOG || type == JFileChooser.SAVE_DIALOG) {
			final JFileChooser chooser = new JFileChooser();
			final FileNameExtensionFilter filter = new FileNameExtensionFilter("*." + MY_EX, MY_EX);
			chooser.setFileFilter(filter);
			int returnVal;
			if (type == JFileChooser.OPEN_DIALOG) {
				returnVal = chooser.showOpenDialog(this);
			} else {
				returnVal = chooser.showSaveDialog(this);
			}
			if (returnVal == JFileChooser.APPROVE_OPTION) {
				return chooser.getCurrentDirectory() + SEPARATOR + chooser.getSelectedFile().getName();
			}
		}
		return null;
	}

}
