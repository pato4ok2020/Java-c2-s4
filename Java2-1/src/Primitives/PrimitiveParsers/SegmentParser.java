package Primitives.PrimitiveParsers;

import java.awt.Color;

import Primitives.Interfaces.PrimitiveParser;
import Primitives.Primitives.Segment;
import file.PrimitiveParserException;

import static file.GeneralPrimitiveParser.parseColor;

public class SegmentParser implements PrimitiveParser<Segment> {
    @Override
    public Segment parse(String[] primitiveArgs) throws PrimitiveParserException {
        try {
            if (primitiveArgs.length != 7) {
                throw new PrimitiveParserException(
                    "Конструктор для SEGMENT: SEGMENT layer r:g:b x1 y1 x2 y2"
                );
            }
            int layer = Integer.parseInt(primitiveArgs[1]);
            Color fillColor = parseColor(primitiveArgs[2]);
            double x1 = Double.parseDouble(primitiveArgs[3]);
            double y1 = Double.parseDouble(primitiveArgs[4]);
            double x2 = Double.parseDouble(primitiveArgs[5]);
            double y2 = Double.parseDouble(primitiveArgs[6]);
            return new Segment(x1, y1, x2, y2, fillColor, layer);
        } catch (Exception e) {
            throw new PrimitiveParserException(
                "Ошибка парсинга отрезка!\n" + 
                e.getMessage()
            );
        }
    }
}