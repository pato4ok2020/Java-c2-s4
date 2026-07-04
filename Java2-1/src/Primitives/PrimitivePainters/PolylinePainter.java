package Primitives.PrimitivePainters;

import java.awt.BasicStroke;
import java.awt.Graphics2D;
import java.awt.geom.Path2D;
import Primitives.Interfaces.PrimitivePainter;
import Primitives.Primitives.Polyline;

public class PolylinePainter implements PrimitivePainter<Polyline> {
    @Override
    public void paint(Graphics2D g2D, Polyline primitive) {
        Graphics2D g2DClone = (Graphics2D) g2D.create();

        try {
            Path2D.Double polyline = new Path2D.Double();
            polyline.moveTo(primitive.getX(0), primitive.getY(0));
            for (int i = 1; i < primitive.getAmountPoints(); i++) {
                polyline.lineTo(primitive.getX(i), primitive.getY(i));
            }
            g2DClone.setColor(primitive.getFillColor());
            g2DClone.setStroke(new BasicStroke(1));
            g2DClone.draw(polyline);
        } finally {
            g2DClone.dispose();
        }
    }
}