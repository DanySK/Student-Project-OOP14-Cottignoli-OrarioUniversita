package model;

public class WrongInputException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public WrongInputException(final String s) {
		super(s);
	}

	@Override
	public String toString() {
		return getMessage();
	}
	
	

}
