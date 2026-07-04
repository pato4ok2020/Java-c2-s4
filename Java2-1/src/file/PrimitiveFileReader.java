package file;

import java.util.ArrayList;
import java.util.List;

import Primitives.Interfaces.Primitive;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public final class PrimitiveFileReader {
    private PrimitiveFileReader() {}
    
    private static List<String> readLines(String filename) throws IOException {
        Path path = Paths.get(filename);

        try (BufferedReader fileReader = Files.newBufferedReader(path)) {
            return fileReader.readAllLines();
        } catch (IOException e) {
            throw new IOException("Ошибка чтения примитивов из файла: " + e.getMessage() + "!");
        }
    }

    public static List<Primitive<?>> readPrimitives(String filename) throws IOException {
        List<String> primitiveStrings = readLines(filename);
        List<Primitive<?>> primitives = new ArrayList<>();

        for (int i = 0; i < primitiveStrings.size(); i++) {
            try {
                primitives.add(GeneralPrimitiveParser.parse(primitiveStrings.get(i)));
            } catch (Exception e) {
                System.out.println("Строка " + (i + 1) + " - ошибка чтения примитива!\n" + e.getMessage() + "\n");
            }
        }

        return primitives;
    } 
}