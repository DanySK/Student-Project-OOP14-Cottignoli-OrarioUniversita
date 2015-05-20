package model;

import java.util.Optional;
import java.util.Set;

/**
 * Interfaccia che definisce l'orario giornaliero universitario {@link IDailyTime} per ogni aula in cui poter fare lezione della facoltà di 
 * scienze e tecnologie informatiche di Cesena.
 * 
 * @author Lorenzo Cottignoli
 *
 */

public interface IClassroomsDailyTime extends java.io.Serializable {
	
	/**
	 * Aggiunge una materia nell'orario giornaliero di una determinata aula. Deve essere possibile per un professore poter avere due aulee
	 * differenti alla stessa ora per la stessa materia, ma non che lo stesso professore insegni due materie differenti alla stessa ora in
	 * aulee diverse.
	 * 
	 * @param sub Materia da inserire.
	 * @param room Aula in cui viene tenuta la lezione.
	 * @param hour Ora di inizio della lezione.
	 * @param n Numero di ore consecutive in cui viene insegnata la materia passata come parametro partendo da hour.
	 * @throws IllegalArgumentException se room è null, se parte o tutte le ore selezionate nelle aulee diverse da room contengono una materia
	 * 									diversa sub ma con lo stesso professore, oppure nei casi specificati nel seguente metodo {@link IDailyTime#add(ISubject, int, int)}.
	 */
	void add(ISubject sub, Classrooms room, int hour, int n);
	
	/**
	 * Rimuove qualsiasi materia nell'orario giornaliero di una determinata aula nelle ore selezionate.
	 * 
	 * @param room Aula da cui eliminare le lezioni.
	 * @param hour Ora da cui iniziare ad eliminare le materie.
	 * @param n Numero di ore consecutive in cui eliminare le materie partendo da hour. 
	 * @throws IllegalArgumentException se room è null oppure nei casi specificati nel seguente metodo {@link IDailyTime#remove(int, int)}.
	 */
	void remove(Classrooms room, int hour, int n);
	
	/**
	 *  Restituisce la materia presente in una determinata ora in un'aula specifica.
	 * 
	 * @param room Aula in cui cercare la materia da restituire.
	 * @param hour Ora in cui cercare la materia da restituire.
	 * @return La materia presente nell'ora e nell'aula indicate. Si utilizza un Optional per evitare di restituire null {@link Optional} 
	 * 		   nel caso non vi sia alcuna materia nell'ora selezionata.
	 * @throws IllegalArgumentException se room è null oppure nei casi specificati nel seguente metodo {@link IDailyTime#getSubject(int)}.
	 */
	Optional<ISubject> getSubject(Classrooms room, int hour);
	
	/**
	 * Restituisce l'orario giornaliero di un'aula specifica.
	 * 
	 * @param room Aula da cui copiare l'orario giornaliero.
	 * @return Orario giornaliero dell'aula room.
	 * @throws IllegalArgumentException se room è null.
	 */
	IDailyTime getDailyTime(Classrooms room);
	
	/**
	 * Crea una copia di se stesso, in modo da rispettare il metodo {@link #equals(Object)}.
	 * 
	 * @return Una copia dell'oggetto che invoca questo metodo.
	 */
	IClassroomsDailyTime copy();
	
	/**
	 * Restituisce un set contenente in quali aulee è il professore in una determinata ora.
	 * 
	 * @param teach Nome del professore di cui ci interessa sapere dove ha lezione in una determinata ora.
	 * @param hour	Ora in cui cercare tra le varie aulee.
	 * @return Set contenente le aulee oppure set vuoto nel caso non stia insegnando nell'ora specificata o se teach è null.
	 */
	Set<Classrooms> whereTeaching(String teach, int hour);
	
	/**
	  * Restituisce un set contenente in quali aulee è tenuta una lezione in una determinata ora.
	 * 
	 * @param sub Materia di cui ci interessa sapere dove è tenuta in una determinata ora.
	 * @param hour	Ora in cui cercare tra le varie aulee.
	 * @return Set contenente le aulee oppure set vuoto nel caso non sia tenuta nell'ora specificata o se sub è null.
	 */
	Set<Classrooms> wherePerforming(ISubject sub, int hour);
}
