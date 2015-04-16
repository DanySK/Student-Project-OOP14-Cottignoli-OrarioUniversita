package model;

import java.util.Optional;

public interface IDailyTime extends java.io.Serializable {

	int HOURS = 9;	//numero ore giornata scolastica
	int FIRST_HOUR = 9;		//ora inizio lezioni
	
	//aggiunge una materia, se parte o tutte le ore "selezionate" sono occupate tira un errore
	void add(ISubject less, int hour, int n) throws WrongInputException;
	
	//rimuove qualsiasi materia nelle ore selezionate
	void remove(int hour, int n) throws WrongInputException;
	
	//restituisce la materia ad una determinata ora
	Optional<ISubject> getSubject(int hour) throws WrongInputException;
	
	IDailyTime copy();
}
