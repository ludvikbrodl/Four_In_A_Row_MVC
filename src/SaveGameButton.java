import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

/**
 * JMenuItem that saves the game state. 
 * Prompts the player to specify where save the game. 
 * @author Ludde
 *
 */
public class SaveGameButton extends JMenuItem implements ActionListener {
	private static final long serialVersionUID = -7423390514888530815L;
	private Controller controller;

	/**
	 * @param controller where to send performed actions.
	 */
	public SaveGameButton(Controller controller) {
		super("Save Game");
		this.controller = controller;
		addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JFileChooser fc = new JFileChooser();
		fc.setSelectedFile(new File(Options.WORKING_DIR + "fourinarow.save"));
	
		int returnValue = fc.showSaveDialog(new JFrame());
		if (returnValue == JFileChooser.APPROVE_OPTION) {
			try {
				controller.saveToFile(fc.getSelectedFile());
				JOptionPane.showMessageDialog(new JFrame(), "Save successful", "Good job you managed to save!", JOptionPane.PLAIN_MESSAGE);
			} catch (IOException ioe) {
				JOptionPane.showMessageDialog(new JFrame(), ioe.getMessage(), "SAVE ERROR", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
}
