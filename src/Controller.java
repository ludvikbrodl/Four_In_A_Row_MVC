import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Sends input to a model of Four In A Row. The interface between the View -> Model.
 * @author Ludde
 *
 */
/**
 * @author Ludde
 *
 */
public class Controller {
	private Model model;

	/**
	 * Creates a controller.
	 * @param model the model to which the commands are sent.
	 */
	public Controller(Model model) {
		this.model = model;
	}

	/**
	 * Attempts to play a specified column.
	 * @param column the column to attempt a game piece placement on.
	 */
	public void playRow(int column) {
		String playerTryingToPlay = model.getCurrentPlayerName();
		try {
			Audit.log("Player: '" + playerTryingToPlay + "' tried to put a game piece in col: '" + column + "'"); 
			model.playRow(column);
			Audit.log("and was succesful!");
		} catch (InvalidMoveException e) {
			Audit.log("Player: '" + playerTryingToPlay + "' was unsuccesful since " + e.getMessage());
		}

	}

	/**
	 * Changes the name of a player with a certain index. Currently only for 2 players
	 * @param newName the requested name of player.
	 * @param index the index of the player whose name should be changed.
	 */
	public void changePlayerName(String newName, int index) {
		String oldName = model.getPlayerName(index);
		model.setPlayerName(index, newName);
		Audit.log("'" + oldName + "' changed name to: '" + model.getPlayerName(index) + "'");
	}

	/**
	 * Gets a player name.
	 * @param index the index of the player's name.
	 * @return the player with the corresponding index.
	 */
	public String getPlayerName(int index) {
		return model.getPlayerName(index);
	}

	/**
	 * Starts a fresh board. High score and player names are retained.
	 */
	public void resetGame() {
		model.resetGame();
		Audit.log("Reset of Game was done");
	}

	/**
	 * Gets the high score in descending order. New line between each player.
	 * @return the high score.
	 */
	public String getHighScore() {
		Map<String, Integer> highScoreMap = sortByValue(model.getHighScore());
		StringBuilder sb = new StringBuilder();
		for (String s : highScoreMap.keySet()) {
			sb.append(s + " " + highScoreMap.get(s) + "\n");
		}
		Audit.log("High Score was prompted");
		return sb.toString();

	}

	/**
	 * Stolen from StackOverFlow for sorting by value instead of by key in a Map.
	 * Very generic solution, hard to read but very correct.
	 * @param map the map to be sorted by value, (the values have to extend comparable)
	 * @return the map - sorted by value.
	 */
	private static <K, V extends Comparable<? super V>> Map<K, V> sortByValue(Map<K, V> map) {
		List<Map.Entry<K, V>> list = new LinkedList<>(map.entrySet());
		Collections.sort(list, new Comparator<Map.Entry<K, V>>() {
			@Override
			public int compare(Map.Entry<K, V> o1, Map.Entry<K, V> o2) {
				return (o2.getValue()).compareTo(o1.getValue());
			}
		});
		Map<K, V> result = new LinkedHashMap<>();
		for (Map.Entry<K, V> entry : list) {
			result.put(entry.getKey(), entry.getValue());
		}
		return result;
	}

	/**
	 * Saves the state of the model including high scores and player names.
	 * @param selectedFile a file to save the state to.
	 * @throws IOException if the file could not be written to.
	 */
	public void saveToFile(File selectedFile) throws IOException {
		FileOutputStream f_out = new FileOutputStream(selectedFile);
		ObjectOutputStream obj_out = new ObjectOutputStream(f_out);
		obj_out.writeObject(model);
		obj_out.close();
		Audit.log("State was saved to file at: '" + selectedFile.getAbsolutePath() + "'");
	}

	/**
	 * Loads the state of the model including high scores and player names.
	 * @param selectedFile a file from which the states are read. Has to be saved by the saveToFile method.
	 * @throws IOException if the file could not be read from.
	 * @throws ClassNotFoundException if there is no class in the classpath which name is Model.
	 */
	public void loadFromFile(File selectedFile) throws IOException, ClassNotFoundException {
		FileInputStream f_in = new FileInputStream(selectedFile);
		ObjectInputStream obj_in = new ObjectInputStream(f_in);
		Object obj = obj_in.readObject();
		obj_in.close();
		if (obj instanceof Model) {
			model.loadModelState((Model) obj);
		} else {
			throw new ClassNotFoundException("Wrong type of file");
		}
		Audit.log("State was loaded from file at: '" + selectedFile.getAbsolutePath() + "'");
	}
}
