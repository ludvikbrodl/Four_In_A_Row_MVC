package com.LudvikBrodl;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;

/**
 * Created by Ludde on 2015-10-02.
 */
public class BoardPanel extends JPanel {
    private ArrayList<ColoredEllipse> list = new ArrayList<ColoredEllipse>();

    public Dimension getPreferredSize() {
        return new Dimension(500,500);
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        super.setBackground(Color.DARK_GRAY);

        for(ColoredEllipse coloredEllipse : list) {
            g.setColor(coloredEllipse.getColor());
            Rectangle r = coloredEllipse.getRectangle();
            g.fillOval((int)r.getX(),(int) r.getY(),
                    (int)r.getWidth(), (int)r.getHeight());
        }
        g.setColor(Color.RED);
        g.drawString("This is the board",20,20);
    }

    public void addPiece(Rectangle circle, Color color) {
        list.add(new ColoredEllipse(circle, color));
    }

    private class ColoredEllipse {
        private Color circleColor;
        private Rectangle rectangle;

        public ColoredEllipse(Rectangle rectangle, Color color) {
            circleColor = color;
            this.rectangle = rectangle;
        }

        public Color getColor() {
            return circleColor;
        }

        public Rectangle getRectangle() {
            return rectangle;
        }
    }
}
