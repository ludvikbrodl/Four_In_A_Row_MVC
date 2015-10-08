import java.util.HashMap;
import java.util.Observable;

/**
 * Created by Ludde on 2015-10-02.
 */
public class Model extends Observable {
	private String player1, player2;
	private String[][] board;
	private String currentPlayer = "";
	private boolean winnerExists;
	private String winner;
	private int lastPlayedRow;
	private int lasyPlayedCol;

	public Model() {
		player1 = "player1";
		player2 = "player2";
		currentPlayer = player1;
		board = new String[6][7];
		for (int row = 0; row < 6; row++) {
			for (int col = 0; col < 7; col++) {
				board[row][col] = "";
			}
		}
	}

	/**
	 * Put the piece in the specified column.
	 * 
	 * @param col
	 *            the column to put the pice in.
	 * @throws InvalidMoveException
	 *             if the column is full.
	 */
	public void playRow(int col) throws InvalidMoveException {
		if (winnerExists) {
			throw new InvalidMoveException("There's a winner, reset game to play");
		}
		// indexing is swapped row/col....
		for (int row = 0; row < 6; row++) {
			if (board[row][col] == "") {
				board[row][col] = currentPlayer;
				if (currentPlayer.equals(player1)) {
					currentPlayer = player2;
				} else {
					currentPlayer = player1;
				}
				lastPlayedRow = row;
				lasyPlayedCol = col;
				checkForWin();
				setChanged();
				notifyObservers();
				return;
			}
		}
		throw new InvalidMoveException("Column is full");
	}

	private void checkForWin() {
		checkHorizontal();
		checkVeritcal();
		checkDiagonal();

	}

	private void checkDiagonal() {
		// TODO Auto-generated method stub

	}

	private void checkVeritcal() {
		// TODO Auto-generated method stub

	}

	private void checkHorizontal() {
		// TODO Auto-generated method stub

	}

	public String[][] getBoard() {
		return board;
	}

	public String getCurrentPlayerName() {
		return currentPlayer;
	}

	public String getPlayerName(int i) throws IllegalArgumentException {
		if (i == 1) {
			return player1;
		} else if (i == 2) {
			return player2;
		}
		throw new IllegalArgumentException("This player is not currently playing");
	}

	public void resetGame() {
		for (int row = 0; row < 6; row++) {
			for (int col = 0; col < 7; col++) {
				board[row][col] = "";
			}
		}
		setChanged();
		notifyObservers();
	}

	public HashMap<String, Integer> getHighScore() {
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		map.put("player2", 2);
		map.put("plataef2", 5);
		map.put("aefae", 1);
		return map;
	}

	public boolean winnerExists() {
		return winnerExists;
	}

	public String getWinner() {
		return winner;
	}
}
