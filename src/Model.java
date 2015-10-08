import java.util.Observable;

/**
 * Created by Ludde on 2015-10-02.
 */
public class Model extends Observable {
    private String player1,player2;
    private String[][] board;
    private String currentPlayer = "";

    public Model() {
        player1 = "player1";
        player2 = "player2";
        currentPlayer = player1;
        board = new String[7][6];
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 6; j++) {
                board[i][j] = "";
            }
        }
    }

    /**
     * Put the piece in the specified column.
     * @param col the column to put the pice in.
     * @throws InvalidMoveException if the column is full.
     */
    public void playRow(int col) throws InvalidMoveException {
        for (int i = 0; i < 6; i++) {
            if (board[col][i] == "") {
                board[col][i] = currentPlayer;
                if (currentPlayer.equals(player1)) {
                	currentPlayer = player2;
                } else {
                	currentPlayer = player1;
                }
                setChanged();
                notifyObservers();
                return;
            }
        }
        throw new InvalidMoveException();
    }

    public String[][] getBoard() {
        return board;
    }
    
    public String getPlayerName(int i) throws IllegalArgumentException {
    	if (i == 1) {
    		return player1;
    	} else if (i == 2){
    		return player2;
    	}
    	throw new IllegalArgumentException("This player is not currently playing");
    }
}
