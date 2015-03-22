package test;

import static org.junit.Assert.*;
import static model.SubjectType.*;

import model.DailyTime;
import model.IDailyTime;
import model.Subject;

import org.junit.Test;

public class TestDailyTime {

	@Test
	public void test() {
		final IDailyTime d = new DailyTime();
		d.add(new Subject("Fisica", "Campari", LT1), 15, 2);
		assertEquals(d.getSubjects(10, 1).size(), 0); 
		assertEquals(d.getSubjects(15, 2).size(), 1);  
		d.add(new Subject("OOP", "viroli", LT1), 11, 3);
		if (!d.getSubjects(11, 2).contains(new Subject("OOP", "viroli", LT1))) {
			System.out.println(d.toString());
			fail("it's contained");
		}
		
		try {
			d.add(new Subject("SO", "salomoni", LT1), 12, 1);
			fail("these hours are busy"); 
		} catch (IllegalArgumentException e) {}
	
		try {
			d.add(new Subject("SO", "salomoni", LT1), 25, 1);
			fail("invalid hour");
		} catch (IllegalArgumentException e) {}
		
		try {
			d.add(new Subject("SO", "salomoni", LT1), 12, 10);
			fail("out of range");
		} catch (IllegalArgumentException e) {}
		
		try {
			d.add(new Subject("SO", "salomoni", LT1), 12, 0);
			fail("invalid number of hours");
		} catch (IllegalArgumentException e) {}
		
		try {
			d.remove(24, 1);
			fail("invalid hour");
		} catch (IllegalArgumentException e) {}
		
		try {
			d.remove(12, 10);
			fail("out of range");
		} catch (IllegalArgumentException e) {}
		
		try {
			d.getSubject(-1);
			fail("invalid hour");
		} catch (IllegalArgumentException e) {}
	}

}
