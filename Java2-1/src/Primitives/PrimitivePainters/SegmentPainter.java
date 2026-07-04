package Primitives.PrimitivePainters;

import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import Primitives.Interfaces.PrimitivePainter;
import Primitives.Primitives.Segment;

public class SegmentPainter implements PrimitivePainter<Segment> {
    @Override
    public void paint(Graphics2D g2D, Segment primitive) {
        Graphics2D g2DClone = (Graphics2D) g2D.create();

        try {
            double x1 = primitive.getX1();
            double y1 = primitive.getY1();
            double x2 = primitive.getX2();
            double y2 = primitive.getY2();
            Line2D.Double segment = new Line2D.Double(x1, y1, x2, y2);
            g2DClone.setColor(primitive.getFillColor());
            g2DClone.fill(segment);
            g2DClone.draw(segment);
        } finally {
            g2DClone.dispose();
        }
    }
}