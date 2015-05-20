package view;

import java.util.List;

import controller.IController;

/**
 * Interfaccia che definisce la View del pattern architetturale MVC, cioè l'interfaccia grafica.
 * 
 * @author Lorenzo Cottignoli
 *
 */
public interface IView {
	
	/**
	 * Changes the current {@link IController}.
	 * 
	 * @param ctrl the new {@link IController}.
	 */
	void setController(IController ctrl);
	
	/**
	 * Displays a message window with an error message.
	 * 
	 * @param message the message to be displayed.
	 */
	void commandFailed(String message);
	
	/**
	 * Aggiunge una lista che rappresenta ciò che deve essere mostrato, starà poi alla view gestirlo in 
	 * maniera tale che i dati risultino comprensibili all'utente.
	 * 
	 * @param list Dati da mostrare.
	 */
	void addData(List<Object> list);
	
	/**
	 * Abilita/disabilita la possibilità di utilizzare il comando undo.
	 * 
	 * @param bool se è vero lo abilita, altrimenti lo disabilita.
	 */
	void setEnabledCommandUndo(boolean bool);
	
	/**
	 * Abilita/disabilita la possibilità di utilizzare il comando redo.
	 * 
	 * @param bool se è vero lo abilita, altrimenti lo disabilita.
	 */
	void setEnabledCommandRedo(boolean bool);
	
	/**
	 * Removes all the entries from this view.
	 */
	void clearData();
	
	/**
	 * Metodo per recuperare quale semestre l'utente vuole interrogare al momento.
	 * 
	 * @return Semestre selezionato dall'utente.
	 */
	int getSelectedSem();
}
