package controller;

import model.IModel;
import view.IView;

/**
 * Interfaccia che definisce metodi relativi ai comandi di gestione del tipo di vista da mostrare e di undo/redo. Questa interfaccia serve 
 * per rendere indipendente l'implementazione di alcuni comandi, in questo modo si può cambiare l'implementazione dei comandi elencati 
 * precedentemente (seguendo le specifiche di questa interfaccia) senza dover modificare completamente la classe {@link Controller}.
 * 
 * @author Lorenzo Cottignoli
 *
 */
public interface IViewController {

	/**
	 * Metodo che permette di caricare un determinato tipo di vista sulla IView passata in ingresso in base all'Object passato come parametro.
	 * - se obj è null carica una vista totale dell'orario.
	 * - se obj è una {@link String} allora corrisponde al nome di un professore e quindi carica una vista dell'orario in base al nome del 
	 *   professore.
	 * - se obj è un {@link Days} allora carica una vista dell'orario in base al giorno passato.
	 * - se obj è un {@link ISubject} allora carica una vista dell'orario in base alla materia selezionata.
	 * - se obj è un {@link Classrooms} allora carica una vista dell'orario in base all'aula scelta.
	 * - se obj è un {@link SubjectType} allora carica una vista dell'orario in base alla tipologia di materia scelta 
	 * 
	 * @param v Vista su cui caricare la tipologia scelta.
	 * @param ob Oggetto che permette di decidere quale tipologia scegliere.
	 */
	void setViewType(IView v, Object ob);
	
	/**
	 * Metodo per definire su quale modello effettuare le varie operazioni.
	 * 
	 * @param m Modello su cui lavorare.
	 */
	void setModel(IModel m);
	
	/**
	 * Restituisce il {@link IModel.IMemento} precedente se esiste, cioè lo stato in cui si trovava il modello prima dell'ultima modifica.
	 * 
	 * @return Stato precedente del modello.
	 * @throws NoSuchElementException se il precedente non esiste.
	 */
	IModel.IMemento getPrevMemento();
	
	/**
	 * Restituisce il {@link IModel.IMemento} successivo se esiste, cioè lo stato in cui si trovava il modello prima di annullare l'ultima
	 * modifica.
	 * 
	 * @return Stato successivo del modello.
	 * @throws NoSuchElementException se il successivo non esiste.
	 */
	IModel.IMemento getSuccMemento();
	
	/**
	 * Utilizza il metodo {@link IModel#createMemento()} e lo tiene salvato all'interno di una struttura apposita.
	 */
	void createMemento();
	
	/**
	 * Resetta la struttura che mantiene salvati tutti gli stati del modello salvati in precedenza.
	 */
	void resetCaretaker();
	
	/**
	 * Aggiorna la IView passata come parametro, scegliendo la tipologia di vista in base all'ultimo {@link #setViewType(IView, Object)}
	 * effettuato, nel caso non sia mai stato invocato quel metodo allora lo gestiste come se obj fosse null.
	 * 
	 * @param v IView da aggiornare.
	 */
	void updateViews(IView v);
	
	/**
	 * Metodo che abilita/disabilita i comandi undo/redo a livello della view, in base se esistono o meno stati precedenti e/o successivi 
	 * salvati del modello.
	 * 
	 * @param v IView su cui abilitare/disabilitare i comandi undo/redo.
	 */
	void undoRedo(IView v);
}
