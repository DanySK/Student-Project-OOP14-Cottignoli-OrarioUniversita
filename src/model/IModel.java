package model;

import java.util.Optional;
import java.util.Set;

public interface IModel extends java.io.Serializable {
	
	int FIRST_SEM = 0;
	int SEC_SEM = 1;
	
	//aggiunge una materia nell'orario di una aula in un determinato giorno del semestre scelto
	void add(final int sem, final Days d, final ISubject sub, final Classrooms room, final int hour, final int n);
			
	//rimuove qualsiasi materia nell'orario di una aula nelle ore selezionate di un determinato giorno del semestre scelto
	void remove(final int sem, final Days d, final Classrooms room, final int hour, final int n);
	
	void addSubject(final String sub, final String teach, final SubjectType type);
	
	void removeSubject(final ISubject sub);
		
	Optional<ISubject> getSubject(final int sem, final Days d, final Classrooms room, final int hour);
	
	Set<ISubject> getSubjects();
	
	void setSubjects(Set<ISubject> set);
}
