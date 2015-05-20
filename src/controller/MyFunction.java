package controller;

import model.Days;

/**
 * Interfaccia funzionale che prende in ingresso semestre, giorno e ora e restituisce un tipo generico. Viene utilizzata nella classe
 * {@link ViewController#setViewType(view.IView, Object)} per gestire alcuni tipi di viste del modello.
 * 
 * @author Lorenzo Cottignoli
 *
 * @param <Y> Tipo generico di ritorno.
 */
public interface MyFunction<Y> {

	/**
	 * @param sem Semestre.
	 * @param d Giorno.
	 * @param hour Ora.
	 * @return Tipo generico.
	 */
	Y apply(int sem, Days d, int hour);
}
