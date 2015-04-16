package model;

import java.util.Optional;
import java.util.Set;

public interface IModel extends java.io.Serializable {
	
	int FIRST_SEM = 0;
	int SEC_SEM = 1;
	
	//aggiunge una materia nell'orario di una aula in un determinato giorno del semestre scelto
	void add(int sem, Days d, ISubject sub, Classrooms room, int hour, int n) throws WrongInputException;
			
	//rimuove qualsiasi materia nell'orario di una aula nelle ore selezionate di un determinato giorno del semestre scelto
	void remove(int sem, Days d, Classrooms room, int hour, int n) throws WrongInputException;
	
	void addSubject(String sub, String teach, SubjectType type);
	
	void removeSubject(ISubject sub);
		
	Optional<ISubject> getSubject(int sem, Days d, Classrooms room, int hour) throws WrongInputException;
	
	Set<ISubject> getSubjects();
	
	void setSubjects(Set<ISubject> set);
	
	Classrooms whereTeaching(String teach, int hour, Days d, int sem) throws WrongInputException;
	
	Classrooms wherePerforming(ISubject sub, int hour, Days d, int sem) throws WrongInputException;
	
	Set<String> getTeachers();
	
	IMemento createMemento();
	
	void setMemento(IMemento mem);
	
	interface IMemento {
		
		Object getState();
	}
}
