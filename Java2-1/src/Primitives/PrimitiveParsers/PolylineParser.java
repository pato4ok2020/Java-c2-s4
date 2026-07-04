package Primitives.PrimitiveParsers;

import Primitives.Interfaces.PrimitiveParser;
import Primitives.Primitives.Polyline;
import file.PrimitiveParserException;

import static file.GeneralPrimitiveParser.parseColor;

import java.awt.Color;

public class PolylineParser implements PrimitiveParser<Polyline> {
    @Override
    public Polyline parse(String[] primitiveArgs) throws PrimitiveParserException {
        try {
            if (primitiveArgs.length % 2 == 0) {
                throw new PrimitiveParserException(
                    "Конструктор для POLYLINE: POLYLINE layer r:g:b x1 y1 x2 y2 ... xn yn"
                );
            }
            int layer = Integer.parseInt(primitiveArgs[1]);
            Color fillColor = parseColor(primitiveArgs[2]);
            int amountPoints = (primitiveArgs.length - 3) / 2;
            double[][] points = new double[amountPoints][2];
            for (int i = 0, j = 3; i < points.length; i++, j += 2) {
                points[i][0] = Double.parseDouble(primitiveArgs[j]);
                points[i][1] = Double.parseDouble(primitiveArgs[j + 1]);
            }
            return new Polyline(points, fillColor, layer);
        } catch (Exception e) {
            throw new PrimitiveParserException(
                "Ошибка парсинга ломаной!\n" + 
                e.getMessage()
            );
        }
    }
}