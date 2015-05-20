package view;

import java.awt.Frame;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import controller.IController;

/**
 * Menù per la gestione delle operazioni di: creazione di un nuovo modello, salvataggio, caricamento,
 * import ed export della lista delle materie e chiusura del programma.
 * 
 * @author Lorenzo Cottignoli
 *
 */
public class MenuFile extends JMenu {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private final JMenuItem itemNew = new JMenuItem("New", new ImageIcon(getClass().getResource("/new.gif")));
	private final JMenuItem itemLoad = new JMenuItem("Load", new ImageIcon(getClass().getResource("/load.gif")));
	private final JMenuItem itemSaveAs = new JMenuItem("Save as..", new ImageIcon(getClass().getResource("/save.gif")));
	private final JMenuItem itemImportSub = new JMenuItem("Import subject list", new ImageIcon(getClass().getResource("/import.gif")));
	private final JMenuItem itemExportSub = new JMenuItem("Export subject list", new ImageIcon(getClass().getResource("/export.gif")));
	private final JMenuItem itemExit = new JMenuItem("Exit");
	
	private final Frame parent;
	private IController controller;
	
	private final JFileChooser chooser = new JFileChooser();
	
	/**
	 * Creazione del menù.
	 * 
	 * @param f Frame sul quale verrà inserito il menù.
	 */
	public MenuFile(final Frame f) {
		super("File");
		parent = f;
		add(itemNew);
		add(itemLoad);
		add(itemSaveAs);
		add(itemImportSub);
		add(itemExportSub);
		add(itemExit);
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
	 * Metodo che associa ad ogni {@link JMenuItem} il proprio listener in base all'operazione che deve svolgere.
	 */
	private void setHandlers() {
		itemNew.addActionListener(e -> {
			final int n = JOptionPane.showConfirmDialog(parent, "Do you wanna save before create a new model?", "Saving?", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);
			if (n == JOptionPane.NO_OPTION) {
				controller.commandNew();
			}
			if (n == JOptionPane.YES_OPTION) {
				final int returnVal = chooser.showSaveDialog(parent);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					controller.commandSave(getPath());
					controller.commandNew();
				}
			}
		});
		
		itemLoad.addActionListener(e -> {
			final int returnVal = chooser.showOpenDialog(parent);
			if (returnVal == JFileChooser.APPROVE_OPTION) {
				controller.commandLoad(getPath());
			}
		});
		
		itemSaveAs.addActionListener(e -> {
			final int returnVal = chooser.showSaveDialog(parent);
			if (returnVal == JFileChooser.APPROVE_OPTION) {
				controller.commandSave(getPath());
			}
		});
		
		itemImportSub.addActionListener(e -> {
			final int returnVal = chooser.showOpenDialog(parent);
			if (returnVal == JFileChooser.APPROVE_OPTION) {
				controller.commandImportSubjectList(getPath());
			}
		});
		
		itemExportSub.addActionListener(e -> {
			final int returnVal = chooser.showSaveDialog(parent);
			if (returnVal == JFileChooser.APPROVE_OPTION) {
				controller.commandExportSubjectList(getPath());
			}
		});
		
		itemExit.addActionListener(e -> {
			final int n = JOptionPane.showConfirmDialog(parent, "Do you wanna save before quit?", "Quitting..", 
														JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);
			if (n == JOptionPane.YES_OPTION) {
				final int returnVal = chooser.showSaveDialog(parent);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					controller.commandSave(getPath());
					System.exit(0);
				}
			}
			if (n == JOptionPane.NO_OPTION) {
				System.exit(0);
			}
		});
	}
	
	private String getPath() {
		return chooser.getCurrentDirectory() + System.getProperty("file.separator") + chooser.getSelectedFile().getName();
	}
}
