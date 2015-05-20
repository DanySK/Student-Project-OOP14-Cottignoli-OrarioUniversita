package model;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

/**
 * 
 * Implementazione dell'interfaccia {@link IWeeklyTime} tramite l'utilizzo di una Map che come chiave ha i valori di 
 * {@link Days#values()} ed associato ad ognuna un {@link IClassroomsDailyTime}.
 * 
 * @author Lorenzo Cottignoli
 * 
 */
public class WeeklyTime implements IWeeklyTime {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6266673430374084472L;
	
	private final Map<Days, IClassroomsDailyTime> week = new HashMap<>();
	
	/**
	 * Costruttore che crea una struttura vuota.
	 */
	public WeeklyTime() {
		for (final Days d : Days.values()) {
			week.put(d, new ClassroomsDailyTime());
		}
	}
	
	/**
	 * Costruttore che crea una nuova struttura copiando quella passata come parametro.
	 * 
	 * @param wt struttura da copiare.
	 * @throws IllegalArgumentException se wt è null.
	 */
	public WeeklyTime(final IWeeklyTime wt) {
		if (wt == null) {
			throw new IllegalArgumentException("the parameter 'wt' can't be null!");
		}
		for (final Days d : Days.values()) {
			week.put(d, wt.getClassroomDailyTime(d));
		}
	}
	
	@Override
	public void add(final ISubject sub, final Days d, final Classrooms room, final int hour, final int n) {
		checkDay(d);
		week.get(d).add(sub, room, hour, n);
	}

	@Override
	public void remove(final Days d, final Classrooms room, final int hour, final int n) {
		checkDay(d);
		week.get(d).remove(room, hour, n);
	}

	@Override
	public Optional<ISubject> getSubject(final Days d, final Classrooms room, final int hour) {
		checkDay(d);
		return week.get(d).getSubject(room, hour);
	}
	
	@Override
	public IClassroomsDailyTime getClassroomDailyTime(final Days d) {
		checkDay(d);
		return week.get(d).copy();
	}

	@Override
	public IWeeklyTime copy() {
		return new WeeklyTime(this);
	}
	
	@Override
	public Set<Classrooms> whereTeaching(final String teach, final Days d, final int hour) {
		checkDay(d);
		return week.get(d).whereTeaching(teach, hour);
	}
	
	@Override
	public Set<Classrooms> wherePerforming(final ISubject sub, final Days d, final int hour) {
		checkDay(d);
		return week.get(d).wherePerforming(sub, hour);
	}

	/**
	 * Metodo per controllare se un giorno è uguale a null.
	 * 
	 * @param d Giorno da controllare.
	 * @throws IllegalArgumentException se d è null.
	 */
	private void checkDay(final Days d) {
		if (d == null) {
			throw new IllegalArgumentException("The day can't be null!");
		}
	}
}
