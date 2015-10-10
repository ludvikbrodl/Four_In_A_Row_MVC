import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

/**
 * Visualization of a 'Four In A Row' game. Mouse clicks on the columns will
 * tell the controller to put a game piece in the specified column.
 * Resizing/scaling is very primitive and stretches to fill the JPanel - thusly the aspect ratio is not fixed.
 * Future features could include an option to retain aspect ratio when resizing.
 * 
 * <h3>Current know limitations: </h3>
 * <ul>
 * <li>
 * Resizing/scaling is very primitive and stretches to fill the JPanel - thusly the aspect ratio is not fixed.
 * Future features could include an option to retain aspect ratio when resizing.
 * </li>
 * <li> 
 * Spamming mouse clicks locks the application while the spamming is done and/or gives unexcptected behaviour.
 * </li>
 * </ul>
 * @author Ludde
 *
 */
public class BoardPanel extends JPanel implements MouseListener, ComponentListener {
	private static final long serialVersionUID = -7974921651863132858L;
	private ArrayList<GamePiece> list = new ArrayList<GamePiece>();
	private Controller controller;

	/**
	 * Creates a BoardPanel that communicates with a controller to a four in a row model.
	 * @param controller the controller that takes input and gives them to the model.
	 */
	public BoardPanel(Controller controller) {
		super();
		super.addMouseListener(this);
		super.addComponentListener(this);
		this.controller = controller;

	}

	@Override
	public Dimension getPreferredSize() {
		return new Dimension(600, 600);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		super.setBackground(Color.DARK_GRAY);

		for (GamePiece coloredEllipse : list) {
			coloredEllipse.paint(g, getSize());
		}
	}

	/**
	 * Adds a game piece to the GUI.
	 * @param row the row index of where the game piece should be placed. Currently only supports up to 6 rows.
	 * @param col the col index of where the game piece should be placed. Currently only supports up to 7 columns.
	 * @param color the color of the game piece.
	 */
	public void addGamePiece(int row, int col, Color color) {
		list.add(new GamePiece(col, row, color));

	}

	/**
	 * Removes and game pieces from the GUI.
	 */
	public void clearGamePieces() {
		list.clear();
	}

	/**
	 * Representation of a game piece in the GUI.
	 * @author Ludde
	 *
	 */
	private class GamePiece {
		private Color gamePieceColor;
		private int xLoc, yLoc;
		private static final int PADDING = 5; // pixels
		private int diameter = 0;

		/**
		 * Creates a new game piece.
		 * @param xLoc the horizontal index of the game piece. Starts at 1. Currently only supports up to 6.
		 * @param yLoc the vertical index of the game piece. Starts 1. Currently only supports up to 7.
		 * @param color
		 */
		public GamePiece(int xLoc, int yLoc, Color color) {
			gamePieceColor = color;
			this.xLoc = xLoc;
			this.yLoc = yLoc;
		}

		/**
		 * Paint the game piece inside the specified dimension.
		 * @param g the graphics to use.
		 * @param dimension the size of the available canvas of the graphic.
		 */
		public void paint(Graphics g, Dimension dimension) {
			// System.out.println(r.getX() + " " + r.getY() +
			// coloredEllipse.getColor().toString());
			int width = dimension.width;
			int height = dimension.height;
			int ellipseX = PADDING + xLoc * (width / 7);
			int ellipseY = PADDING + yLoc * (height / 6);
			//Calculating diameter of every game piece is not necessary, optimization possible.
			diameter = (Math.min(width, height) + PADDING) / 7;
			
			Graphics2D g2d = (Graphics2D) g;
			//edge smoothing, so nice.
			g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			g2d.setColor(gamePieceColor);
			g2d.fillOval(ellipseX, ellipseY, diameter, diameter);
		}

	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		int row = arg0.getX() / (getWidth() / 7);
		controller.playRow(row);

	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void componentHidden(ComponentEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void componentMoved(ComponentEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void componentResized(ComponentEvent arg0) {
		// for padding left/right or top/bottom and retaining aspect ratio, TODO

	}

	@Override
	public void componentShown(ComponentEvent arg0) {
		// TODO Auto-generated method stub

	}

}
