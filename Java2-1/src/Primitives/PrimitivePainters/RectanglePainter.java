package Primitives.PrimitivePainters;

import java.awt.BasicStroke;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import Primitives.Interfaces.PrimitivePainter;
import Primitives.Primitives.Rectangle;

public class RectanglePainter implements PrimitivePainter<Rectangle> {
    @Override
    public void paint(Graphics2D g2D, Rectangle primitive) {
        Graphics2D g2DClone = (Graphics2D) g2D.create();

        try {
            double x = primitive.getX();
            double y = primitive.getY();
            double width = primitive.getWidth();
            double height = primitive.getHeight();
            Rectangle2D.Double rectangle = new Rectangle2D.Double(x, y, width, height);
            g2DClone.setColor(primitive.getFillColor());
            g2DClone.fill(rectangle);
            g2DClone.setColor(primitive.getStrokeColor());
            g2DClone.setStroke(new BasicStroke(1));
            g2DClone.draw(rectangle);
        } finally {
            g2DClone.dispose();
        }
    }
}