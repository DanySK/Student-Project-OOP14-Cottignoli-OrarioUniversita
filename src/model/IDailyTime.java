package model;

import java.util.Optional;
import java.util.Set;

public interface IDailyTime extends java.io.Serializable {

	int HOURS = 9;	//numero ore giornata scolastica
	int FIRST_HOUR = 9;		//ora inizio lezioni
	
	//aggiunge una materia, se parte o tutte le ore "selezionate" sono occupate tira un errore
	void add(final ISubject less, final int hour, final int n);
	
	//rimuove qualsiasi materia nelle ore selezionate
	void remove(final int hour, final int n);
	
	//restituisce la materia ad una determinata ora
	Optional<ISubject> getSubject(final int hour);
	
	//restituisce le materie che vi sono in un determinato arco di tempo
	Set<ISubject> getSubjects(final int hour, final int n);
	
}
