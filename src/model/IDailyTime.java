package model;

import java.util.Optional;

/**
 * Interfaccia che definisce l'orario giornaliero universitario e le operazioni su esso applicabili.
 * 
 * @author Lorenzo Cottignoli
 *
 */
public interface IDailyTime extends java.io.Serializable {

	/**
	 * Costante int che rappresenta il numero di ore presenti in una giornata universitaria.
	 */
	int HOURS = 9;
	
	/**
	 * Costante int che rappresenta l'ora di inizio delle lezioni universitarie.
	 */
	int FIRST_HOUR = 9;		
	
	/**
	 * Aggiunge una materia nell'arco di tempo indicato.
	 * 
	 * @param sub Materia da inserire.
	 * @param hour Ora di inizio della lezione.
	 * @param n Numero di ore consecutive in cui viene insegnata la materia passata come parametro partendo da hour.
	 * @throws IllegalArgumentException se alcune o tutte le ore indicate sono già occupate da altre materie 
	 * 									oppure se hour e n hanno valori incoerenti. hour deve essere maggiore o uguale
	 * 									a {@link #FIRST_HOUR} e minore di {@link #FIRST_HOUR} + {@link #HOURS}, 
	 * 									n deve essere maggiore di 0 e la somma hour + n deve essere compresa tra {@link #FIRST_HOUR} 
	 * 									e {@link #FIRST_HOUR} + {@link #HOURS} compresi.
	 */
	void add(ISubject sub, int hour, int n);
	
	/**
	 * Rimuove qualsiasi materia presente nell'arco di tempo selezionato, nel caso non ve ne siano non fa nulla.
	 * 
	 * @param hour Ora da cui iniziare ad eliminare le materie.
	 * @param n Numero di ore consecutive in cui eliminare le materie partendo da hour. 
	 * @throws IllegalArgumentException se hour e n hanno valori incoerenti. hour deve essere maggiore o uguale
	 * 									a {@link #FIRST_HOUR} e minore di {@link #FIRST_HOUR} + {@link #HOURS}, 
	 * 									n deve essere maggiore di 0 e la somma hour + n deve essere compresa tra {@link #FIRST_HOUR} 
	 * 									e {@link #FIRST_HOUR} + {@link #HOURS} compresi.
	 */
	void remove(int hour, int n);
	
	/**
	 * Restituisce la materia presente in una determinata ora.
	 * 
	 * @param hour Ora in cui cercare la materia da restituire.
	 * @return La materia presente nell'ora indicata. Si utilizza un Optional per evitare di restituire null {@link Optional} 
	 * 		   nel caso non vi sia alcuna materia nell'ora selezionata.
	 * @throws IllegalArgumentException se hour ha valori incoerenti. hour deve essere maggiore o uguale
	 * 									a {@link #FIRST_HOUR} e minore di {@link #FIRST_HOUR} + {@link #HOURS}.
	 */
	Optional<ISubject> getSubject(int hour);
	
	/**
	 * Crea una copia di se stesso, in modo da rispettare il metodo {@link #equals(Object)}.
	 * 
	 * @return Una copia dell'oggetto che invoca questo metodo.
	 */
	IDailyTime copy();
}
