package Primitives.PrimitivePainters;

import java.awt.BasicStroke;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import Primitives.Interfaces.PrimitivePainter;
import Primitives.Primitives.Ellipse;

public class EllipsePainter implements PrimitivePainter<Ellipse> {
    @Override
    public void paint(Graphics2D g2D, Ellipse primitive) {
        Graphics2D g2DClone = (Graphics2D) g2D.create();

        try {
            double x = primitive.getX();
            double y = primitive.getY();
            double width = primitive.getWidth();
            double height = primitive.getHeight();
            Ellipse2D.Double ellipse = new Ellipse2D.Double(x, y, width, height);
            g2DClone.setColor(primitive.getFillColor());
            g2DClone.fill(ellipse);
            g2DClone.setColor(primitive.getStrokeColor());
            g2DClone.setStroke(new BasicStroke(1));
            g2DClone.draw(ellipse);
        } finally {
            g2DClone.dispose();
        }
    }
}