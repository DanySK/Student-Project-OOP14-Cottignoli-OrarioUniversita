package model;

import java.util.Optional;
import java.util.Set;

public interface IClassroomsDailyTime extends java.io.Serializable {
	
	//aggiunge una materia nell'orario di una determinata aula, se parte o tutte le ore "selezionate" sono occupate tira un errore
	void add(final ISubject sub, final Classrooms room, final int hour, final int n);
	
	//rimuove qualsiasi materia nell'orario di una determinata aula nelle ore selezionate
	void remove(final Classrooms room, final int hour, final int n);
	
	//restituisce la materia ad una determinata ora in una determinata aula
	Optional<ISubject> getSubject(final Classrooms room, final int hour);
	
	//restituisce le materie che vi sono in un determinato arco di tempo in una determinata aula
	Set<ISubject> getSubjects(final Classrooms room, final int hour, final int n);
	
}
