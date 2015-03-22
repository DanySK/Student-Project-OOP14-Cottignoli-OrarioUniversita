package model;

import java.util.Optional;

public interface IWeeklyTime extends java.io.Serializable {

	//aggiunge una materia nell'orario di una aula in un determinato giorno, se parte o tutte le ore "selezionate" sono occupate tira un errore
	void add(final Days d, final ISubject sub, final Classrooms room, final int hour, final int n);
		
	//rimuove qualsiasi materia nell'orario di una aula nelle ore selezionate di un determinato giorno
	void remove(final Days d, final Classrooms room, final int hour, final int n);
	
	Optional<ISubject> getSubject(final Days d, final Classrooms room, final int hour);
	
}
