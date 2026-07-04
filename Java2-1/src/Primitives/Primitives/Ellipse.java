package Primitives.Primitives;

import java.awt.Color;

import Primitives.Interfaces.Primitive;
import Primitives.Interfaces.PrimitivePainter;
import Primitives.PrimitivePainters.EllipsePainter;

public class Ellipse implements Primitive<Ellipse> {
    private double x, y, width, height;
    private Color strokeColor, fillColor;
    private int layer;

    public Ellipse(
        double x, double y, double width, double height,
        Color fillColor, Color strokeColor, int layer
    ) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
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

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
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
    public PrimitivePainter<Ellipse> getPainter() {
        return new EllipsePainter();
    }
}