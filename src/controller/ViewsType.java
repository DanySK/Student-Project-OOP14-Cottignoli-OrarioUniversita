package controller;

/**
 * Enumerator class per le varie tipologie di viste del modello.
 * 
 * @author Lorenzo Cottignoli
 *
 */
public enum ViewsType {

	/**
	 * Indica una vista totale dell'orario universitario.
	 */
	TOT("Total"),
	
	/**
	 * Indica una vista dell'orario universitario in base al professore.
	 */
	TEACH("Teacher"),
	
	/**
	 * Indica una vista dell'orario universitario in base alla materia.
	 */
	SUB("Subject"),
	
	/**
	 * Indica una vista dell'orario universitario in base all'aula.
	 */
	ROOM("Classroom"),
	
	/**
	 * Indica una vista dell'orario universitario in base al giorno.
	 */
	DAY("Day"),
	/**
	 * Indica una vista dell'orario universitario in base al tipo di materia.
	 */
	SUB_TYPE("Subject Type");
	
	private final String description;
	
	private ViewsType(final String desc) {
		description = desc;
	}
	
	/**
	 * @return La descrizione completa del tipo di vista.
	 */
	public String getDescription() {
		return description;
	}
}
