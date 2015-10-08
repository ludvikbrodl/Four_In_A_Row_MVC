import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;

public class LoadGameButton extends JMenuItem implements ActionListener {

	private Controller controller;

	public LoadGameButton(Controller controller) {
		super("Load Game");
		this.controller = controller;
		addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}

}
