import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;

public class ResetButton extends JMenuItem implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 518993276695555440L;
	private Controller controller;

	public ResetButton(Controller controller) {
		super("Reset Game");
		this.controller = controller;
		addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		controller.resetGame();
	}

}
