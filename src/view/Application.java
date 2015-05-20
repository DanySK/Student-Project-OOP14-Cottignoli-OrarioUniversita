package view;

import controller.Controller;
import controller.IController;

/**
 * 
 * @author Lorenzo Cottignoli
 *
 */
public final class Application {
	
	private Application() {
		super();
	}

	/**
	 * @param args
	 *            ignored
	 */
	public static void main(final String[] args) {
		final IController c = new Controller();
		final IView v = new View();
		c.addView(v);
	}

}
