package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JMenu;
import javax.swing.JRadioButtonMenuItem;

import model.Classrooms;
import model.Days;
import model.SubjectType;
import controller.IController;
import controller.ViewsType;

/**
 * Menu per la gestione del tipo di vista dell'orario che si vuole avere {@link ViewsType}.
 * 
 * @author Lorenzo Cottignoli
 *
 */
public class MenuViews extends JMenu {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private final JRadioButtonMenuItem[] itemsView = new JRadioButtonMenuItem[ViewsType.values().length];
	
	private final ListObjectForm fViewType;
	
	private final IView parent;
	private IController controller;
	
	/**
	 * Creazione del menù.
	 * 
	 * @param v View su cui verrà messo il menù.
	 * @param vType Form contenente una lista di oggetti, utilizzabile quando si richiede su quale oggetto voler focalizzare il tipo di vista.
	 */
	public MenuViews(final IView v, final ListObjectForm vType) {
		super("Views");
		parent = v;
		fViewType = vType;
		final ButtonGroup btngrp = new ButtonGroup();
		for (int i = 0; i < itemsView.length; i++) {
			itemsView[i] = new JRadioButtonMenuItem(ViewsType.values()[i].getDescription());
			itemsView[i].setActionCommand(ViewsType.values()[i].getDescription());
			add(itemsView[i]);
			btngrp.add(itemsView[i]);
			if (ViewsType.values()[i].equals(ViewsType.TOT)) {
				itemsView[i].setSelected(true);
			}
		}
		setHandlers();
	}
	
	/**
	 * Metodo per associare un controller a questo menù, in modo tale che gli handlers dei
	 * componenti funzionino sullo stesso controller che gestiste il Frame su cui è inserito il menù. 
	 * 
	 * @param ctrl Controller su cui poter effettuare le operazioni che mette a disposizione questo menù.
	 */
	public void setController(final IController ctrl) {
		controller = ctrl;
	}
	
	/**
	 * Metodo che associa all'Array di {@link JRadioButtonMenuItem} un listener comune che in base alla String restituita dal metodo 
	 * {@link JRadioButtonMenuItem#getActionCommand()} effettua compiti differenti.
	 */
	private void setHandlers() {
		final ActionListener itemsViewListener = new ActionListener() {
			
			@Override
			public void actionPerformed(final ActionEvent e) { 
				if (e.getActionCommand().equals(ViewsType.TOT.getDescription())) {
					controller.setViewType(null);
				}
				if (e.getActionCommand().equals(ViewsType.ROOM.getDescription())) {
					fViewType.setList(Classrooms.getClassroomsValues(), e.getActionCommand());
					fViewType.setVisible(true);
				}
				if (e.getActionCommand().equals(ViewsType.DAY.getDescription())) {
					fViewType.setList(Days.getDaysValues(), e.getActionCommand());
					fViewType.setVisible(true);
				}
				if (e.getActionCommand().equals(ViewsType.SUB_TYPE.getDescription())) {
					fViewType.setList(SubjectType.getSubjectTypeValues(), e.getActionCommand());
					fViewType.setVisible(true);
				}
				if (e.getActionCommand().equals(ViewsType.TEACH.getDescription())) {
					if (controller.getTeachersList().isEmpty()) {
						parent.commandFailed("There are no teacher in the list!");
					} else {
						fViewType.setList(controller.getTeachersList(), e.getActionCommand());
						fViewType.setVisible(true);
					}
				}
				if (e.getActionCommand().equals(ViewsType.SUB.getDescription())) {
					if (controller.getSubjectsList().isEmpty()) {
						parent.commandFailed("There are no Subject in the list!");
					} else {
						fViewType.setList(controller.getSubjectsList(), e.getActionCommand());
						fViewType.setVisible(true);
					}
				}
			}
		};
		
		for (final JRadioButtonMenuItem it : itemsView) {
			it.addActionListener(itemsViewListener);
		}
	}
}
