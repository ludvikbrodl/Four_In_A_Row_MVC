package com.LudvikBrodl;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by Ludde on 2015-10-02.
 */
public class View implements Observer {
    private int padding = 5; //pixels
    private Model model;
    private Controller controller;
    private BoardPanel boardPanel;


    public View(Controller controller, Model model) {
        this.model = model;
        this.controller = controller;
    }

    public void open() {
        //1. Create the frame.
        JFrame frame = new JFrame("Four in a row - Lunicore");

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        boardPanel = new BoardPanel();
        frame.add(boardPanel);
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("First Menu");
        menu.getAccessibleContext().setAccessibleDescription(
                "The only menu in this program that has menu items");

        JMenuItem menuItem = new JMenuItem("String here");

        menu.add(menuItem);

        menuBar.add(menu);

        frame.setJMenuBar(menuBar);
        frame.setSize(500, 500);

        frame.setVisible(true);
    }

    @Override
    public void update(Observable o, Object arg) {
        String[][] board = model.getBoard();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {

                Rectangle circle = new Rectangle(i*boardWidth/7, j*boardHeight/6, )
                boardPanel.addPiece();
            }
        }
    }
}
