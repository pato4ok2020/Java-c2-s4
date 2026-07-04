package GUI;

import java.awt.Graphics;

import javax.swing.JLayeredPane;

import Primitives.Interfaces.Primitive;

public class Picture extends JLayeredPane {
    private Iterable<Primitive<?>> primitives;
    private double[] scale;

    public Picture(Iterable<Primitive<?>> primitives, double[] scale) {
        if (scale.length != 2) {
            throw new IllegalArgumentException("scale должен иметь размер равный 2!");
        }
        this.primitives = primitives;
        this.scale = scale;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        removeAll();

        AxisPicture axisPicture = new AxisPicture();
        axisPicture.setBounds(0, 0, getWidth(), getHeight());
        setLayer(axisPicture, Integer.MIN_VALUE);
        add(axisPicture);

        for (Primitive<?> primitive : primitives) {
            PrimitivePicture primitivePicture = new PrimitivePicture(primitive, scale[0], scale[1]);
            primitivePicture.setBounds(0, 0, getWidth(), getHeight());
            setLayer(primitivePicture, primitive.getLayer());
            add(primitivePicture);
        }
    }
}