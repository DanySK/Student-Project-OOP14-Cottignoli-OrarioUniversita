package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Implementazione dell'interfaccia {@link IDailyTime} tramite l'ultilizzo di una List di {@value IDailyTime#HOURS} elementi 
 * che rappresentano le varie ore della giornata.
 * 
 * @author Lorenzo Cottignoli
 *
 */
public class DailyTime implements IDailyTime {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4080899701940069606L;
	
	private final List<ISubject> daily = new ArrayList<ISubject>();
	
	/**
	 * Crea un nuovo orario giornaliero con tutte le ore libere, cioè a null.
	 */
	public DailyTime() {
		for (int i = 0; i < HOURS; i++) {
			daily.add(null);
		}
	}
	
	/**
	 * Crea un nuovo orario giornaliero copiando quello passato come parametro.
	 * 
	 * @param dt Orario giornaliero da copiare.
	 * @throws IllegalArgumentException se il parametro dt è null.
	 */
	public DailyTime(final IDailyTime dt) {
		if (dt == null) {
			throw new IllegalArgumentException("the parameter 'dt' can't be null!");
		}
		for (int i = 0; i < HOURS; i++) {
			daily.add(dt.getSubject(i + FIRST_HOUR).orElse(null));
		}
	}
	

	@Override
	public void add(final ISubject sub, final int hour, final int n) {
		checkHour(hour, n);
		for (int i = 0; i < n; i++) {
			if (daily.get(hour - FIRST_HOUR + i) != null) {
				throw new IllegalArgumentException("These hours are already busy");
			}
		}
		
		for (int i = 0; i < n; i++) {
			daily.set(hour - FIRST_HOUR + i, sub);
		}
	}
	
	@Override
	public void remove(final int hour, final int n) {
		checkHour(hour, n);
		for (int i = 0; i < n; i++) {
			daily.set(hour - FIRST_HOUR + i, null);
		}
	}
	
	@Override
	public Optional<ISubject> getSubject(final int hour) {
		checkHour(hour);
		return Optional.ofNullable(daily.get(hour - FIRST_HOUR));
	}
	
	@Override
	public IDailyTime copy() {
		return new DailyTime(this);
	}
	
	/**
	 * Esegue il controllo su hour come specificato nel metodo {@link #getSubject(int)}.
	 * 
	 * @param hour Ora da controllare il valore.
	 */
	private void checkHour(final int h) {
		if (h < FIRST_HOUR || h >= (FIRST_HOUR + HOURS)) {
			throw new IllegalArgumentException("Wrong hour! Must be beetween " + FIRST_HOUR + " to " + (FIRST_HOUR + HOURS - 1));
		}
	}
	
	/**
	 * Esegue tutti i controlli dichiarati nel metodo {@link #add(ISubject, int, int)} e nel metodo {@link #remove(int, int)}.
	 * 
	 * @param hour Ora di inizio.
	 * @param n Numero di ore consecutive a partire da hour.
	 */
	private void checkHour(final int hour, final int n) {
		checkHour(hour);
		if (n <= 0) {
			throw new IllegalArgumentException("Wrong number of hour! Must be greater than 0!");
		}
		if ((hour + n) > (FIRST_HOUR + HOURS)) {
			throw new IllegalArgumentException("Wrong number of hour! Hour + Number must not be greater than " + (FIRST_HOUR + HOURS));
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((daily == null) ? 0 : daily.hashCode());
		return result;
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final DailyTime other = (DailyTime) obj;
		if (daily == null) {
			if (other.daily != null) {
				return false;
			}
		} else if (!daily.equals(other.daily)) {
			return false;
		}
		return true;
	}
	
}
