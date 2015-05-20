package controller;

import java.util.NoSuchElementException;

/**
 * Interfaccia generica che definisce uno Stack specifico per adempire meglio ad operazioni utili per implementare il pattern memento.
 * 
 * @author Lorenzo Cottignoli
 *
 * @param <X> Tipo generico utilizzato all'interno della classe.
 */
public interface ICaretaker<X> {
	
	/**
	 * Numero massimo di oggetti X che può contenere all'interno della sua struttura.
	 */
	int SIZE_MAX = 10; 
	
	/**
	 * Aggiunge l'oggetto x nella posizione sucessiva a {@link #getCurrentPos()} della struttura e aggiorna la posizione corrente. 
	 * Se vi sono elementi in posizioni maggiori rispetto a {@link #getCurrentPos()} allora verranno eliminati prima di inserire il nuovo oggetto.  
	 * Se la struttura contiene già {@link #SIZE_MAX} oggetti al suo interno allora l'oggetto in testa verrà eliminato prima di inserire 
	 * il nuovo oggetto.
	 * 
	 * @param x oggetto da aggiungere nella struttura.
	 */
	void add(X x);
	
	/**
	 * Restituisce la posizione corrente.
	 * 
	 * @return Restituisce la posizione corrente, se la struttura è vuora restituisce -1.
	 */
	int getCurrentPos();
	
	/**
	 * Restituisce l'oggetto nella posizione precedente all'oggetto in posizione {@link #getCurrentPos()}.
	 * 
	 * @return Oggetto precedente.
	 * @throws NoSuchElementException se {@link #prevExist()} restituisce false.
	 */
	X getPrev();
	
	/**
	 * Restituisce l'oggetto nella posizione successiva all'oggetto in posizione {@link #getCurrentPos()}.
	 * 
	 * @return Oggetto successivo.
	 * @throws NoSuchElementException se {@link #succExist()} restituisce false.
	 */
	X getSucc();
	
	/**
	 * Metodo per sapere se esiste un oggetto in posizione {@link #getCurrentPos()} - 1.
	 * 
	 * @return true se esiste, altrimenti false.
	 */
	boolean prevExist();
	
	/**
	 * Metodo per sapere se esiste un oggetto in posizione {@link #getCurrentPos()} + 1.
	 * 
	 * @return true se esiste, altrimenti false.
	 */
	boolean succExist();
}
