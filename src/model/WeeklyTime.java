package model;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class WeeklyTime implements IWeeklyTime {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6266673430374084472L;
	
	private final Map<Days, IClassroomsDailyTime> week = new HashMap<>();
	
	public WeeklyTime() {
		for (final Days d : Days.values()) {
			week.put(d, new ClassroomsDailyTime());
		}
	}
	
	public WeeklyTime(final IWeeklyTime wt) {
		for (final Days d : Days.values()) {
			week.put(d, wt.getClassroomDailyTime(d));
		}
	}
	
	@Override
	public void add(final Days d, final ISubject sub, final Classrooms room, final int hour, final int n) throws WrongInputException {
		checkDay(d);
		week.get(d).add(sub, room, hour, n);
	}

	@Override
	public void remove(final Days d, final Classrooms room, final int hour, final int n) throws WrongInputException {
		checkDay(d);
		week.get(d).remove(room, hour, n);
	}

	@Override
	public Optional<ISubject> getSubject(final Days d, final Classrooms room, final int hour) throws WrongInputException {
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
	
	//provaaaaaaaaaaaaaaa
	@Override
	public Classrooms whereTeaching(final String teach, final int hour, final Days d) throws WrongInputException {
		checkDay(d);
		return week.get(d).whereTeaching(teach, hour);
	}
	
	//provaaaaaaaaaaaaaa
	@Override
	public Classrooms wherePerforming(final ISubject sub, final int hour, final Days d) throws WrongInputException {
		checkDay(d);
		return week.get(d).wherePerforming(sub, hour);
	}

	private void checkDay(final Days d) {
		if (d == null) {
			throw new IllegalArgumentException("The day can't be null!");
		}
	}
}
