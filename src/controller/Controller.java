package controller;

import java.util.HashSet;
import java.util.Set;

import model.Classrooms;
import model.Days;
import model.IModel;
import model.ISubject;
import model.Model;
import model.SubjectType;
import view.IView;

/**
 * Implementazione dell'interfaccia {@link IController}.
 * 
 * @author Lorenzo Cottignoli
 *
 */
public class Controller implements IController {
	
	private final Set<IView> view = new HashSet<>();
	private IModel model = new Model();
	private final IViewController vcontr = new ViewController(model);
 
	/**
	 * Costruttore che salva il primo stato del modello, ovvero quando è vuoto.
	 */
	public Controller() {
		vcontr.createMemento();
	}
	
	@Override
	public void addView(final IView v) {
		v.setController(this);
		view.add(v);
		updateViews();
	}
	
	@Override
	public void removeView(final IView v) {
		view.remove(v);
	}
	
	@Override
	public void setViewType(final Object ob) {
		for (final IView v : view) {
			vcontr.setViewType(v, ob);
		}
	}
	
	@Override
	public void updateViews() {
		for (final IView v : view) {
			vcontr.updateViews(v);
		}
	}
	
	@Override
	public Set<ISubject> getSubjectsList() {
		return model.getSubjects();
	}
	
	@Override
	public Set<String> getTeachersList() {
		return model.getTeachers();
	}
	
	@Override
	public void commandNew() {
		model = new Model();
		vcontr.setModel(model);
		vcontr.resetCaretaker();
		updateViews();
		undoRedo();
	}
	
	@Override
	public void commandAddSubject(final String sub, final String teach, final SubjectType type) {
		try {
			model.addSubject(sub, teach, type);
		} catch (Exception e) {
			displayError(e);
		}
	}
	
	@Override
	public void commandRemoveSubject(final ISubject sub) {
		model.removeSubject(sub);
	}
	
	@Override
	public void commandExportSubjectList(final String fileName) {
		try {
			SaveLoad.commandSave(fileName, model.getSubjects());
		} catch (Exception e) {
			displayError(e);
		}
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void commandImportSubjectList(final String fileName) {
		try {
			model.setSubjects((Set<ISubject>) SaveLoad.commandLoad(fileName));
			vcontr.setModel(model);
		} catch (Exception e) {
			displayError(e);
		}
	}
	
	@Override
	public void commandAdd(final ISubject sub, final int sem, final Days d, final Classrooms room, final int hour, final int n) {
		try {
			model.add(sub, sem, d, room, hour, n);
			vcontr.createMemento();
			undoRedo();
			updateViews();
		} catch (Exception e) {
			displayError(e);
		}
	}
	
	@Override
	public void commandRemove(final int sem, final Days d, final Classrooms room, final int hour, final int n) {
		try {
			model.remove(sem, d, room, hour, n);
			vcontr.createMemento();
			undoRedo();
			updateViews();
		} catch (Exception e) {
			displayError(e);
		}
	}
	
	@Override
	public void commandSave(final String fileName) {
		try {
			SaveLoad.commandSave(fileName, model);
		} catch (Exception e) {
			displayError(e);
		}
	}
	
	@Override
	public void commandLoad(final String fileName) {
		try {
			model = (IModel) SaveLoad.commandLoad(fileName);
			vcontr.setModel(model);
			vcontr.resetCaretaker();
			undoRedo();
			updateViews();
		} catch (Exception e) {
			displayError(e);
		}
	}
	
	@Override
	public void commandUndo() {
		model.setMemento(vcontr.getPrevMemento());
		undoRedo();
		updateViews();
	}
	
	@Override
	public void commandRedo() {
		model.setMemento(vcontr.getSuccMemento());
		undoRedo();
		updateViews();
	}
	
	/**
	 * Richiamata il metodo {@link IViewController#undoRedo(IView)} per ogni view attaccata al controller.
	 */
	private void undoRedo() {
		for (final IView v : view) {
			vcontr.undoRedo(v);
		}
	}
	
	/**
	 * Notifica tutte le view che è avvenuto un qualche tipo di errore.
	 * 
	 * @param e Eccezione avvenuta in seguito ad un errore della quale si mostrerà la descrizione alla view.
	 */
	private void displayError(final Throwable e) {
		for (final IView v : view) {
			v.commandFailed(e.getMessage());
		}
	}
	
}
