package Primitives.Primitives;

import java.awt.Color;

import Primitives.Interfaces.Primitive;
import Primitives.Interfaces.PrimitivePainter;
import Primitives.PrimitivePainters.SegmentPainter;

public class Segment implements Primitive<Segment> {
    private double x1, y1, x2, y2;
    private Color fillColor;
    private int layer;

    public Segment(
        double x1, double y1, double x2, double y2, Color fillColor, int layer
    ) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.fillColor = fillColor;
        this.layer = layer; 
    }

    public double getX1() {
        return x1;
    }

    public double getY1() {
        return y1;
    }

    public double getX2() {
        return x2;
    }

    public double getY2() {
        return y2;
    }

    @Override
    public Color getStrokeColor() {
        return null;
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
    public PrimitivePainter<Segment> getPainter() {
        return new SegmentPainter();
    }
}