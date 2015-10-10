import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

/**
 * JMenuItem that loads prompts the player to specify a file from which the model state should be read from.
 * @author Ludde
 *
 */
public class LoadGameButton extends JMenuItem implements ActionListener {
	private static final long serialVersionUID = 1L;
	private Controller controller;

	/**
	 * @param controller where to send performed actions.
	 */
	public LoadGameButton(Controller controller) {
		super("Load Game");
		this.controller = controller;
		addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JFileChooser fc = new JFileChooser(Options.WORKING_DIR);
		int returnValue = fc.showOpenDialog(new JFrame());
		if (returnValue == JFileChooser.APPROVE_OPTION) {
			try {
				controller.loadFromFile(fc.getSelectedFile());
				JOptionPane.showMessageDialog(new JFrame(), "Load successful", "Holy crap, sucessful load was made!",
						JOptionPane.PLAIN_MESSAGE);
			} catch (ClassNotFoundException e1) {
				JOptionPane.showMessageDialog(new JFrame(), e1.getMessage(), "crap on a stick m8, incorrect filetype!",
						JOptionPane.ERROR_MESSAGE);
				e1.printStackTrace();
			} catch (IOException e1) {
				JOptionPane.showMessageDialog(new JFrame(), e1.getMessage(), "crap on a stick m8, incorrect filetype!",
						JOptionPane.ERROR_MESSAGE);
				e1.printStackTrace();
			}

		}
	}
}
