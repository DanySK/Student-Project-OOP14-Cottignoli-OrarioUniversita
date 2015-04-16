package test;

import static org.junit.Assert.*;
import static model.SubjectType.*;
import model.DailyTime;
import model.IDailyTime;
import model.Subject;
import model.WrongInputException;

import org.junit.Test;

public class TestDailyTime {

	@Test
	public void test(){
		final IDailyTime d = new DailyTime();
		try{
			d.add(new Subject("OOP", "viroli", LT2), 9, 9);
			d.remove(9, 9);
			d.add(new Subject("Fisica", "Campari", LT2), 15, 2);
			d.add(new Subject("OOP", "viroli", LT2), 17, 1);
			assertTrue(d.copy().equals(d));	
		} catch (WrongInputException e) {
			fail("these sequence of instruction is right");
		}
		
		try {
			d.add(new Subject("SO", "salomoni", LT2), 15, 1);
			fail("these hours are busy"); 
		} catch (WrongInputException e) { }
	
		try {
			d.add(new Subject("SO", "salomoni", LT2), 25, 1);
			fail("invalid hour");
		} catch (WrongInputException e) { }
		
		try {
			d.add(new Subject("SO", "salomoni", LT2), 12, 10);
			fail("out of range");
		} catch (WrongInputException e) { }
		
		try {
			d.add(new Subject("SO", "salomoni", LT2), 12, 0);
			fail("invalid number of hours");
		} catch (WrongInputException e) { }
		
		try {
			d.remove(24, 1);
			fail("invalid hour");
		} catch (WrongInputException e) { }
		
		try {
			d.remove(12, 10);
			fail("out of range");
		} catch (WrongInputException e) { }
		
		try {
			d.getSubject(-1);
			fail("invalid hour");
		} catch (WrongInputException e) { }
	}

}
