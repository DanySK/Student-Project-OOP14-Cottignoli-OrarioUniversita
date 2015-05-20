package controller;

import java.util.Set;

import model.Classrooms;
import model.Days;
import model.IModel;
import model.ISubject;
import model.SubjectType;
import view.IView;

/**
 * Interfaccia che definisce il Controller del pattern architetturale MVC, cioè le operazioni 
 * utilizzabili dalla {@link IView} sul {@link IModel} riducendo il più possibile le dipendenze tra le due
 * entità. Deve poter gestire più IView alla volta.
 * 
 * @author Lorenzo Cottignoli
 *
 */
public interface IController {
	
	/**
	 * Adds a view to this controller.
	 * 
	 * @param v the view to add
	 */
	void addView(IView v);
	
	/**
	 * Removes a view from this controller.
	 * 
	 * @param v the view to remove
	 */
	void removeView(IView v);
	
	/**
	 * Richiede al controller di caricare una determinata tipologia di vista. More informations
	 * {@link IViewController#setViewType(IView, Object)}.
	 * 
	 * @param ob Oggetto per la scelta del tipo di vista.
	 */
	void setViewType(Object ob);
	
	/**
	 *  Refreshes the status of each attached. More informations {@link IViewController#updateViews(IView)}.
	 */
	void updateViews();
	
	/**
	 * 
	 * @return L'elenco delle materie disponibili.
	 */
	Set<ISubject> getSubjectsList();
	
	/**
	 * 
	 * @return L'elenco dei professori disponibili.
	 */
	Set<String> getTeachersList();
	
	/**
	 * Crea un nuovo modello su cui poter lavorare.
	 */
	void commandNew();
	
	/**
	 * Creazione di una nuova materia {@link IModel#addSubject(String, String, SubjectType)}.
	 * 
	 * @param sub Nome della materia.
	 * @param teach Nome del professore.
	 * @param type Tipo di materia.
	 */
	void commandAddSubject(String sub, String teach, SubjectType type);
	
	/**
	 * Rimozione di una materia {@link IModel#removeSubject(ISubject)}.
	 * Nel caso vi siano valori non accettabili in input avverte la view dell'errore tramite il metodo
	 * {@link IView#commandFailed(String)}.
	 * 
	 * @param sub Materia da rimuovere dalla lista.
	 */
	void commandRemoveSubject(ISubject sub);
	
	/**
	 * Salvataggio della lista delle materie su file. Nel caso si verifichi qualche errore durante il salvataggio 
	 * avverte la view dell'errore tramite il metodo {@link IView#commandFailed(String)}.
	 * 
	 * @param fileName Nome del file su cui salvare la lista.
	 */
	void commandExportSubjectList(String fileName);
	
	/**
	 * Caricamento della lista delle materie da file. Nel caso si verifichi qualche errore durante il caricamento 
	 * avverte la view dell'errore tramite il metodo {@link IView#commandFailed(String)}.
	 * 
	 * @param fileName Nome del file da cui carire la lista.
	 */
	void commandImportSubjectList(String fileName);
	
	/**
	 * Aggiunge una materia all'orario {@link IModel#add(ISubject, int, Days, Classrooms, int, int)}.
	 * Nel caso vi siano valori non accettabili in input avverte la view dell'errore tramite il metodo
	 * {@link IView#commandFailed(String)}.
	 * 
	 * @param sub Materia da inserire.
	 * @param sem Semestre in cui inserire la materia.
	 * @param d Giorno in cui inserire la materia.
	 * @param room Aula in cui inserire la materia.
	 * @param hour Ora di inizio della materia.
	 * @param n Numero di ore consecutive in cui inserirla.
	 */
	void commandAdd(ISubject sub, int sem, final Days d, Classrooms room, int hour, int n);
	
	/**
	 * Rimuove una qualsiasi materia dall'orario {@link IModel#remove(int, Days, Classrooms, int, int)}.
	 * Nel caso vi siano valori non accettabili in input avverte la view dell'errore tramite il metodo
	 * {@link IView#commandFailed(String)}.
	 * 
	 * @param sem Semestre da cui eliminare.
	 * @param d Giorno da cui eliminare.
	 * @param room Aula da cui eliminare.
	 * @param hour Ora da cui inizare ad eliminare.
	 * @param n Numero di ore consecutive da eliminare.
	 */
	void commandRemove(int sem, Days d, Classrooms room, int hour, int n);
	
	/**
	 * Salvataggio su file dell'intero modello. Nel caso si verifichi qualche errore durante il salvataggio 
	 * avverte la view dell'errore tramite il metodo {@link IView#commandFailed(String)}.
	 * 
	 * @param fileName Nome del file su cui salvare il modello.
	 */
	void commandSave(String fileName);
	
	/**
	 * Caricamento da file dell'intero modello. Nel caso si verifichi qualche errore durante il caricamento 
	 * avverte la view dell'errore tramite il metodo {@link IView#commandFailed(String)}.
	 * 
	 * @param fileName Nome del file da cui caricare il modello.
	 */
	void commandLoad(String fileName);
	
	/**
	 * Comando undo.
	 */
	void commandUndo();
	
	/**
	 * Comando redo.
	 */
	void commandRedo();
	 
}
