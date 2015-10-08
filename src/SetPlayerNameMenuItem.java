import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

public class SetPlayerNameMenuItem extends JMenuItem implements ActionListener {

	private Controller controller;

	public SetPlayerNameMenuItem(Controller controller) {
		super("Set Player Names");
		this.controller = controller;
		addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		Object[] possibilities = { "ham", "spam", "yam" };
		String s = (String) JOptionPane.showInputDialog(new JFrame(),
				"Who's name would you like to change?", 
				"Name Change",
				JOptionPane.QUESTION_MESSAGE, 
				null, 
				possibilities, 
				"ham");

		// If a string was returned, say so.
		if ((s != null) && (s.length() > 0)) {
			setLabel("Green eggs and... " + s + "!");
			return;
		}

		// If you're here, the return value was null/empty.
		setLabel("Come on, finish the sentence!");
		controller.changePlayerName("", 1);
	}

}
