import java.io.Serializable;
import java.rmi.NotBoundException;
import java.util.HashMap;
import java.util.Observable;

/**
 * Four In A Row model.
 * <h3>Current know limitations:</h3>
 * <ul>
 * <li>Assigning player1 and player2 to have the same name will give unexpected
 * behavior.</li>
 * </ul>
 * 
 * @author Ludde
 *
 */
public class Model extends Observable implements Serializable {
	private static final long serialVersionUID = -4987156369922245142L;
	private static final int WIN_CONDITION = 4;
	private String player1, player2;
	private String[][] board;
	private String currentPlayer = "";
	private String winner = "";
	private int lastPlayedRow;
	private int lastPlayedCol;
	private HashMap<String, Integer> highScores = new HashMap<String, Integer>();

	/**
	 * Creates a new instance of the model.
	 */
	public Model() {
		player1 = "red";
		player2 = "yellow";
		currentPlayer = player1;
		board = new String[6][7];
		newBoard();
	}

	/**
	 * Clean the board to (all strings = "")
	 */
	private void newBoard() {
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
	 *            the column to put the piece in.
	 * @throws InvalidMoveException
	 *             if the column is full.
	 */
	public void playRow(int col) throws InvalidMoveException {
		if (hasWinner()) {
			throw new InvalidMoveException("There's a winner, reset game to play");
		}
		for (int row = 0; row < 6; row++) {
			if (board[row][col].equals("")) {
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

	/**
	 * Helper function, swap to next player's turn.
	 */
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

	/**
	 * Gets the current state of the board.
	 * 
	 * @return the board
	 */
	public String[][] getBoard() {
		return board;
	}

	/**
	 * Gets the player who's turn it is next.
	 * 
	 * @return the player who's turn it is next.
	 */
	public String getCurrentPlayerName() {
		return currentPlayer;
	}

	/**
	 * Gets the player name of the specified index.
	 * 
	 * @param index
	 *            of of the player. Valid Index = 1 and 2.
	 * @return the player with the specified index.
	 * @throws IllegalArgumentException
	 *             if the index is out of bounds (currently != 1 or 2)
	 */
	public String getPlayerName(int index) throws IllegalArgumentException {
		switch (index) {
		case 1:
			return player1;
		case 2:
			return player2;
		default:
			throw new IndexOutOfBoundsException(index + " is not a valid player number");
		}
	}

	/**
	 * Sets the player name of the specified index.
	 * 
	 * @param index
	 *            the index of the player who's name should be set.
	 * @param newName
	 *            the new player name
	 */
	public void setPlayerName(int index, String newName) {
		switch (index) {
		case 1:
			player1 = newName;
			break;
		case 2:
			player2 = newName;
			break;
		default:
			throw new IndexOutOfBoundsException(index + " is not a valid player number");
		}
		setChanged();
		notifyObservers();
	}

	/**
	 * Starts a fresh game. High score is retained.
	 */
	public void resetGame() {
		newBoard();
		winner = "";
		setChanged();
		notifyObservers();
	}

	/**
	 * Gets the high score of this game.
	 * 
	 * @return the high score of this game.
	 */
	public HashMap<String, Integer> getHighScore() {
		return highScores;
	}

	/**
	 * State checker, to see if a winner exists or the game is still going.
	 * 
	 * @return true if someone has achieved four in a row.
	 */
	public boolean hasWinner() {
		return !winner.equals("");
	}

	/**
	 * Gets the winner of the current game.
	 * 
	 * @return the winner of the game.
	 * @throws NotBoundException
	 *             if no player has met the win condition.
	 */
	public String getWinner() throws NotBoundException {
		if (hasWinner()) {
			return winner;
		} else {
			throw new NotBoundException("No player has met the win condition");
		}
	}

	/**
	 * Sets and variables to the new model. Very skeptical that this is the best
	 * way to do it. But I wanted to avoid having View variable in the
	 * controller. (Which would be needed to set the new Model instance if it
	 * was solely done in the controller).
	 * 
	 * @param model the model to read all attributes from
	 */

	public void loadModelState(Model model) {
		this.player1 = model.player1;
		this.player2 = model.player2;
		this.board = model.board;
		this.currentPlayer = model.currentPlayer;
		this.winner = model.winner;
		this.lastPlayedRow = model.lastPlayedRow;
		this.lastPlayedCol = model.lastPlayedCol;
		this.highScores = model.highScores;
		setChanged();
		notifyObservers();
	}
}
