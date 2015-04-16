package view;

import java.util.List;

import controller.IController;

public interface IView {
	
	void setController(IController ctrl);
	
	void commandFailed(String message);
	
	void addData(List<Object> list);
	
	void setEnabledCommandUndo(boolean bool);
	
	void setEnabledCommandRedo(boolean bool);
	
	void clearData();
	
	int getSelectedSem();
}
