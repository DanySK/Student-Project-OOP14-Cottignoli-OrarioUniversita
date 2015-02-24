package model;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class ClassroomsDailyTimeImpl implements ClassroomsDailyTime{

	private Map<Classrooms, DailyTime> m = new HashMap<>();
	
	public ClassroomsDailyTimeImpl(){
		for (Classrooms c : Classrooms.values()){
			m.put(c, new DailyTimeImpl());
		}
	}

	@Override
	public void add(Lesson less, Classrooms room, int hour, int n) {
		checkNull(room);
		for (Classrooms s : m.keySet()){
			if (m.get(s).getlLessons(hour, n).contains(less)){
				throw new IllegalArgumentException("This lesson already exist at this time in another classroom");
			}
		}
		m.get(room).add(less, hour, n);
	}

	@Override
	public void remove(Classrooms room, int hour, int n) {
		checkNull(room);
		m.get(room).remove(hour, n);
	}

	@Override
	public Lesson getLesson(Classrooms room, int hour) {
		checkNull(room);
		return m.get(room).getLesson(hour);
	}

	@Override
	public Set<Lesson> getlLessons(Classrooms room, int hour, int n) {
		checkNull(room);
		return m.get(room).getlLessons(hour, n);
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((m == null) ? 0 : m.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ClassroomsDailyTimeImpl other = (ClassroomsDailyTimeImpl) obj;
		if (m == null) {
			if (other.m != null)
				return false;
		} else if (!m.equals(other.m))
			return false;
		return true;
	}
	

	@Override
	public String toString() {
		return "ClassroomsDailyTimeImpl [m=" + m + "]";
	}

	private void checkNull(final Object o){
		if (o == null){
			throw new NullPointerException();
		}
	}
}
