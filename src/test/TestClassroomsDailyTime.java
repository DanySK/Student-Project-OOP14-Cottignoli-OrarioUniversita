package test;

import static org.junit.Assert.*;
import model.Classrooms;
import model.ClassroomsDailyTime;
import model.ClassroomsDailyTimeImpl;
import model.LessonImpl;

import org.junit.Test;

public class TestClassroomsDailyTime {

	@Test
	public void test() {
		ClassroomsDailyTime c = new ClassroomsDailyTimeImpl();
		c.add(new LessonImpl("OOP", "viroli", 1), Classrooms.MAGNA, 10, 2);
		c.add(new LessonImpl("SO", "salomoni", 1), Classrooms.VELA, 10, 2);
		c.add(new LessonImpl("SO", "salomoni", 1), Classrooms.MAGNA, 13, 2);
		assertEquals(c.getlLessons(Classrooms.MAGNA, 9, 8).size(), 2);
		c.remove(Classrooms.MAGNA, 13, 2);
		
		try{
			c.add(new LessonImpl("OOP", "viroli", 1), Classrooms.A, 10, 2);
			fail("viroli is in Magna at that time");
		}catch(IllegalArgumentException e){}
		
		try{
			c.add(new LessonImpl("OOP", "viroli", 1), null, 15, 2);
			fail("null exception");
		}catch(NullPointerException e){}
		
		try{
			c.remove(null, 10, 2);
			fail("null exception");
		}catch(NullPointerException e){}
		
		try{
			c.getlLessons(null, 9, 15);
			fail("null exception");
		}catch(NullPointerException e){}
	}

}
