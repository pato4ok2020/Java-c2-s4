package Primitives.PrimitiveParsers;

import java.awt.Color;

import Primitives.Interfaces.PrimitiveParser;
import Primitives.Primitives.Circle;
import file.PrimitiveParserException;

import static file.GeneralPrimitiveParser.parseColor;

public class CircleParser implements PrimitiveParser<Circle> {
    @Override
    public Circle parse(String[] primitiveArgs) throws PrimitiveParserException {
        try {
            if (primitiveArgs.length != 7) {
                throw new PrimitiveParserException("Конструктор для CIRCLE: CIRCLE layer r:g:b r:g:b x y r ");
            }
            int layer = Integer.parseInt(primitiveArgs[1]);
            Color fillColor = parseColor(primitiveArgs[2]);
            Color strokeColor = parseColor(primitiveArgs[3]);
            double x = Double.parseDouble(primitiveArgs[4]);
            double y = Double.parseDouble(primitiveArgs[5]);
            double r = Double.parseDouble(primitiveArgs[6]);
            return new Circle(x, y, r, fillColor, strokeColor, layer);
        } catch (Exception e) {
            throw new PrimitiveParserException(
                "Ошибка парсинга эллипса!\n" + 
                e.getMessage()
            );
        }
    }
}