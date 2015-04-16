package model;

import java.util.Optional;

public interface IWeeklyTime extends java.io.Serializable {

	//aggiunge una materia nell'orario di una aula in un determinato giorno, se parte o tutte le ore "selezionate" sono occupate tira un errore
	void add(Days d, ISubject sub, Classrooms room, int hour, int n) throws WrongInputException;
		
	//rimuove qualsiasi materia nell'orario di una aula nelle ore selezionate di un determinato giorno
	void remove(Days d, Classrooms room, int hour, int n) throws WrongInputException;
	
	Optional<ISubject> getSubject(Days d, Classrooms room, int hour) throws WrongInputException;
	
	IClassroomsDailyTime getClassroomDailyTime(Days d);
	
	IWeeklyTime copy();
	
	Classrooms whereTeaching(String teach, int hour, Days d) throws WrongInputException;
	
	Classrooms wherePerforming(ISubject sub, int hour, Days d) throws WrongInputException;
}
