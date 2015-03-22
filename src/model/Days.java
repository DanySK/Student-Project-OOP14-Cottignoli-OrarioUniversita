package model;

public enum Days{
	
	MONDAY(0, "Monday"), 
	TUESDAY(1, "Tuesday"), 
	WEDNESDEY(2, "Wednesday"), 
	THURSDAY(3, "Thursday"), 
	FRIDAY(4, "Friday");
	
	public static final int N_DAYS = 5;
	
	private final int ordinal;
	private final String name;
	
	private Days(final int o, final String s){
		ordinal = o;
		name = s;
	}
	
	public int getOrdinal(){
		return ordinal;
	}
	
	public String getName(){
		return name;
	}
}
