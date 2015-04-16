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
	
	private Map<Integer, IWeeklyTime> semester = new HashMap<>();
	private Set<ISubject> subjects = new HashSet<>();
	
	public Model() {
		semester.put(FIRST_SEM, new WeeklyTime());
		semester.put(SEC_SEM, new WeeklyTime());
	}
	
	@Override
	public void add(final int sem, final Days d, final ISubject sub, final Classrooms room, final int hour, final int n) throws WrongInputException {
		checkSem(sem);
		semester.get(sem).add(d, sub, room, hour, n);
	}

	@Override
	public void remove(final int sem, final Days d, final Classrooms room, final int hour, final int n) throws WrongInputException {
		checkSem(sem);
		semester.get(sem).remove(d, room, hour, n);
	}
	
	@Override
	public Optional<ISubject> getSubject(final int sem, final Days d, final Classrooms room, final int hour) throws WrongInputException {
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
	public void setSubjects(final Set<ISubject> set) {
		subjects = set;
	}

	
	private Map<Integer, IWeeklyTime> createCopy() {
		final Map<Integer, IWeeklyTime> map = new HashMap<>();
		map.put(FIRST_SEM, semester.get(FIRST_SEM).copy());
		map.put(SEC_SEM, semester.get(SEC_SEM).copy());
		return map;
	}
	
	//provaaaaaaaaaaa
	@Override
	public Classrooms whereTeaching(final String teach, final int hour, final Days d, final int sem) throws WrongInputException {
		return semester.get(sem).whereTeaching(teach, hour, d);
	}
	
	//provaaaaaaaaaaaaaa
	@Override
	public Classrooms wherePerforming(final ISubject sub, final int hour, final Days d, final int sem) throws WrongInputException {
		return semester.get(sem).wherePerforming(sub, hour, d);
	}
	
	@Override
	public Set<String> getTeachers() {
		final Set<String> setProf = new HashSet<>();
		for (final ISubject s : subjects) {
			if (!setProf.contains(s.getTeachName())) {
				setProf.add(s.getTeachName());
			}
		}
		return setProf;
	}
	
	@Override
	public IMemento createMemento() {
		return new Memento(createCopy());
	}


	
	@SuppressWarnings("unchecked")
	@Override
	public void setMemento(final IMemento mem) {
		semester = (Map<Integer, IWeeklyTime>) mem.getState();
		final Map<Integer, IWeeklyTime> app = createCopy();
		semester = app;
	}
	
	
	
	private static class Memento implements IMemento {
		
		private final Object sem;
		
		Memento(final Object s) {
			sem = s;
		}

		@Override
		public Object getState() {
			return sem;
		}

	}
}
