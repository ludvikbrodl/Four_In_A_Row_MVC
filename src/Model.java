import java.rmi.NotBoundException;
import java.util.HashMap;
import java.util.Observable;

/**
 * Created by Ludde on 2015-10-02.
 */
public class Model extends Observable {
	private static final int WIN_CONDITION = 4;
	private String player1, player2;
	private String[][] board;
	private String currentPlayer = "";
	private String winner = "";
	private int lastPlayedRow;
	private int lastPlayedCol;
	private HashMap<String, Integer> highScores = new HashMap<String, Integer>();;

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
		if (hasWinner()) {
			throw new InvalidMoveException("There's a winner, reset game to play");
		}
		// indexing is swapped row/col....
		for (int row = 0; row < 6; row++) {
			if (board[row][col] == "") {
				board[row][col] = currentPlayer;
				lastPlayedRow = row;
				lastPlayedCol = col;
				checkForWin();
				swapCurrentPlayer();
				setChanged();
				notifyObservers();
				return;
			}
		}
		throw new InvalidMoveException("Column is full");
	}

	private void swapCurrentPlayer() {
		if (currentPlayer.equals(player1)) {
			currentPlayer = player2;
		} else {
			currentPlayer = player1;
		}
	}

	private void checkForWin() {
		int horInARow = checkHorizontal();
		int verInARow = checkVeritcal();
		int diagInARow = checkDiagonal();
		if (Math.max(Math.max(horInARow, verInARow), diagInARow) >= WIN_CONDITION) {
			applyWin();
		}

	}

	private void applyWin() {
		winner = currentPlayer;
		if (highScores.containsKey(winner)) {
			highScores.put(winner, highScores.get(winner) + 1);
		} else {
			highScores.put(winner, 1);
		}
	}

	private int checkDiagonal() {
		int nbrFound = 0;
		int diagOffset = 0;
		// check right of origin
		try {
			while (true) {
				String foundPlayer = board[lastPlayedRow + diagOffset][lastPlayedCol + diagOffset];
				if (foundPlayer.equals(currentPlayer)) {
					nbrFound++;
				} else {
					break;
				}
				diagOffset++;
			}
		} catch (ArrayIndexOutOfBoundsException e) {
			// end of gaming board
		}
		// check left of origin
		try {
			diagOffset = -1;
			while (true) {
				String foundPlayer = board[lastPlayedRow + diagOffset][lastPlayedCol + diagOffset];
				if (foundPlayer.equals(currentPlayer)) {
					nbrFound++;
				} else {
					break;
				}
				diagOffset--;
			}

		} catch (ArrayIndexOutOfBoundsException e) {
			// end of gaming board
		}
		return nbrFound;

	}

	private int checkVeritcal() {
		int nbrFound = 0;
		int colOffset = 0;
		// check right of origin
		try {
			while (true) {
				String foundPlayer = board[lastPlayedRow][lastPlayedCol + colOffset];
				if (foundPlayer.equals(currentPlayer)) {
					nbrFound++;
				} else {
					break;
				}
				colOffset++;
			}
		} catch (ArrayIndexOutOfBoundsException e) {
			// end of gaming board
		}
		// check left of origin
		try {
			colOffset = -1;
			while (true) {
				String foundPlayer = board[lastPlayedRow][lastPlayedCol + colOffset];
				if (foundPlayer.equals(currentPlayer)) {
					nbrFound++;
				} else {
					break;
				}
				colOffset--;
			}

		} catch (ArrayIndexOutOfBoundsException e) {
			// end of gaming board
		}
		return nbrFound;

	}

	private int checkHorizontal() {
		int nbrFound = 0;
		int rowOffset = 0;
		// check right of origin
		try {
			while (true) {
				String foundPlayer = board[lastPlayedRow + rowOffset][lastPlayedCol];
				if (foundPlayer.equals(currentPlayer)) {
					nbrFound++;
				} else {
					break;
				}
				rowOffset++;
			}
		} catch (ArrayIndexOutOfBoundsException e) {
			// end of gaming board
		}
		// check left of origin
		try {
			rowOffset = -1;
			while (true) {
				String foundPlayer = board[lastPlayedRow + rowOffset][lastPlayedCol];
				if (foundPlayer.equals(currentPlayer)) {
					nbrFound++;
				} else {
					break;
				}
				rowOffset--;
			}

		} catch (ArrayIndexOutOfBoundsException e) {
			// end of gaming board
		}
		return nbrFound;
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

	public void setPlayerName(int i, String name) {
		switch (i) {
		case 1:
			player1 = name;
			break;
		case 2:
			player2 = name;
			break;
		default:
			throw new IndexOutOfBoundsException(i + " is not a valid player number");
		}
	}

	public void resetGame() {
		for (int row = 0; row < 6; row++) {
			for (int col = 0; col < 7; col++) {
				board[row][col] = "";
			}
		}
		winner = "";
		setChanged();
		notifyObservers();
	}

	public HashMap<String, Integer> getHighScore() {
		highScores.put("player2", 2);
		highScores.put("plataef2", 5);
		highScores.put("aefae", 1);
		return highScores;
	}

	public boolean hasWinner() {
		return !winner.equals("");
	}

	public String getWinner() throws NotBoundException {
		if (!hasWinner()) {
			return winner;
		} else {
			throw new NotBoundException("No player has met the win condition");
		}
	}
}
