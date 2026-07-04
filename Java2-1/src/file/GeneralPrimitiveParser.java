package file;

import java.awt.Color;
import java.util.HashMap;
import java.util.Map;

import Primitives.Interfaces.Primitive;
import Primitives.Interfaces.PrimitiveParser;
import Primitives.PrimitiveParsers.CircleParser;
import Primitives.PrimitiveParsers.EllipseParser;
import Primitives.PrimitiveParsers.PolygonParser;
import Primitives.PrimitiveParsers.PolylineParser;
import Primitives.PrimitiveParsers.RectangleParser;
import Primitives.PrimitiveParsers.SegmentParser;

public final class GeneralPrimitiveParser {
    private GeneralPrimitiveParser() {}

    private static final Map<String, PrimitiveParser<?>> parsers;
    static {
        parsers = new HashMap<>();
        parsers.put("ELLIPSE", new EllipseParser());
        parsers.put("RECTANGLE", new RectangleParser());
        parsers.put("SEGMENT", new SegmentParser());
        parsers.put("POLYLINE", new PolylineParser());
        parsers.put("CIRCLE", new CircleParser());
        parsers.put("POLYGON", new PolygonParser());
    }

    public static Primitive<?> parse(String primitiveString) throws PrimitiveParserException {
        String[] primitiveArgs = primitiveString.strip().split(" ");

        String primitiveName = primitiveArgs[0].toUpperCase();
        if (parsers.containsKey(primitiveName)) {
            return parsers.get(primitiveName).parse(primitiveArgs);
        } else {
            throw new PrimitiveParserException("Данный примитив не поддреживается: " + primitiveName + "!");
        }
    }

    public static Color parseColor(String colorString) {
        String[] rgbString = colorString.split(":");
        int r = Integer.parseInt(rgbString[0]);
        int g = Integer.parseInt(rgbString[1]);
        int b = Integer.parseInt(rgbString[2]);
        return new Color(r, g, b);
    }
}