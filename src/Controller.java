import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;
import java.util.TreeMap;

/**
 * Created by Ludde on 2015-10-02.
 */
public class Controller {

	private Model model;

	public Controller(Model model) {
		this.model = model;
	}

	public void playRow(int col) {
		try {
			model.playRow(col);
		} catch (InvalidMoveException e) {
			// Not a valid move;
			System.out.println("not a free row");
		}

	}

	public void changePlayerName(String player, int index) {

	}

	public void resetGame() {
		model.resetGame();
	}

	public String getHighScore() {
		Map<String, Integer> highScoreMap = sortByValue(model.getHighScore());
		StringBuilder sb = new StringBuilder();
		for (String s : highScoreMap.keySet()) {
			sb.append(s + " " + highScoreMap.get(s) + "\n");
		}
		return sb.toString();
		
	}

	//stolen from stackoverflow for sorting by value to getting a highscore list with descending values
	//very generic solution, hard to read but is very well written.
	public static <K, V extends Comparable<? super V>> Map<K, V> sortByValue(Map<K, V> map) {
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

}
