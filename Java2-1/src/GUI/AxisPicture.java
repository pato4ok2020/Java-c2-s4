package GUI;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;

import javax.swing.JPanel;

public class AxisPicture extends JPanel {
    public AxisPicture() {
        setOpaque(false);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2DClone = (Graphics2D) g.create();

        try {
            g2DClone.translate(getWidth() / 2, getHeight() / 2);
            g2DClone.scale(1, -1);
            g2DClone.setColor(Color.BLACK);
            g2DClone.draw(new Line2D.Double(-getWidth() / 2., 0, getWidth() / 2., 0));
            g2DClone.draw(new Line2D.Double(0, getHeight() / 2., 0, -getHeight() / 2.));
        } finally {
            g2DClone.dispose();
        }
    }
}