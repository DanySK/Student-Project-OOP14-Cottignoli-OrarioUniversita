package model;

public interface WeeklyHours {

	//aggiunge una materia nell'orario di una aula in un determinato giorno, se parte o tutte le ore "selezionate" sono occupate tira un errore
	public void add(final Days d, final Lesson less, final Classrooms room, final int hour, final int n);
		
	//rimuove qualsiasi materia nell'orario di una aula nelle ore selezionate di un determinato giorno
	public void remove(final Days d,final Classrooms room, final int hour, final int n);
	
}
