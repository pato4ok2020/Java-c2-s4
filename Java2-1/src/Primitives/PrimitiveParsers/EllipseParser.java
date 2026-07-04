package Primitives.PrimitiveParsers;

import java.awt.Color;

import Primitives.Interfaces.PrimitiveParser;
import Primitives.Primitives.Ellipse;
import file.PrimitiveParserException;

import static file.GeneralPrimitiveParser.parseColor;

public class EllipseParser implements PrimitiveParser<Ellipse> {
    @Override
    public Ellipse parse(String[] primitiveArgs) throws PrimitiveParserException {
        try {
            if (primitiveArgs.length != 8) {
                throw new PrimitiveParserException("Конструктор для ELLIPSE: ELLIPSE layer r:g:b r:g:b x y width height");
            }
            int layer = Integer.parseInt(primitiveArgs[1]);
            Color fillColor = parseColor(primitiveArgs[2]);
            Color strokeColor = parseColor(primitiveArgs[3]);
            double x = Double.parseDouble(primitiveArgs[4]);
            double y = Double.parseDouble(primitiveArgs[5]);
            double width = Double.parseDouble(primitiveArgs[6]);
            double height = Double.parseDouble(primitiveArgs[7]);
            return new Ellipse(x, y, width, height, fillColor, strokeColor, layer);
        } catch (Exception e) {
            throw new PrimitiveParserException(
                "Ошибка парсинга эллипса!\n" + 
                e.getMessage()
            );
        }
    }
}