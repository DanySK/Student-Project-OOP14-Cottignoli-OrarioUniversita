package model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public class DailyTime implements IDailyTime {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4080899701940069606L;
	
	private final List<ISubject> daily = new ArrayList<ISubject>();
	
	public DailyTime() {
		for (int i = 0; i < HOURS; i++) {
			daily.add(null);
		}
	}
	
	public DailyTime(final IDailyTime dt) {
		for (int i = 0; i < HOURS; i++) {
			try {
				daily.add(dt.getSubject(i + FIRST_HOUR).orElse(null));
			} catch (WrongInputException e) {
				e.printStackTrace();
			}
		}
	}
	

	@Override
	public void add(final ISubject sub, final int hour, final int n) throws WrongInputException {
		checkHour(hour, n);
		for (int i = 0; i < n; i++) {
			if (daily.get(hour - FIRST_HOUR + i) != null) {
				throw new WrongInputException("These hours are already busy");
			}
		}
		
		for (int i = 0; i < n; i++) {
			daily.set(hour - FIRST_HOUR + i, sub);
		}
	}
	
	@Override
	public void remove(final int hour, final int n) throws WrongInputException {
		checkHour(hour, n);
		for (int i = 0; i < n; i++) {
			daily.set(hour - FIRST_HOUR + i, null);
		}
	}
	
	@Override
	public Optional<ISubject> getSubject(final int hour) throws WrongInputException {
		checkHour(hour);
		return Optional.ofNullable(daily.get(hour - FIRST_HOUR));
	}
	
	@Override
	public IDailyTime copy() {
		return new DailyTime(this);
	}
	
	//controlla che l'ora inserita sia compresa tra le 9 e le 17 altrimenti tira un errore
	private void checkHour(final int h) throws WrongInputException {
		if (h < FIRST_HOUR || h >= (FIRST_HOUR + HOURS)) {
			throw new WrongInputException("Wrong hour! Must be beetween 9 to 17");
		}
	}
	
	//controlla che l'ora + il numero di ore per cui va effettuata una determinata operazione non sia superiore a 17, altrimenti errore
	private void checkHour(final int h, final int n) throws WrongInputException {
		checkHour(h);
		if (n <= 0) {
			throw new WrongInputException("Wrong number of hour! Must be greater than 0");
		}
		if ((h + n) > (FIRST_HOUR + HOURS)) {
			throw new WrongInputException("Wrong number of hour! Hour + Number must not be greater than 17");
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
