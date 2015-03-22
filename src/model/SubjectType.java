package model;

public enum SubjectType {
	LT1("Laurea triennale primo anno"),
	LT2("Laurea triennale secondo anno"),
	LT3("Laurea triennale terzo anno"),
	LM1("Laurea magistrale primo anno"),
	LM2("Laurea magistrale secondo anno"),
	LTO("Laurea triennale opzionale"),
	LMO("Laurea magistrale opzionale"),
	CTA("Curricula terzo anno");
	
	private final String descr;
	
	private SubjectType(final String s) {
		descr = s;
	}
	
	public String getDescription(){
		return descr;
	}
}
