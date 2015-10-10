import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

/**
 * JMenuItem that shows the high score with a JFrame message dialog.
 * @author Ludde
 *
 */
public class HighScoreButton extends JMenuItem implements ActionListener {
	private static final long serialVersionUID = 8279729502204813430L;
	private Controller controller;

	/**
	 * @param controller where to send performed actions.
	 */
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
