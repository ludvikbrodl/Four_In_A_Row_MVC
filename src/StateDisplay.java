import java.awt.Font;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JMenu;

@SuppressWarnings("serial")
public class StateDisplay extends JMenu implements Observer {
	private Model model;

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
		if (model.winnerExists()) {
			state = "WINNER!!!: " + model.getWinner();
		} else {
			state = "Player Turn: " + model.getCurrentPlayerName();			
		}
		setText(state);
	}

}
