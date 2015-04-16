package controller;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;

import model.Caretaker;
import model.Classrooms;
import model.DailyTime;
import model.Days;
import model.ICaretaker;
import model.IModel;
import model.ISubject;
import model.Model;
import model.SubjectType;
import model.ViewsType;
import view.IView;

public class Controller implements IController {

	private final Set<IView> view = new HashSet<>();
	private IModel model = new Model();
	private transient ICaretaker<IModel.IMemento> care = new Caretaker<>();
	
	private Object obj; //provaaaaaaaaaaaaaaaa
	
	public Controller() {
		care.add(model.createMemento());
	}
	
	@Override
	public void addView(final IView v) {
		v.setController(this);
		view.add(v);
		
		updateViews(); //provaaaaaaaaa
	}
	
	@Override
	public void commandAddSubject(final String sub, final String teach, final SubjectType type) {
		model.addSubject(sub, teach, type);
	}
	
	@Override
	public void commandRemoveSubject(final ISubject sub) {
		model.removeSubject(sub);
	}

	@Override
	public void commandAdd(final int sem, final Days d, final ISubject sub, final Classrooms room, final int hour, final int n) {
		try {
			model.add(sem, d, sub, room, hour, n);
		} catch (Exception e) {
			displayError(e);
		}
		care.add(model.createMemento());
		undoRedo();
		
		updateViews(); //provaaaaaaaaa
	}

	@Override
	public void commandRemove(final int sem, final Days d, final Classrooms room, final int hour, final int n) {
		try {
			model.remove(sem, d, room, hour, n);
		} catch (Exception e) {
			displayError(e);
		}
		care.add(model.createMemento());
		undoRedo();
		
		updateViews(); //provaaaaaaaaa
	}

