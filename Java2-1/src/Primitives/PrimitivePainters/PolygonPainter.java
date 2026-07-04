package Primitives.PrimitivePainters;

import java.awt.BasicStroke;
import java.awt.Graphics2D;
import java.awt.geom.Path2D;
import Primitives.Interfaces.PrimitivePainter;
import Primitives.Primitives.Polygon;

public class PolygonPainter implements PrimitivePainter<Polygon> {
    @Override
    public void paint(Graphics2D g2D, Polygon primitive) {
        Graphics2D g2DClone = (Graphics2D) g2D.create();

        try {
            Path2D.Double polygon = new Path2D.Double();
            polygon.moveTo(primitive.getX(0), primitive.getY(0));
            for (int i = 1; i < primitive.getAmountPoints(); i++) {
                polygon.lineTo(primitive.getX(i), primitive.getY(i));
            }
            polygon.closePath();
            g2DClone.setColor(primitive.getFillColor());
            g2DClone.fill(polygon);
            g2DClone.setColor(primitive.getStrokeColor());
            g2DClone.setStroke(new BasicStroke(1));
            g2DClone.draw(polygon);
        } finally {
            g2DClone.dispose();
        }
    }
}