package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Frame;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;

/**
 * Abstract Form contenente un tasto ok e cancel.
 * 
 * @author Lorenzo Cottignoli
 *
 */
public abstract class AbstractForm extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JButton bOk = new JButton("Ok");
	private final JButton bCanc = new JButton("Cancel");
	
	private final Frame mainFrame;
	
	private boolean okState;
	
	/**
	 * 
	 * @param v Frame principale.
	 */
	protected AbstractForm(final Frame v) {
		super(v);
		mainFrame = v;
		setResizable(false);
		okState = false;
		getContentPane().setLayout(new BorderLayout());
		final JPanel p = new JPanel(new FlowLayout());
		getContentPane().add(p, BorderLayout.SOUTH);
		p.add(bOk);
		bOk.addActionListener(e -> {
			okState = true;
			this.setVisible(false);
		});
		p.add(bCanc);
		bCanc.addActionListener(e -> {
			okState = false;
			this.setVisible(false);
		});
	}
	
	/**
	 * Metodo che informa se è stato premuto il tasto ok o meno.
	 * 
	 * @return true se è stato premuto ok, altrimenti false.
	 */
	public boolean isOk() {
		return okState;
	}
	
	/**
	 * Aggiunge al metodo standard la collocazione del form al centro del form mainFrame.
	 */
	@Override
	public void setVisible(final boolean b) {
		super.setVisible(b);
		if (b) {
			okState = false;
			final int x = mainFrame.getX() + mainFrame.getWidth() / 2 - this.getWidth() / 2;
			final int y = mainFrame.getY() + mainFrame.getHeight() / 2 - this.getHeight() / 2;	
			setLocation(x, y);
		}
	}
}
