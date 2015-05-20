package model;

import java.util.Optional;
import java.util.Set;

/**
 * Interfaccia che definisce l'orario completo annuale e gestisce la lista di tutte le materie presenti nella facoltà di scienze 
 * e tecnologie informatiche di Cesena. Rappresenta il Model nel pattern architetturale MVC.
 * 
 * @author Lorenzo Cottignoli
 *
 */
public interface IModel extends java.io.Serializable {
	
	/**
	 * Costante per indicare il primo semestre.
	 */
	int FIRST_SEM = 0;
	
	/**
	 * Costante per indicare il secondo semestre.
	 */
	int SEC_SEM = 1;
	
	/**
	 * Aggiunge una materia nell'orario di una aula nelle ore selezionate, in un determinato giorno del semestre scelto.
	 * 
	 * @param sub Materia da aggiungere all'orario.
	 * @param sem Semestre in cui inserire la materia.
	 * @param d Giorno in cui inserire la materia.
	 * @param room Aula in cui inserire la materia.
	 * @param hour Ora di inizio della materia.
	 * @param n Numero di ore consecutive in cui inserire la materia.
	 * @throws IllegalArgumentException se sem non corrisponde ne a {@link #FIRST_SEM} ne a {@link #SEC_SEM} oppure nei casi
	 * 									specificati nel metodo {@link IWeeklyTime#add(ISubject, Days, Classrooms, int, int)}.  
	 */
	void add(ISubject sub, int sem, Days d, Classrooms room, int hour, int n);
			
	/**
	 * Rimuove qualsiasi materia nell'orario di una aula nelle ore selezionate, di un determinato giorno del semestre scelto.
	 * 
	 * @param sem Semestre in cui eliminare la materia.
	 * @param d Giorno in cui eliminare la materia.
	 * @param room Aula in cui eliminare la materia.
	 * @param hour Ora da cui iniziare ad eliminare.
	 * @param n Numero di ore consecutive da eliminare.
	 * @throws IllegalArgumentException se sem non corrisponde ne a {@link #FIRST_SEM} ne a {@link #SEC_SEM} oppure nei casi
	 * 									specificati nel metodo {@link IWeeklyTime#remove(Days, Classrooms, int, int)}.
	 */
	void remove(int sem, Days d, Classrooms room, int hour, int n);
	
	/**
	 * Crea una nuova materia e la aggiunge alla lista delle materie disponibili, nel caso vi sia già quella materia nella lista 
	 * non aggiunge nulla.
	 * 
	 * @param sub Nome della materia.
	 * @param teach Nome del professore.
	 * @param type Tipo di materia.
	 * @throws IllegalArgumentException se uno dei parametri è null.
	 */
	void addSubject(String sub, String teach, SubjectType type);
	
	/**
	 * Rimuove una materia dalla lista delle materie disponibili, nel caso la materia passata come parametro non vi sia non succede nulla.
	 * 
	 * @param sub Materia da eliminare dalla lista.
	 */
	void removeSubject(ISubject sub);
		
	/**
	 * Restituisce la materia presente in un'aula in un giorno specifico ad una determinata ora del semestre scelto. 
	 * 
	 * @param sem Semestere in cui cercare la materia da restituire.
	 * @param d Giorno in cui cercare la materia da restituire.
	 * @param room Aula in cui cercare la materia da restituire.
	 * @param hour Ora in cui cercare la materia da restituire.
	 * @return La materia presente nell'ora, nell'aula, giorno e nel semestre indicati. Si utilizza un Optional per evitare di restituire null 
	 * 		   {@link Optional} nel caso non vi sia alcuna materia nell'ora selezionata.
	 * @throws IllegalArgumentException se sem non corrisponde ne a {@link #FIRST_SEM} ne a {@link #SEC_SEM} oppure nei casi
	 * 									specificati nel metodo {@link IWeeklyTime#getSubject(Days, Classrooms, int)}.
	 */
	Optional<ISubject> getSubject(int sem, Days d, Classrooms room, int hour);
	
	/**
	 * Restituisce una copia della lista delle materie disponibili.
	 * 
	 * @return Set contente tutte le materie.
	 */
	Set<ISubject> getSubjects();
	
	/**
	 * Permette di importare la lista delle materie disponibili.
	 * 
	 * @param set Set contenente la nuova lista di materie.
	 */
	void setSubjects(Set<ISubject> set);
	
	/**
	 * Restituisce un set contenente in quali aulee è il professore in una determinata ora del giorno e del semestre scelti.
	 * 
	 * @param teach Nome del professore di cui ci interessa sapere dove ha lezione in una determinata ora.
	 * @param sem Semestre in cui cercare.
	 * @param d Giorno in cui cercare.
	 * @param hour Ora in cui cercare tra le varie aulee.
	 * @return Set contenente le aulee oppure set vuoto nel caso non stia insegnando nell'ora specificata o se teach è null.
	 * @throws IllegalArgumentException se sem non corrisponde ne a {@link #FIRST_SEM} ne a {@link #SEC_SEM} oppure nei casi
	 * 									specificati nel metodo {@link IWeeklyTime#whereTeaching(String, Days, int)}.
	 */
	Set<Classrooms> whereTeaching(String teach, int sem, Days d, int hour);
	
	/**
	 * Restituisce un set contenente in quali aulee è tenuta una materia in una determinata ora del giorno e del semestre scelti.
	 * 
	 * @param sub Materia di cui ci interessa sapere dove è tenuta in una determinata ora.
	 * @param sem Semestre in cui cercare.
	 * @param d Giorno in cui cercare.
	 * @param hour Ora in cui cercare tra le varie aulee.
	 * @return Set contenente le aulee oppure set vuoto nel caso non stia insegnando nell'ora specificata o se teach è null.
	 * @throws IllegalArgumentException se sem non corrisponde ne a {@link #FIRST_SEM} ne a {@link #SEC_SEM} oppure nei casi
	 * 									specificati nel metodo {@link IWeeklyTime#wherePerforming(ISubject, Days, int)}.
	 */
	Set<Classrooms> wherePerforming(ISubject sub, int sem, Days d, int hour);
	
	/**
	 * Restituisce un set con tutti il nome di tutti i professori.
	 * 
	 * @return Set con i nomi dei professori.
	 */
	Set<String> getTeachers();
	
	/**
	 * Metodo per creare un memento, cioè crare un oggetto in cui è inserito lo stato attuale della struttura.
	 * 
	 * @return l'oggetto Memento.
	 */
	IMemento createMemento();
	
	/**
	 * Metodo per settare il modello ad uno stato precedente che dipende dal contenuto di mem.
	 * 
	 * @param mem Stato precedente a cui vogliamo far tornare il modello.
	 * @throws IllegalArgumentException se mem o {@link IMemento#getState()} sono null.
	 */
	void setMemento(IMemento mem);
	
	/**
	 * Interfaccia che definisce il memento, cioè un oggetto che contiene lo stato in cui si trova la struttura in un determinato istante. 
	 * Serve per poter realizzare il pattern Memento.
	 * 
	 * @author Lorenzo Cottignoli
	 *
	 */
	interface IMemento {
		
		/**
		 * Metodo per salvare lo stato della struttura all'interno di questo oggetto.
		 * 
		 * @param state Stato da salvare.
		 * @throws IllegalArgumentException se state è null. 
		 */
		void setState(Object state);
		/**
		 * Restituisce lo stato della struttura che vi è salvato all'interno di questo oggetto.
		 * 
		 * @return Stato salvato.
		 */
		Object getState();
	}
}
