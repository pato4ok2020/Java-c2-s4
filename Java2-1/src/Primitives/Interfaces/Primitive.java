package Primitives.Interfaces;

import java.awt.Color;

public interface Primitive<T extends Primitive<T>> {
    Color getStrokeColor();
    Color getFillColor();
    int getLayer();
    PrimitivePainter<T> getPainter();
}