	@Override
	public void commandSave(final String fileName) {
		try {
			final ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName));
			oos.writeObject(model);
			oos.close();
		} catch (Exception e) {
			displayError(e);
		}
	}

	@Override
	public void commandLoad(final String fileName) {
		try {
			final ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName));
			model = (IModel) ois.readObject();
			ois.close();
		} catch (Exception e) {
			displayError(e);
		}
		care = new Caretaker<>();
		care.add(model.createMemento());
		undoRedo();
		
		updateViews(); //provaaaaaaaaa
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
	public void commandUndo() {
		model.setMemento(care.getPrev());
		undoRedo();

		updateViews(); //provaaaaaaaaa
	}

	@Override
	public void commandRedo() {
		model.setMemento(care.getSucc());
		undoRedo();
		
		updateViews(); //provaaaaaaaaaa
	}
	
	
	
	private void displayError(final Throwable e) {
		for (final IView v : view) {
			v.commandFailed(e.getMessage());
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public void commandImportSubjectList(final String fileName) {
		try {
			final ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName));
			model.setSubjects((Set<ISubject>) ois.readObject());
			ois.close();
		} catch (Exception e) {
			displayError(e);
		}
	}

	@Override
	public void commandExportSubjectList(final String fileName) {
		try {
			final ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName));
			oos.writeObject(model.getSubjects());
			oos.close();
		} catch (Exception e) {
			displayError(e);
		}
	}
	
	private void undoRedo() {
		for (final IView v : view) {
			if (care.succExist()) {
				v.setEnabledCommandRedo(true);
			} else {
				v.setEnabledCommandRedo(false);
			}
			if (care.prevExist()) {
				v.setEnabledCommandUndo(true);
			} else {
				v.setEnabledCommandUndo(false);
			}
		}
	}
	
	//provaaaaaaaaaaaa
	private void updateViews() {
		for (final IView v : view) {
			v.clearData();
			if (obj == null) {
				v.addData(viewTot(v.getSelectedSem()));
			} else {
				if (obj.getClass().equals(String.class)) {
					v.addData(viewProf(v.getSelectedSem(), (String) obj));
				}
				
				if (obj.getClass().equals(Days.class)) {
					v.addData(viewDay(v.getSelectedSem(), (Days) obj));
				}
				
				if (obj.getClass().equals(Classrooms.class)) {
					v.addData(viewClassroom(v.getSelectedSem(), (Classrooms) obj));
				}
				
				if (ISubject.class.isAssignableFrom(obj.getClass())) {
					v.addData(viewSubject(v.getSelectedSem(), (ISubject) obj));
				}
				if (obj.getClass().equals(SubjectType.class)) {
					v.addData(viewTypeSubject());
				}
			}
		}
	}
	
	//provaaaaaaaaaaaaaaa
	@Override
	public void setViews(final Object ob) {
		obj = ob;
		updateViews();
	}
	
	private List<Object> viewDay(final int sem, final Days d) {
		final List<Object> list = new ArrayList<>(Arrays.asList(new Object[]{d.getName(), "9-10", "10-11", "11-12", "12-13", "13-14", "14-15", "15-16", "16-17", "17-18"}));
		for (final Classrooms cls : Classrooms.values()) {
			list.add(cls.getName());
			try {
				for (int i = DailyTime.FIRST_HOUR; i < (DailyTime.FIRST_HOUR + DailyTime.HOURS); i++) {
					if (model.getSubject(sem, d, cls, i).isPresent()) {
						list.add(model.getSubject(sem, d, cls, i).get());
					} else {
						list.add("");
					}
				}
			} catch (Exception e) {
				displayError(e);
			}
		}
		return list;
	}
	
	private List<Object> viewProf(final int sem, final String teach) {
		final List<Object> list = new ArrayList<>(Arrays.asList(new Object[]{"Days", "9-10", "10-11", "11-12", "12-13", "13-14", "14-15", "15-16", "16-17", "17-18"}));
		Classrooms app;
		for (final Days d : Days.values()) {
			list.add(d.getName());
			try {
				for (int i = DailyTime.FIRST_HOUR; i < (DailyTime.FIRST_HOUR + DailyTime.HOURS); i++) {
					app = model.whereTeaching(teach, i, d, sem);
					if (app == null) {
						list.add("");
					} else {
						list.add(model.getSubject(sem, d, app, i).get().getSubName() + " - " + app.getName());
					}
				}
			} catch (Exception e) {
				displayError(e);
			}
		}
		return list;
	}
	
	private List<Object> viewTot(final int sem) {
		final List<Object> list = new ArrayList<>();
		for (final Days d : Days.values()) {
			list.addAll(viewDay(sem, d));
		}
		return list;
	}
	
	private List<Object> viewClassroom(final int sem, final Classrooms room) {
		final List<Object> list = new ArrayList<>(Arrays.asList(new Object[]{"Days", "9-10", "10-11", "11-12", "12-13", "13-14", "14-15", "15-16", "16-17", "17-18"}));
		for (final Days d : Days.values()) {
			list.add(d.getName());
			try {
				for (int i = DailyTime.FIRST_HOUR; i < (DailyTime.FIRST_HOUR + DailyTime.HOURS); i++) {
					if (model.getSubject(sem, d, room, i).isPresent()) {
						list.add(model.getSubject(sem, d, room, i).get());
					} else {
						list.add("");
					}
				}
			} catch (Exception e) {
				displayError(e);
			}
		}
		return list;
	}
	
	private List<Object> viewSubject(final int sem, final ISubject sub) {
		final List<Object> list = new ArrayList<>(Arrays.asList(new Object[]{"Days", "9-10", "10-11", "11-12", "12-13", "13-14", "14-15", "15-16", "16-17", "17-18"}));
		Classrooms app;
		for (final Days d : Days.values()) {
			list.add(d.getName());
			try {
				for (int i = DailyTime.FIRST_HOUR; i < (DailyTime.FIRST_HOUR + DailyTime.HOURS); i++) {
					app = model.wherePerforming(sub, i, d, sem);
					if (app == null) {
						list.add("");
					} else {
						list.add(app.getName());
					}
				}
			} catch (Exception e) {
				displayError(e);
			}
		}
		return list;
	}
	
	private List<Object> viewTypeSubject() {
		return null;
	}

	@Override
	public void commandNew() {
		model = new Model();
		
		updateViews();
	}
}
