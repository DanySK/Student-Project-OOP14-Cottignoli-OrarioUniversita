package controller;

import java.util.Set;

import model.Classrooms;
import model.Days;
import model.ISubject;
import model.Subject;
import model.SubjectType;
import view.IView;

public interface IController {
	
	void addView(final IView v);
	
	void commandAddSubject(final String sub, final String teach, final SubjectType type);
	
	void commandRemoveSubject(final ISubject sub);
	
	void commandAdd(final int sem, final Days d, final ISubject sub, final Classrooms room, final int hour, final int n);
	
	void commandRemove(final int sem, final Days d, final Classrooms room, final int hour, final int n);
	
	void commandSave(final String fileName);
	
	void commandLoad(final String fileName);
	
	Set<ISubject> commandGetSubjectsList();
	
	void commandSetSubjectList();
	
	void commandBack();
	
	void commandNext();
	
	void test();
}
