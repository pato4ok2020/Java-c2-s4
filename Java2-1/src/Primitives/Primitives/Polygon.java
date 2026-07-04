package Primitives.Primitives;

import java.awt.Color;
import java.util.Arrays;

import Primitives.Interfaces.Primitive;
import Primitives.Interfaces.PrimitivePainter;
import Primitives.PrimitivePainters.PolygonPainter;

public class Polygon implements Primitive<Polygon> {
    private double[][] points;
    private Color strokeColor, fillColor;
    private int layer;

    public Polygon(
        double[][] points,
        Color fillColor, Color strokeColor, int layer
    ) {
        if (points.length < 3) {
            throw new IllegalArgumentException("Минимальное кол-во точек - 3!");
        }
        this.points = Arrays.copyOf(points, points.length);
        this.fillColor = fillColor;
        this.strokeColor = strokeColor;
        this.layer = layer; 
    }

    public double getX(int i) {
        return points[i][0];
    }

    public double getY(int i) {
        return points[i][1];
    }

    public int getAmountPoints() {
        return points.length;
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
    public PrimitivePainter<Polygon> getPainter() {
        return new PolygonPainter();
    }
}