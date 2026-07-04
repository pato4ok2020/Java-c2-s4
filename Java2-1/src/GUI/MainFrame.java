package GUI;

import javax.swing.*;

import Primitives.Interfaces.Primitive;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class MainFrame extends JFrame {
    public MainFrame(Iterable<Primitive<?>> primitives) {
        super("Рисунок");

        final int FRAME_WIDTH = 800;
        final int FRAME_HEIGHT = 600;
        final double SCREEN_WIDTH = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
        final double SCREEN_HEIGHT = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
        final int FRAME_X = (int)((SCREEN_WIDTH - FRAME_WIDTH) / 2);
        final int FRAME_Y = (int)((SCREEN_HEIGHT - FRAME_HEIGHT) / 2);
        
        double[] scale = new double[] {1, 1};
        setBounds(FRAME_X, FRAME_Y, FRAME_WIDTH, FRAME_HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Picture picture = new Picture(primitives, scale);
        picture.setBounds(0, 0, getWidth(), getHeight());
        add(picture);

        KeyListener scaleKeyListener = new KeyListener() {

            @Override
            public void keyTyped(KeyEvent e) {
                final double step = 0.01;
                if (e.getKeyChar() == '-' && scale[0] > 0.0001) {
                    scale[0] -= step;
                    scale[1] -= step;
                } else if (e.getKeyChar() == '+') {
                    scale[0] += step;
                    scale[1] += step;
                }
            }

            @Override
            public void keyPressed(KeyEvent e) {}

            @Override
            public void keyReleased(KeyEvent e) {}
        };
        addKeyListener(scaleKeyListener);

        validate();
        setVisible(true);
    }
}