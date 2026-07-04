package Primitives.PrimitiveParsers;

import Primitives.Interfaces.PrimitiveParser;
import Primitives.Primitives.Polygon;
import file.PrimitiveParserException;

import static file.GeneralPrimitiveParser.parseColor;

import java.awt.Color;

public class PolygonParser implements PrimitiveParser<Polygon> {
    @Override
    public Polygon parse(String[] primitiveArgs) throws PrimitiveParserException {
        try {
            if (primitiveArgs.length % 2 != 0) {
                throw new PrimitiveParserException("Конструктор для POLYGON: POLYGON layer r:g:b r:g:b x1 y1 x2 y2 ... xn yn");
            }
            int layer = Integer.parseInt(primitiveArgs[1]);
            Color fillColor = parseColor(primitiveArgs[2]);
            Color strokeColor = parseColor(primitiveArgs[3]);
            int amountPoints = (primitiveArgs.length - 4) / 2;
            double[][] points = new double[amountPoints][2];
            for (int i = 0, j = 4; i < points.length; i++, j += 2) {
                points[i][0] = Double.parseDouble(primitiveArgs[j]);
                points[i][1] = Double.parseDouble(primitiveArgs[j + 1]);
            }
            return new Polygon(points, fillColor, strokeColor, layer);
        } catch (Exception e) {
            throw new PrimitiveParserException(
                "Ошибка парсинга полигона!\n" + 
                e.getMessage()
            );
        }
    }
}