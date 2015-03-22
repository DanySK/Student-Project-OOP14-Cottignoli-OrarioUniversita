package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Frame;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;

public abstract class AbstractForm extends JDialog{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JButton bOk = new JButton("Ok");
	private final JButton bCanc = new JButton("Cancel");
	
	private final Frame mainFrame;
	
	private boolean okState;
	
	protected AbstractForm(final Frame v) {
		super(v);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		mainFrame = v;
		setLocationByPlatform(true);
		setResizable(false);
		okState = false;
		getContentPane().setLayout(new BorderLayout());
		final JPanel p = new JPanel(new FlowLayout());
		getContentPane().add(p, BorderLayout.SOUTH);
		p.add(bOk);
		bOk.setActionCommand("OK");
		bOk.addActionListener(e -> {
			okState = true;
			quit();
		});
		p.add(bCanc);
		bCanc.setActionCommand("CANCEL");
		bCanc.addActionListener(e -> {
			okState = false;
			quit();
		});
		pack();
	}
	
	public boolean isOk() {
		return okState;
	}
	
	public void reinit() {
		okState = false;
		init();
	}
	
	protected abstract void init();

	private void quit() {
		this.setVisible(false);
		mainFrame.setEnabled(true);
	}
}
