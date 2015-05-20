package model;

import java.util.Optional;
import java.util.Set;

/**
 * Interfaccia che definisce l'orario completo {@link IClassrooomsDailyTime} di una settimana universitaria, cioè rappresenta 
 * l'orario completo di un semestre della facoltà di scienze e tecnologie informatiche di Cesena.
 * 
 * @author Lorenzo Cottignoli
 *
 */
public interface IWeeklyTime extends java.io.Serializable {

	/**
	 * Aggiunge una materia nell'orario di un'aula alle ore selezionate di un determinato giorno.
	 * 
	 * @param sub Materia da inserire nell'orario.
	 * @param d Giorno in cui inserire la materia.
	 * @param room Aula in cui svolgere la materia.
	 * @param hour Ora di inizio della materia.
	 * @param n Numero di ore consecutive in cui viene tenuta la materia.
	 * @throws IllegalArgumentException se d è null oppure nei casi specificati nel metodo 
	 * 									{@link IClassroomsDailyTime#add(ISubject, Classrooms, int, int)}.
	 */
	void add(ISubject sub, Days d, Classrooms room, int hour, int n);
		
	//
	/**
	 * Rimuove qualsiasi materia nell'orario di un'aula nelle ore selezionate di un determinato giorno.
	 * 
	 * @param d Giorno in cui eliminare la materia.
	 * @param room Aula da cui eliminare la materia.
	 * @param hour Ora di inizio da cui partire ad eliminare.
	 * @param n Numero di ore consecutive da eliminare.
	 * @throws IllegalArgumentException se d è null oppure nei casi specificati nel metodo 
	 * 									{@link IClassroomsDailyTime#remove(Classrooms, int, int)}.
	 */
	void remove(Days d, Classrooms room, int hour, int n);
	
	/**
	 * Restituisce la materia presente in una determinata ora in un'aula di uno specifico giorno.
	 * 
	 * @param d Giorno in cui cercare la materia da restituire.
	 * @param room Aula in cui cercare la materia da restituire.
	 * @param hour Ora in cui cercare la materia da restituire.
	 * @return La materia presente nell'ora, nell'aula e giorno indicati. Si utilizza un Optional per evitare di restituire null 
	 * 		   {@link Optional} nel caso non vi sia alcuna materia nell'ora selezionata.
	 * @throws IllegalArgumentException se d è null oppure nei casi specificati nel seguente metodo 
	 * 									{@link IClassroomsDailyTime#getSubject(Classrooms, int)}.
	 */
	Optional<ISubject> getSubject(Days d, Classrooms room, int hour);
	
	/**
	 * Restituisce una copia dell'orario giornaliero di tutte le aulee {@link IClassroomsDailyTime} di un determinato giorno.
	 * 
	 * @param d Giorno di cui si vuole avere l'orario giornaliero completo.
	 * @return Orario giornaliero completo di tutte le aulee.
	 * @throws IllegalArgumentException se d è null.
	 */
	IClassroomsDailyTime getClassroomDailyTime(Days d);
	
	/**
	 * Crea una copia di se stesso, in modo da rispettare il metodo {@link #equals(Object)}.
	 * 
	 * @return Una copia dell'oggetto che invoca questo metodo.
	 */
	IWeeklyTime copy();
	
	/**
	 * Restituisce un set contenente in quali aulee è il professore in una determinata ora del giorno specificato.
	 * 
	 * @param teach Nome del professore di cui ci interessa sapere dove ha lezione.
	 * @param d Giorno in cui cercare.
	 * @param hour	Ora in cui cercare tra le varie aulee.
	 * @return Set contenente le aulee oppure set vuoto nel caso non stia insegnando nell'ora specificata o se teach è null.
	 * @throws IllegalArgumentException se d è null.
	 */
	Set<Classrooms> whereTeaching(String teach, Days d, int hour);
	
	/**
	 * Restituisce un set contenente in quali aulee è tenuta una materia in una determinata ora del giorno specificato.
	 * 
	 * @param sub Materia di cui ci interessa sapere dove è tenuta in una determinata ora.
	 * @param d Giorno in cui cercare.
	 * @param hour	Ora in cui cercare tra le varie aulee.
	 * @return Set contenente le aulee oppure set vuoto nel caso non sia tenuta nell'ora specificata o se sub è null.
	 * @throws IllegalArgumentException se d è null.
	 */
	Set<Classrooms> wherePerforming(ISubject sub, Days d, int hour);
}
