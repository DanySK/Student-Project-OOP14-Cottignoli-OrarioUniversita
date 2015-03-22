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
	
	public DailyTime(){
		for (int i = 0; i < HOURS; i++) {
			daily.add(null);
		}
	}
	

	@Override
	public void add(final ISubject sub, final int hour, final int n) {
		checkHour(hour, n);
		for (int i = 0; i < n; i++) {
			if (daily.get(hour - FIRST_HOUR + i) != null) {
				throw new IllegalArgumentException("this hours are already busy");
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
	public Set<ISubject> getSubjects(final int hour, final int n) {
		checkHour(hour, n);
		final Set<ISubject> setLess = new HashSet<>();
		for (int i = 0; i < n; i++) {
			if (getSubject(hour + i).isPresent()) {
				setLess.add(getSubject(hour + i).get());
			}
		}
		return setLess;
	}
	
	//controlla che l'ora inserita sia compresa tra le 9 e le 17 altrimenti tira un errore
	private void checkHour(final int h) {
		if (h < FIRST_HOUR || h >= (FIRST_HOUR + HOURS)) {
			throw new IllegalArgumentException("Wrong hour! Must be beetween 9 to 17");
		}
	}
	
	//controlla che l'ora + il numero di ore per cui va effettuata una determinata operazione non sia superiore a 17, altrimenti errore
	private void checkHour(final int h, final int n) {
		checkHour(h);
		if (n <= 0) {
			throw new IllegalArgumentException("Wrong number of hour! Must be greater than 0");
		}
		if ((h + n) >= (FIRST_HOUR + HOURS)) {
			throw new IllegalArgumentException("Wrong number of hour! Hour + Number must not be greater than 17");
		}
	}
}
