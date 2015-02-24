package model;

public class WeeklyHoursImpl implements WeeklyHours{

	private final ClassroomsDailyTime[] week = new ClassroomsDailyTime[Days.N_DAYS];
	
	public WeeklyHoursImpl(){
		for (int i = 0; i < Days.N_DAYS; i++){
			week[i] = new ClassroomsDailyTimeImpl();
		}
	}
	
	@Override
	public void add(Days d, Lesson less, Classrooms room, int hour, int n) {
		week[d.getOrdinal()].add(less, room, hour, n);
	}

	@Override
	public void remove(Days d, Classrooms room, int hour, int n) {
		week[d.getOrdinal()].remove(room, hour, n);
	}


}
