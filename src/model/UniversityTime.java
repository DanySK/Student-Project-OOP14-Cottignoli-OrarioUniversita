package model;

public interface UniversityTime {
	
	//aggiunge una materia nell'orario di una aula in un determinato giorno del semestre scelto
	public void add(final Days d, final Lesson less, final Classrooms room, final int hour, final int n);
			
	//rimuove qualsiasi materia nell'orario di una aula nelle ore selezionate di un determinato giorno del semestre scelto
	public void remove(final int sem, final Days d,final Classrooms room, final int hour, final int n);
		
}
