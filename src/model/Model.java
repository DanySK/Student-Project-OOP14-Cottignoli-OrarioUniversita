package model;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

public class Model implements IModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4369449608688351336L;
	
	private final Map<Integer, IWeeklyTime> semester = new HashMap<>();
	private Set<ISubject> subjects = new HashSet<>();
	
	public Model() {
		semester.put(FIRST_SEM, new WeeklyTime());
		semester.put(SEC_SEM, new WeeklyTime());
	}
	
	@Override
	public void add(final int sem, final Days d, final ISubject sub, final Classrooms room, final int hour, final int n) {
		checkSem(sem);
		semester.get(sem).add(d, sub, room, hour, n);
	}

	@Override
	public void remove(final int sem, final Days d, final Classrooms room, final int hour, final int n) {
		checkSem(sem);
		semester.get(sem).remove(d, room, hour, n);
	}
	
	@Override
	public Optional<ISubject> getSubject(final int sem, final Days d, final Classrooms room, final int hour) {
		checkSem(sem);
		return semester.get(sem).getSubject(d, room, hour);
	}
	
	@Override
	public void addSubject(final String sub, final String teach, final SubjectType type) {
		final ISubject su = new Subject(sub, teach, type);
		if (!subjects.contains(su)) {
			subjects.add(su);
		}
	}
	
	@Override
	public void removeSubject(final ISubject sub) {
		if (subjects.contains(sub)) {
			subjects.remove(sub);
		}
	}
	
	@Override
	public Set<ISubject> getSubjects() {
		return subjects;
	}
	
	private void checkSem(final int sem) {
		if (sem != FIRST_SEM && sem != SEC_SEM) {
			throw new IllegalArgumentException("Invalid semester");
		}
	}

	@Override
	public void setSubjects(Set<ISubject> set) {
		subjects = set;
	}
}
