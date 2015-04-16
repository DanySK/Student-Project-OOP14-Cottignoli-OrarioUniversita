package test;

import static org.junit.Assert.*;
import static model.SubjectType.*;
import model.Classrooms;
import model.ClassroomsDailyTime;
import model.IClassroomsDailyTime;
import model.Subject;
import model.WrongInputException;

import org.junit.Test;

public class TestClassroomsDailyTime {

	@Test
	public void test() {
		final IClassroomsDailyTime c = new ClassroomsDailyTime();
		try {
			c.add(new Subject("OOP", "viroli", LT1), Classrooms.MAGNA, 10, 2);
			c.add(new Subject("SO", "salomoni", LT1), Classrooms.VELA, 10, 2);
			c.add(new Subject("SO", "salomoni", LT1), Classrooms.MAGNA, 13, 2);
			c.remove(Classrooms.MAGNA, 13, 2);
		} catch (WrongInputException e) {
			fail("this sequence of instructions is right!");
		}
		
		try {
			c.add(new Subject("OOP", "viroli", LT1), null, 15, 2);
			fail("null can't be accepted");
		} catch (WrongInputException e) {
			fail("Wrong exception catched");
		} catch (IllegalArgumentException e) {}
			
		
		try {
			c.remove(null, 10, 2);
			fail("null can't be accepted");
		} catch (WrongInputException e) {
			fail("Wrong exception catched");
		} catch (IllegalArgumentException e) {}
		
		
	}
}
