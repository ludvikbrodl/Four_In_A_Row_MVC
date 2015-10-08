import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by Ludde on 2015-10-02.
 */
public class View implements Observer {
    private Color player1Color = Color.RED;
    private Color player2Color = Color.YELLOW;
    
    private Model model;
    private Controller controller;
    private BoardPanel boardPanel;


    public View(Controller controller, Model model) {
        this.model = model;
        this.controller = controller;
    }

    public void open() {
        JFrame frame = new JFrame("Four in a row - Lunicore");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        boardPanel = new BoardPanel(controller);
        frame.add(boardPanel);
        
        JMenuBar menuBar = new JMenuBar();
        
        JMenu menu = new JMenu("Menu");
        JMenuItem resetMenuItem = new ResetButton(controller);
        JMenuItem highScoreMenuItem = new HighScoreButton(controller);
        JMenuItem saveGameMenuItem = new SaveGameButton(controller);
        JMenuItem loadGameMenuItem = new LoadGameButton(controller);
        JMenuItem setPlayerNameMenuItem = new SetPlayerNameMenuItem(controller);
        menu.add(resetMenuItem);
        menu.add(setPlayerNameMenuItem);
        menu.add(highScoreMenuItem);
        menu.add(saveGameMenuItem);
        menu.add(loadGameMenuItem);

        //hax, don't want to have to handle layouts so 
        StateDisplay stateMenu = new StateDisplay(model);
        
        menuBar.add(menu);
        menuBar.add(stateMenu);
        
        frame.setJMenuBar(menuBar);
        frame.setSize(500, 500);

        frame.setVisible(true);
        
        update(null, null);
    }

    @Override
    public void update(Observable o, Object arg) {
    	boardPanel.clearPieces();
        String[][] board = model.getBoard();
//        for (int i = 0; i < board.length; i++) {
//			for (int j = 0; j < board[i].length; j++) {
//				System.out.print(board[i][j] + " ");
//			}
//			System.out.println();
//		}
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
            	
            	Color curColor = Color.BLACK; //Error checking
        		try {
					if (model.getPlayerName(1).equals(board[i][j])) {
						curColor = player1Color;
					} else if (model.getPlayerName(2).equals(board[i][j])) {
						curColor = player2Color;
					} else {
						curColor = Color.WHITE;
					}
				} catch (IllegalArgumentException e) {
					//Currently this will never happen, 
					//it's here for future implementation of more than 2 players.
					e.printStackTrace();
				}
                boardPanel.addPiece(board.length - 1 - i, j, curColor);
            }
        }
        boardPanel.repaint();
        
        
       
    }
}
