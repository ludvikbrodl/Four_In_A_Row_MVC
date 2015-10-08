import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

public class HighScoreButton extends JMenuItem implements ActionListener {

	private Controller controller;

	public HighScoreButton(Controller controller) {
		super("High Score");
		this.controller = controller;
		addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		String highScore = controller.getHighScore();
		JOptionPane.showMessageDialog(new JFrame(),
			    highScore,
			    "High Scorez",
			    JOptionPane.PLAIN_MESSAGE);
	}


}
