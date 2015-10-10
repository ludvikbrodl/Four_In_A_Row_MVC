import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

/**
 * JMenuItem which prompts the user for a player name to change and what to change it to.
 * @author Ludde
 *	
 */
public class SetPlayerNameMenuItem extends JMenuItem implements ActionListener {
	private static final long serialVersionUID = 2504866661776907381L;
	private Controller controller;

	/**
	 * @param controller where to send performed actions.
	 */
	public SetPlayerNameMenuItem(Controller controller) {
		super("Set Player Names");
		this.controller = controller;
		addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		String player1 = controller.getPlayerName(1);
		String player2 = controller.getPlayerName(2);
		
		Object[] possibilities = {player1, player2};
		String chosenPlayer = (String) JOptionPane.showInputDialog(new JFrame(),
				"Who's name would you like to change?", 
				"Choose player",
				JOptionPane.QUESTION_MESSAGE, 
				null, 
				possibilities, 
				player1);

		if ((chosenPlayer != null) && (chosenPlayer.length() > 0)) {
			int indexOfChosenPlayer = 0;
			if (chosenPlayer.equals(player1)) {
				indexOfChosenPlayer = 1;
			} else if (chosenPlayer.equals(player2)) {
				indexOfChosenPlayer = 2;
			}
			String newPlayerName = (String) JOptionPane.showInputDialog(new JFrame(),
					"Who's name would you like to change?", 
					"Choose player",
					JOptionPane.QUESTION_MESSAGE, 
					null, 
					null, 
					"");
			if ((newPlayerName != null) && (newPlayerName.length() > 0)) {
				controller.changePlayerName(newPlayerName, indexOfChosenPlayer);
			} else {
				//empty string was entered in the second dialog/x was pressed.
			}
		} else {
			//empty string was entered in first dialog/x was pressed.
		}
	}

}
