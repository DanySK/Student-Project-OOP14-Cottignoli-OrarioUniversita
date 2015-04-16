package test;

import static org.junit.Assert.*;
import model.Caretaker;
import model.ICaretaker;

import org.junit.Test;

public class TestCaretaker {

	@Test
	public void test() {
		final ICaretaker<Integer> ct = new Caretaker<>();
		assertFalse(ct.prevExist());
		assertFalse(ct.succExist());
		ct.add(1);
		assertFalse(ct.prevExist());
		assertFalse(ct.succExist());
		ct.add(2);
		assertTrue(ct.prevExist());
		assertFalse(ct.succExist());
		assertEquals(ct.getPrev(), Integer.valueOf(1));
		assertTrue(ct.succExist());
		
		assertEquals(ct.getSucc(), Integer.valueOf(2));
		ct.add(3);
		System.out.println(ct.toString());
		assertEquals(ct.getPrev(), Integer.valueOf(2));
		assertEquals(ct.getPrev(), Integer.valueOf(1));
		System.out.println(ct.toString());
		ct.add(4);
		System.out.println(ct.toString());
	}

}
