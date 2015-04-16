package model;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class ClassroomsDailyTime implements IClassroomsDailyTime {

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
	
	public ClassroomsDailyTime(IClassroomsDailyTime cdt) {
		for (final Classrooms c : Classrooms.values()) {
			m.put(c, cdt.getDailyTime(c));
		}
	}

	@Override
	public void add(final ISubject sub, final Classrooms room, final int hour, final int n) throws WrongInputException {
		checkRoom(room);
		m.get(room).add(sub, hour, n);
	}

	@Override
	public void remove(final Classrooms room, final int hour, final int n) throws WrongInputException {
		checkRoom(room);
		m.get(room).remove(hour, n);
	}

	@Override
	public Optional<ISubject> getSubject(final Classrooms room, final int hour) throws WrongInputException {
		checkRoom(room);
		return m.get(room).getSubject(hour);
	}
	
	@Override
	public IDailyTime getDailyTime(final Classrooms room) {
		checkRoom(room);
		return  m.get(room).copy();
	}
	 
	@Override
	public IClassroomsDailyTime copy() {
		return new ClassroomsDailyTime(this);
	}
	
	//provaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa..si può migliorare restituendo un set<classrooms>
	@Override
	public Classrooms whereTeaching(final String teach, final int hour) throws WrongInputException {
		for (final Classrooms room : Classrooms.values()) {
			if (m.get(room).getSubject(hour).isPresent() && m.get(room).getSubject(hour).get().getTeachName().equals(teach)) {
				return room;
			}
		}
		return null;
	}
	
	//provaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa..si può migliorare restituendo un set<classrooms>
	@Override
	public Classrooms wherePerforming(final ISubject sub, final int hour) throws WrongInputException {
		for (final Classrooms room : Classrooms.values()) {
			if (m.get(room).getSubject(hour).isPresent() && m.get(room).getSubject(hour).get().equals(sub)) {
				return room;
			}
		}
		return null;
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

	private void checkRoom(final Classrooms room) {
		if (room == null) {
			throw new IllegalArgumentException("The classroom can't be null!");
		}
	}
}
