package Primitives.PrimitiveParsers;

import Primitives.Interfaces.PrimitiveParser;
import Primitives.Primitives.Rectangle;
import file.PrimitiveParserException;

import static file.GeneralPrimitiveParser.parseColor;

import java.awt.Color;

public class RectangleParser implements PrimitiveParser<Rectangle> {
    @Override
    public Rectangle parse(String[] primitiveArgs) throws PrimitiveParserException {
        try {
            if (primitiveArgs.length != 8) {
                throw new PrimitiveParserException(
                    "Конструктор для RECTANGLE: RECTANGLE layer r:g:b r:g:b x y width height"
                );
            }
            int layer = Integer.parseInt(primitiveArgs[1]);
            Color fillColor = parseColor(primitiveArgs[2]);
            Color strokeColor = parseColor(primitiveArgs[3]);
            double x = Double.parseDouble(primitiveArgs[4]);
            double y = Double.parseDouble(primitiveArgs[5]);
            double width = Double.parseDouble(primitiveArgs[6]);
            double height = Double.parseDouble(primitiveArgs[7]);
            return new Rectangle(x, y, width, height, fillColor, strokeColor, layer);
        } catch (Exception e) {
            throw new PrimitiveParserException(
                "Ошибка парсинга прямоугольника!\n" + 
                e.getMessage()
            );
        }
    }
}