package model;

public interface Lesson {
	
	public static final int FIRST_SEM = 1;
	public static final int SEC_SEM = 2;
	
	//restituisce il nome della materia
	public String getSubName();
	//restituisce il nome del professore
	public String getTeachName();
	//restituisce 1 se è una materia del primo semestre, altrimenti 2
	public int getSemester();
}
