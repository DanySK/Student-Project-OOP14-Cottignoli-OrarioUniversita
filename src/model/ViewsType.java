package model;

public enum ViewsType {

	TOT("Total"),
	TEACH("Teacher"),
	SUB("Subject"),
	ROOM("Classroom"),
	DAY("Day"),
	SUB_TYPE("Subject Type");
	
	private final String description;
	
	private ViewsType(final String desc){
		description = desc;
	}
	
	public String getDescription() {
		return description;
	}
}
