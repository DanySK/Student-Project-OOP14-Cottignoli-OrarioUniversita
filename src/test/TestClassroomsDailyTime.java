package test;

import static org.junit.Assert.*;
import static model.SubjectType.*;
import model.Classrooms;
import model.ClassroomsDailyTime;
import model.IClassroomsDailyTime;
import model.Subject;

import org.junit.Test;

public class TestClassroomsDailyTime {

	@Test
	public void test() {
		final IClassroomsDailyTime c = new ClassroomsDailyTime();
		c.add(new Subject("OOP", "viroli", LT1), Classrooms.MAGNA, 10, 2);
		c.add(new Subject("SO", "salomoni", LT1), Classrooms.VELA, 10, 2);
		c.add(new Subject("SO", "salomoni", LT1), Classrooms.MAGNA, 13, 2);
		assertEquals(c.getSubjects(Classrooms.MAGNA, 9, 8).size(), 2);
		c.remove(Classrooms.MAGNA, 13, 2);
		
		try {
			c.add(new Subject("OOP", "viroli", LT1), Classrooms.A, 10, 2);
			fail("viroli is in Magna at that time");
		} catch (IllegalArgumentException e) {}
		
		try {
			c.add(new Subject("OOP", "viroli", LT1), null, 15, 2);
			fail("null exception");
		} catch (NullPointerException e) {}
		
		try {
			c.remove(null, 10, 2);
			fail("null exception");
		} catch (NullPointerException e) {}
		
		try {
			c.getSubjects(null, 9, 15);
			fail("null exception");
		} catch (NullPointerException e) {}
	}

}
