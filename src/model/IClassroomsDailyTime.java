package model;

import java.util.Optional;

public interface IClassroomsDailyTime extends java.io.Serializable {
	
	//aggiunge una materia nell'orario di una determinata aula, se parte o tutte le ore "selezionate" sono occupate tira un errore
	void add(ISubject sub, Classrooms room, int hour, int n) throws WrongInputException;
	
	//rimuove qualsiasi materia nell'orario di una determinata aula nelle ore selezionate
	void remove(Classrooms room, int hour, int n) throws WrongInputException;
	
	//restituisce la materia ad una determinata ora in una determinata aula
	Optional<ISubject> getSubject(Classrooms room, int hour) throws WrongInputException;
	
	IDailyTime getDailyTime(Classrooms room);
	
	IClassroomsDailyTime copy();
	
	Classrooms whereTeaching(String teach, int hour) throws WrongInputException;
	
	Classrooms wherePerforming(ISubject sub, int hour) throws WrongInputException;
}
