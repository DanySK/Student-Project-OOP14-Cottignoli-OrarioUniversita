package model;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;


public enum Days {
	
	MONDAY("Monday"), 
	TUESDAY("Tuesday"), 
	WEDNESDEY("Wednesday"), 
	THURSDAY("Thursday"), 
	FRIDAY("Friday");
	
	public static final int N_DAYS = 5;
	
	private final String name;
	
	private Days(final String s) {
		name = s;
	}
	
	public String getName() {
		return name;
	}
	
	public static Set<Days> getDaysValues() {
		return new HashSet<>(Arrays.asList(Days.values()));
	}
}
