package GUI;

import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import Primitives.Interfaces.Primitive;

public class PrimitivePicture extends JPanel {
    private Primitive<?> primitive;
    private double scaleX;
    private double scaleY;

    public PrimitivePicture(Primitive<?> primitive, double scaleX, double scaleY) {
        this.primitive = primitive;
        this.scaleX = scaleX;
        this.scaleY = scaleY;
        setOpaque(false);
    }

    private <T extends Primitive<T>> void paintHelper(Graphics2D g2d, Primitive<?> primitive) {
        Primitive<T> typedPrimitive = (Primitive<T>) primitive;
        typedPrimitive.getPainter().paint(g2d, (T) primitive);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2DClone = (Graphics2D) g.create();;
    
        try {
            g2DClone.translate(getWidth() / 2, getHeight() / 2);
            g2DClone.scale(scaleX, -scaleY);
            paintHelper(g2DClone, primitive);
        } finally {
            g2DClone.dispose();
        }
    }
}