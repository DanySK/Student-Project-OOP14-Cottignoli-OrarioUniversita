package model;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

public class ClassroomsDailyTime implements IClassroomsDailyTime{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3887851499745557411L;
	
	private final Map<Classrooms, IDailyTime> m = new HashMap<>();
	
	public ClassroomsDailyTime() {
		for (final Classrooms c : Classrooms.values()) {
			m.put(c, new DailyTime());
		}
	}

	@Override
	public void add(final ISubject sub, final Classrooms room, final int hour, final int n) {
		checkNull(room);
		for (final Classrooms s : m.keySet()) {
			if (m.get(s).getSubjects(hour, n).contains(sub)) {
				throw new IllegalArgumentException("This lesson already exist at this time in another classroom");
			}
		}
		m.get(room).add(sub, hour, n);
	}

	@Override
	public void remove(final Classrooms room, final int hour, final int n) {
		checkNull(room);
		m.get(room).remove(hour, n);
	}

	@Override
	public Optional<ISubject> getSubject(final Classrooms room, final int hour) {
		checkNull(room);
		return m.get(room).getSubject(hour);
	}

	@Override
	public Set<ISubject> getSubjects(final Classrooms room, final int hour, final int n) {
		checkNull(room);
		return m.get(room).getSubjects(hour, n);
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((m == null) ? 0 : m.hashCode());
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
		final ClassroomsDailyTime other = (ClassroomsDailyTime) obj;
		if (m == null) {
			if (other.m != null) {
				return false;
			}
		} else if (!m.equals(other.m)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "ClassroomsDailyTimeImpl [m=" + m + "]";
	}

	private void checkNull(final Object o) {
		if (o == null) {
			throw new NullPointerException();
		}
	}
}
