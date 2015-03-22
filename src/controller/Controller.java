package controller;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashSet;
import java.util.Set;

import model.Classrooms;
import model.Days;
import model.IModel;
import model.ISubject;
import model.Model;
import model.SubjectType;
import view.IView;

public class Controller implements IController{

	private final Set<IView> view = new HashSet<>();
	private IModel model = new Model();
	
	@Override
	public void addView(final IView v) {
		v.setController(this);
		view.add(v);
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
		model.add(sem, d, sub, room, hour, n);
	}

	@Override
	public void commandRemove(final int sem, final Days d, final Classrooms room, final int hour, final int n) {
		model.remove(sem, d, room, hour, n);
	}

	@Override
	public void commandSave(final String fileName) {
		try {
			final ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName));
			oos.writeObject(model);
			oos.close();
		} catch (IOException e) {
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
	}

	@Override
	public Set<ISubject> commandGetSubjectsList() {
		return model.getSubjects();
	}

	@Override
	public void commandBack() {
		
	}

	@Override
	public void commandNext() {
		
	}
	
	
	
	private void displayError(final Throwable e) {
		for (final IView v : view) {
			v.commandFailed(e.getMessage());
		}
	}
	
	public void test(){
		System.out.println("Materie presenti nel set: ");
		for (final ISubject s : model.getSubjects()) {
			System.out.println(s.toString());
		}
		System.out.println(model.getSubject(0, Days.MONDAY, Classrooms.A, 12).toString());
		System.out.println(model.getSubject(0, Days.FRIDAY, Classrooms.B, 9).toString());
	}

	@Override
	public void commandSetSubjectList() {
		
	}
	
	
	
}
