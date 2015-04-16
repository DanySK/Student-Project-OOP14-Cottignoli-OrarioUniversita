package model;

import java.awt.Color;

public enum SubjectType {
	LT1("1° anno laurea triennale", Color.CYAN),
	LT2("2° anno laurea triennale", Color.YELLOW),
	LT3("3° anno laurea triennale", Color.LIGHT_GRAY),
	LM1("1° anno laurea magistrale", Color.GREEN),
	LM2("2° anno laurea magistrale", Color.MAGENTA),
	LTO("Opzionali laurea triennale", Color.ORANGE),
	LMO("Opzionali laurea magistrale", Color.PINK),
	CTA("3° anno curricula scienze laurea triennale", Color.RED);
	
	private final String descr;
	private final Color color;
	
	private SubjectType(final String s, final Color c) {
		descr = s;
		color = c;
	}
	
	public String getDescription() {
		return descr;
	}
	
	public Color getColor() {
		return color;
	}
}
