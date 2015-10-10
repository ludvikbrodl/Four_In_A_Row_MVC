import java.awt.Font;
import java.rmi.NotBoundException;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JMenu;

/**
 * Displays the current state of the game.
 * Observers the model and updates itself with the current state of the model changes.
 * @author Ludde
 *
 */
public class StateDisplay extends JMenu implements Observer {
	private static final long serialVersionUID = -4591598976882571598L;
	private Model model;

	/**
	 * @param model the game to observe.
	 */
	public StateDisplay(Model model) {
		super();
		setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
		setFocusable(false);
		setPopupMenuVisible(false);
		this.model = model;
		this.model.addObserver(this);
		update(null, null);
		
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		String state = "";
		if (model.hasWinner()) {
			try {
				Audit.log("'" + model.getWinner() + "' has won!");
				state = "WINNER!!!: '" + model.getWinner() + "' - Reset game to play";
			} catch (NotBoundException e) {
				//something is amiss, tried to access winner before win condition was met
				System.out.println(e.toString());
				e.printStackTrace();
			}
		} else {
			state = "Player Turn: " + model.getCurrentPlayerName();			
		}
		setText(state);
	}

}
