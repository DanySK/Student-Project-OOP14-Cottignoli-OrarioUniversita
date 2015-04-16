package test;

import static org.junit.Assert.*;
import model.Classrooms;
import model.Days;
import model.IModel;
import model.Model;
import model.Subject;
import model.SubjectType;

import org.junit.Test;

public class TestModel {

	@Test
	public void test() {
		final IModel u = new Model();
		/*u.add(1, Days.FRIDAY, new Subject("OOP", "viroli", SubjectType.LM1), Classrooms.A, 10, 5);
		System.out.println(u.getSubject(1, Days.FRIDAY, Classrooms.A, 11).toString());*/
	}

}
