package model;

import java.util.Set;

public interface DailyTime {

	//aggiunge una materia, se parte o tutte le ore "selezionate" sono occupate tira un errore
	public void add(final Lesson less, final int hour, final int n);
	
	//rimuove qualsiasi materia nelle ore selezionate
	public void remove(final int hour, final int n);
	
	//restituisce la materia ad una determinata ora
	public Lesson getLesson(final int hour);
	
	//restituisce le materie che vi sono in un determinato arco di tempo
	public Set<Lesson> getlLessons(final int hour, final int n);
	
}
