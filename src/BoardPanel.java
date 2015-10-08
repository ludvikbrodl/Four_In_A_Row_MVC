import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;

/**
 * Created by Ludde on 2015-10-02.
 */
public class BoardPanel extends JPanel implements MouseListener{
    /**
	 * 
	 */
	private static final long serialVersionUID = -7974921651863132858L;
	private ArrayList<ColoredEllipse> list = new ArrayList<ColoredEllipse>();
	private Controller controller;
    
    
    public BoardPanel(Controller controller) {
    	super();
    	super.addMouseListener(this);
    	this.controller = controller;
    }
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(500,500);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        super.setBackground(Color.DARK_GRAY);
        
        for(ColoredEllipse coloredEllipse : list) {
        	coloredEllipse.paint(g, getSize());
        }
    }

    /**
     * 
     * @param row row
     * @param col col
     * @param color
     */
	public void addPiece(int row, int col, Color color) {    	
    	list.add(new ColoredEllipse(col, row, color));
		
	}
	
	public void clearPieces() {
		list.clear();
	}

    private class ColoredEllipse {
        private Color circleColor;
        private int xLoc,yLoc;
        private static final int PADDING = 5; //pixels
        private int diameter = 0;
        
        //integer locations in order to have dynamic coordinates
        public ColoredEllipse(int xLoc, int yLoc, Color color) {
            circleColor = color;
            this.xLoc = xLoc;
            this.yLoc = yLoc;
        }
     
        //currenyl calcs diameter for every ellipse, not necassary.
        public void paint(Graphics g, Dimension dimension) {
        	 g.setColor(circleColor);
        	 // System.out.println(r.getX() + " " + r.getY() + 
             //		coloredEllipse.getColor().toString());
        	 int width = dimension.width;
        	 int height = dimension.height;
        	 int ellipseX = PADDING + xLoc * (width / 7);
        	 int ellipseY = PADDING + yLoc * (height / 6);
        	 diameter = (Math.min(width, height) + PADDING) / 7;
        	 
             g.fillOval(ellipseX, ellipseY, diameter, diameter);
             
        }
        
    }

    //current know limitation, spamming mouse clicks 
    //in different x positions will give unexpected behaviour.
	@Override
	public void mouseClicked(MouseEvent arg0) {
		int row = arg0.getX() / (getWidth() / 7);
		System.out.println(arg0.getX() + " row " + row);
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


}
