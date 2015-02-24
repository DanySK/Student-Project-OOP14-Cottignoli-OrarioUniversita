package model;

import java.util.Set;

public interface ClassroomsDailyTime {
	
	//aggiunge una materia nell'orario di una determinata aula, se parte o tutte le ore "selezionate" sono occupate tira un errore
	public void add(final Lesson less, final Classrooms room, final int hour, final int n);
	
	//rimuove qualsiasi materia nell'orario di una determinata aula nelle ore selezionate
	public void remove(final Classrooms room, final int hour, final int n);
	
	//restituisce la materia ad una determinata ora in una determinata aula
	public Lesson getLesson(final Classrooms room, final int hour);
	
	//restituisce le materie che vi sono in un determinato arco di tempo in una determinata aula
		public Set<Lesson> getlLessons(final Classrooms room, final int hour, final int n);
	
}
