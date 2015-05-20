package model;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Enumerator class utilizzata per rappresentare le aule disponibili nella facoltà di scienze e tecnologie informatiche di Cesena.
 * 
 * @author Lorenzo Cottignoli
 *
 */
public enum Classrooms {
	
	/**
	 * Aula Magna.
	 */
	MAGNA("Aula magna"),
	
	/**
	 * Aula A.
	 */
	A("Aula A"),
	
	/**
	 * Aula B.
	 */
	B("Aula B"),
	
	/**
	 * Aula C.
	 */
	C("Aula C"),
	
	/**
	 * Aula D.
	 */
	D("Aula D"),
	
	/**
	 * Aula E.
	 */
	E("Aula E"),
	
	/**
	 * Laboratorio Vela.
	 */
	VELA("Laboratorio vela"),
	
	/**
	 * Laboratorio 2.
	 */
	LAB2("Laboratorio 2"),
	
	/**
	 * Laboratorio 3.
	 */
	LAB3("Laboratorio 3"),
	
	/**
	 * Aula G piano terra.
	 */
	GPT("Aula G PT"),
	
	/**
	 * Aula G primo piano.
	 */
	GP1("Aula G P1");
	
	private final String name;
	
	private Classrooms(final String s) {
		name = s;
	}
	
	/**
	 * 
	 * @return Il nome completo dell'aula.
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * 
	 * @return Un Set che contiene i valori dell'Array restituito dal metodo {{@link #values()}.
	 */
	public static Set<Classrooms> getClassroomsValues() {
		return new HashSet<>(Arrays.asList(Classrooms.values()));
	}
}
