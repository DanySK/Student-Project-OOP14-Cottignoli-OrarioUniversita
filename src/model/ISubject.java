package model;

public interface ISubject extends java.io.Serializable {
	
	//restituisce il nome della materia
	String getSubName();
	//restituisce il nome del professore
	String getTeachName();
	//restituisce di che "tipo" è la materia
	SubjectType getSubjectType();
}
