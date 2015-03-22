package view;

import controller.Controller;

public class Test {

	public static void main(final String[] args) {
		final Controller c = new Controller();
		final View v = new View();
		c.addView(v);
	}

}
