package view;

import controller.IController;

public interface IView {
	
	void setController(final IController ctrl);
	
	void commandFailed(final String message);
}
