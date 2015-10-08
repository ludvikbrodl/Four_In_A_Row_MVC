package com.LudvikBrodl;

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
        board = new String[6][7];
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
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
                notifyObservers();
                return;
            }
        }
        throw new InvalidMoveException();
    }

    public String[][] getBoard() {
        return board;
    }
}
