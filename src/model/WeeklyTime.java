package model;

import java.util.Optional;

public class WeeklyTime implements IWeeklyTime {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6266673430374084472L;
	
	private final IClassroomsDailyTime[] week = new IClassroomsDailyTime[Days.N_DAYS];
	
	public WeeklyTime() {
		for (int i = 0; i < Days.N_DAYS; i++) {
			week[i] = new ClassroomsDailyTime();
		}
	}
	
	@Override
	public void add(final Days d, final ISubject sub, final Classrooms room, final int hour, final int n) {
		week[d.getOrdinal()].add(sub, room, hour, n);
	}

	@Override
	public void remove(final Days d, final Classrooms room, final int hour, final int n) {
		week[d.getOrdinal()].remove(room, hour, n);
	}

	@Override
	public Optional<ISubject> getSubject(final Days d, final Classrooms room, final int hour) {
		return week[d.getOrdinal()].getSubject(room, hour);
	}


}
