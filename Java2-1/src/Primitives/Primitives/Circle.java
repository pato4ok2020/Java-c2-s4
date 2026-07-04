package Primitives.Primitives;

import java.awt.Color;

import Primitives.Interfaces.Primitive;
import Primitives.Interfaces.PrimitivePainter;
import Primitives.PrimitivePainters.CirclePainter;

public class Circle implements Primitive<Circle> {
    private double x, y, r;
    private Color strokeColor, fillColor;
    private int layer;

    public Circle(
        double x, double y, double r,
        Color fillColor, Color strokeColor, int layer
    ) {
        this.x = x;
        this.y = y;
        this.r = r;
        this.fillColor = fillColor;
        this.strokeColor = strokeColor;
        this.layer = layer; 
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getRadius() {
        return r;
    }

    @Override
    public Color getStrokeColor() {
        return strokeColor;
    }

    @Override
    public Color getFillColor() {
        return fillColor;
    }

    @Override
    public int getLayer() {
        return layer;
    }

    @Override
    public PrimitivePainter<Circle> getPainter() {
        return new CirclePainter();
    }
}