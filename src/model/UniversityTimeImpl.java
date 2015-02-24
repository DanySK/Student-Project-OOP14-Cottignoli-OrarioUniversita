package model;

public class UniversityTimeImpl implements UniversityTime{

	private final WeeklyHours first = new WeeklyHoursImpl();
	private final WeeklyHours second = new WeeklyHoursImpl();
	
	public UniversityTimeImpl(){}
	
	@Override
	public void add(Days d, Lesson less, Classrooms room, int hour, int n) {
		if (less.getSemester() == Lesson.FIRST_SEM){ 
			first.add(d, less, room, hour, n);
		}else{
			second.add(d, less, room, hour, n);
		}
	}

	@Override
	public void remove(int sem, Days d, Classrooms room, int hour, int n) {
		if (sem != Lesson.FIRST_SEM && sem != Lesson.SEC_SEM){
			throw new IllegalArgumentException("Invalid semester");
		}
		
		if (sem == Lesson.FIRST_SEM){ 
			first.remove(d, room, hour, n);
		}else{
			second.remove(d, room, hour, n);
		}
	}
	
}
