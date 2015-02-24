package test;

import static org.junit.Assert.*;
import model.DailyTime;
import model.DailyTimeImpl;
import model.LessonImpl;

import org.junit.Test;

public class TestDailyTime {

	@Test
	public void test() {
		DailyTime d = new DailyTimeImpl();
		d.add(new LessonImpl("Fisica", "Campari", 1), 15, 2);
		assertEquals(d.getlLessons(10, 1).size(), 0); 
		assertEquals(d.getlLessons(15, 2).size(), 1);  
		d.add(new LessonImpl("OOP", "viroli", 1), 11, 3);
		if (!d.getlLessons(11, 2).contains(new LessonImpl("OOP", "viroli", 1))){
			fail("it's contained");
		}
		
		try{
			d.add(null, 9, 1);
			fail("null can't be acepted ");
		}catch(NullPointerException e){}
		
		try{
			d.add(new LessonImpl("SO", "salomoni", 1), 12, 1);
			fail("these hours are busy"); 
		}catch(IllegalArgumentException e){}
	
		try{
			d.add(new LessonImpl("SO", "salomoni", 1), 25, 1);
			fail("invalid hour");
		}catch(IllegalArgumentException e){}
		
		try{
			d.add(new LessonImpl("SO", "salomoni", 1), 12, 10);
			fail("out of range");
		}catch(IllegalArgumentException e){}
		
		try{
			d.add(new LessonImpl("SO", "salomoni", 1), 12, 0);
			fail("invalid number of hours");
		}catch(IllegalArgumentException e){}
		
		try{
			d.remove(24, 1);;
			fail("invalid hour");
		}catch(IllegalArgumentException e){}
		
		try{
			d.remove(12, 10);
			fail("out of range");
		}catch(IllegalArgumentException e){}
		
		try{
			d.getLesson(-1);
			fail("invalid hour");
		}catch(IllegalArgumentException e){}
	}

}
