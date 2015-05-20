package model;

import java.awt.Color;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Enumerator class che rappresenta le varie tipologie a cui appartengono le materie. Ogni tipologia ha un colore associato in modo da poterlo
 * sfruttare nella View per migliorare la leggibilità da parte dell'utente.
 * 
 * @author Lorenzo Cottignoli
 *
 */
public enum SubjectType {
	/**
	 * Esami del primo anno della laurea triennale.
	 */
	LT1("1° anno laurea triennale", Color.CYAN),
	
	/**
	 * Esami del secondo anno della laurea triennale.
	 */
	LT2("2° anno laurea triennale", Color.YELLOW),
	
	/**
	 * Esami del terzo anno della laurea triennale.
	 */
	LT3("3° anno laurea triennale", Color.LIGHT_GRAY),
	
	/**
	 * Esami del primo anno della laurea magistrale.
	 */
	LM1("1° anno laurea magistrale", Color.GREEN),
	
	/**
	 * Esami del secondo anno della laurea magistrale.
	 */
	LM2("2° anno laurea magistrale", Color.MAGENTA),
	
	/**
	 * Esami opzionali della laurea triennale.
	 */
	LTO("Opzionali laurea triennale", Color.ORANGE),
	
	/**
	 * Esami opzionali della laurea magistrale.
	 */
	LMO("Opzionali laurea magistrale", Color.PINK),
	
	/**
	 * Esami del terzo anno della laurea triennale dei ragazzi che hanno scelto l'indirizzo di scienze.
	 */
	PST("3° anno percorso scienze laurea triennale", Color.RED);
	
	private final String descr;
	private final Color color;
	
	private SubjectType(final String s, final Color c) {
		descr = s;
		color = c;
	}
	
	/**
	 * 
	 * @return descrizione completa del tipo.
	 */
	public String getDescription() {
		return descr;
	}
	
	/**
	 * 
	 * @return il colore associato ad ogni tipo.
	 */
	public Color getColor() {
		return color;
	}
	
	/**
	 * 
	 * @return Un Set che contiene i valori dell'Array restituito dal metodo {{@link #values()}.
	 */
	public static Set<SubjectType> getSubjectTypeValues() {
		return new HashSet<>(Arrays.asList(SubjectType.values()));
	}
}
