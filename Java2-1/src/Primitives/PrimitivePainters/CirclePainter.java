package Primitives.PrimitivePainters;

import java.awt.BasicStroke;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import Primitives.Interfaces.PrimitivePainter;
import Primitives.Primitives.Circle;

public class CirclePainter implements PrimitivePainter<Circle> {
    @Override
    public void paint(Graphics2D g2D, Circle primitive) {
        Graphics2D g2DClone = (Graphics2D) g2D.create();

        try {
            double x = primitive.getX() - primitive.getRadius();
            double y = primitive.getY() - primitive.getRadius();
            double r = primitive.getRadius();
            Ellipse2D.Double circle = new Ellipse2D.Double(x, y, 2 * r, 2 * r);
            g2DClone.setColor(primitive.getFillColor());
            g2DClone.fill(circle);
            g2DClone.setColor(primitive.getStrokeColor());
            g2DClone.setStroke(new BasicStroke(1));
            g2DClone.draw(circle);
        } finally {
            g2DClone.dispose();
        }
    }
}