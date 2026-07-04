import java.util.List;

import javax.swing.JOptionPane;

import GUI.MainFrame;
import Primitives.Interfaces.Primitive;
import file.PrimitiveFileReader;

public class Main {
    public static void main(String[] args) {
        try {
            String filename = "src/Resources/shapes.txt";
            List<Primitive<?>> primitives = PrimitiveFileReader.readPrimitives(filename);
            new MainFrame(primitives);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
}