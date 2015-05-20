package model;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

/**
 * Implementazione dell'interfaccia {@link IClassroomsDailyTime} tramite l'utilizzo di una Map che come chiave ha i valori di 
 * {@link Classrooms#values()} ed associato ad ognuna un {@link IDailyTime}.
 * 
 * @author Lorenzo Cottignoli
 *
 */
public class ClassroomsDailyTime implements IClassroomsDailyTime {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3887851499745557411L;
	
	private final Map<Classrooms, IDailyTime> m = new HashMap<>();
	
	/**
	 * Crea un nuovo orario giornaliero con tutte le ore libere per ogni aula.
	 */
	public ClassroomsDailyTime() {
		for (final Classrooms c : Classrooms.values()) {
			m.put(c, new DailyTime());
		}
	}
	
	/**
	 * Crea un nuovo orario giornaliero per ogni aula copiando quello passato come parametro.
	 * 
	 * @param cdt Orario giornaliero di tutte le aulee da copiare.
	 * @throws IllegalArgumentException se il parametro cdt è null.
	 */
	public ClassroomsDailyTime(final IClassroomsDailyTime cdt) {
		if (cdt == null) {
			throw new IllegalArgumentException("the parameter 'cdt' can't be null!");
		}
		for (final Classrooms c : Classrooms.values()) {
			m.put(c, cdt.getDailyTime(c));
		}
	}

	@Override
	public void add(final ISubject sub, final Classrooms room, final int hour, final int n) {
		checkRoom(room);
		if (!whereTeaching(sub.getTeachName(), hour).isEmpty()) {
			for (final Classrooms cls : whereTeaching(sub.getTeachName(), hour)) {
				if (!getSubject(cls, hour).get().equals(sub)) {
					throw new IllegalArgumentException(sub.getTeachName() + "is already teaching another subject in this hour!");
				}
			}
		}
		m.get(room).add(sub, hour, n);
	}

	@Override
	public void remove(final Classrooms room, final int hour, final int n) {
		checkRoom(room);
		m.get(room).remove(hour, n);
	}

	@Override
	public Optional<ISubject> getSubject(final Classrooms room, final int hour) {
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
	
	@Override
	public Set<Classrooms> whereTeaching(final String teach, final int hour) {
		final Set<Classrooms> set = new HashSet<>();
		for (final Classrooms room : Classrooms.values()) {
			if (m.get(room).getSubject(hour).isPresent() && m.get(room).getSubject(hour).get().getTeachName().equals(teach)) {
				set.add(room);
			}
		}
		return set;
	}
	
	@Override
	public Set<Classrooms> wherePerforming(final ISubject sub, final int hour) {
		final Set<Classrooms> set = new HashSet<>();
		for (final Classrooms room : Classrooms.values()) {
			if (m.get(room).getSubject(hour).isPresent() && m.get(room).getSubject(hour).get().equals(sub)) {
				set.add(room);
			}
		}
		return set;
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
		return "ClassroomsDailyTime [m=" + m + "]";
	}

	/**
	 * Metodo per controllare che l'aula non sia null.
	 * 
	 * @param room Aula da controllare.
	 * @throws IllegalArgumentException se room è null.
	 */
	private void checkRoom(final Classrooms room) {
		if (room == null) {
			throw new IllegalArgumentException("The classroom can't be null!");
		}
	}
}
