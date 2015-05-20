package view;

import javax.swing.ImageIcon;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

import controller.IController;

/**
 * Menù per la gestione delle operazioni di: undo, redo, aggiunta e rimozione di una materia dalla lista delle materie
 * disponibili.
 * 
 * @author Lorenzo Cottignoli
 *
 */
public class MenuModify extends JMenu {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JMenuItem itemUndo = new JMenuItem("Undo", new ImageIcon(getClass().getResource("/undo.gif")));
	private final JMenuItem itemRedo = new JMenuItem("Redo", new ImageIcon(getClass().getResource("/redo.gif")));
	private final JMenuItem itemAddSub = new JMenuItem("Add new subjcet to list");
	private final JMenuItem itemRemoveSub = new JMenuItem("Remove subjcet from list");
	
	private final AddSubjectForm addSub;
	private final ListObjectForm remSub;
	
	private final IView parent;
	private IController controller;
	
	/**
	 * Creazione del menù.
	 * 
	 * @param v View su cui verrà messo il menù.
	 * @param add Form per l'inserimento dei dati relativi alla creazione di una nuova materia.
	 * @param rem Form per la selezione della materia da eliminare dalla lista.
	 */
	public MenuModify(final IView v, final AddSubjectForm add, final ListObjectForm rem) {
		super("Modify");
		parent = v;
		addSub = add;
		remSub = rem;
		add(itemUndo);
		itemUndo.setEnabled(false);
		add(itemRedo);
		itemRedo.setEnabled(false);
		addSeparator();
		add(itemAddSub);
		add(itemRemoveSub);
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
	 * Metodo per abilitare o disabilitare il pulsante undo.
	 * 
	 * @param bool se è true abilita il pulsante, altrimenti lo disabilita.
	 */
	public void setEnabledCommandUndo(final boolean bool) {
		itemUndo.setEnabled(bool);
	}

	/**
	 * Metodo per abilitare o disabilitare il pulsante redo.
	 * 
	 * @param bool se è true abilita il pulsante, altrimenti lo disabilita.
	 */
	public void setEnabledCommandRedo(final boolean bool) {
		itemRedo.setEnabled(bool);
	}
	
	/**
	 * Metodo che associa ad ogni {@link JMenuItem} il proprio listener in base all'operazione che deve svolgere.
	 */
	private void setHandlers() {
		itemUndo.addActionListener(e -> {
			controller.commandUndo();
		});
		
		itemRedo.addActionListener(e -> {
			controller.commandRedo();
		});
		
		itemAddSub.addActionListener(e -> {
			addSub.setVisible(true);
		});
		
		itemRemoveSub.addActionListener(e -> {
			if (controller.getSubjectsList().isEmpty()) {
				parent.commandFailed("There are no subject in the list!");
			} else {
				remSub.setList(controller.getSubjectsList(), "Subject");
				remSub.setVisible(true);
			}
		});
	}
}
