package model;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public enum Classrooms {
	MAGNA("Aula magna", 240),
	A("Aula A", 98),
	B("Aula B", 118),
	C("Aula C", 60),
	D("Aula D", 30),
	E("Aula E", 37),
	VELA("Laboratorio vela", 80),
	LAB2("Laboratorio 2", 60),
	LAB3("Laboratorio 3", 80);
	
	private final String name;
	private final int nSeats;
	
	private Classrooms(final String s, final int n) {
		name = s;
		nSeats = n;
	}

	public String getName() {
		return name;
	}
	
	public int getSeats() {
		return nSeats;
	}
	
	public static Set<Classrooms> getClassroomsValues() {
		return new HashSet<>(Arrays.asList(Classrooms.values()));
	}
}
