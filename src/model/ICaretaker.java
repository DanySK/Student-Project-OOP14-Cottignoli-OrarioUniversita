package model;

public interface ICaretaker<X> {
	
	int SIZE_MAX = 10; 
	
	void add(X x);
	
	X getPrev();
	
	X getSucc();
	
	boolean prevExist();
	
	boolean succExist();
}
