package model;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Classe enumerator per rappresentare i giorni in cui l'università tiene lezioni.
 * 
 * @author Lorenzo Cottignoli
 *
 */
public enum Days {
	
	/**
	 * Monday.
	 */
	MONDAY("Monday"), 
	
	/**
	 * Tuesday.
	 */
	TUESDAY("Tuesday"), 
	
	/**
	 * Wednesday.
	 */
	WEDNESDEY("Wednesday"), 
	
	/**
	 * Thursday.
	 */
	THURSDAY("Thursday"), 
	
	/**
	 * Friday.
	 */
	FRIDAY("Friday");
	
	private final String name;
	
	private Days(final String s) {
		name = s;
	}
	
	/**
	 * 
	 * @return una String contenente il nome del giorno.
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * 
	 * @return Un Set che contiene i valori dell'Array restituito dal metodo {{@link #values()}.
	 */
	public static Set<Days> getDaysValues() {
		return new HashSet<>(Arrays.asList(Days.values()));
	}
}
