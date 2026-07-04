package Primitives.Interfaces;

import java.awt.Graphics2D;

public interface PrimitivePainter<T extends Primitive<T>> {
    void paint(Graphics2D g2D, T primitive);
}