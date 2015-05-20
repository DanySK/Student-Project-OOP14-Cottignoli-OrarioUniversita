package model;
/**
 * Interfaccia che definisce le operazioni standard di una materia.
 * 
 * @author Lorenzo Cottignoli
 *
 */
public interface ISubject extends java.io.Serializable {
	
	/**
	 * 
	 * @return Nome della materia.
	 */
	String getSubName();
	/**
	 * 
	 * @return Nome del professore che tiene questa lezione.
	 */
	String getTeachName();

	/**
	 * 
	 * @return Tipologia di materia.
	 */
	SubjectType getSubjectType();
}
