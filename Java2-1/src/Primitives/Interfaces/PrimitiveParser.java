package Primitives.Interfaces;

import file.PrimitiveParserException;

public interface PrimitiveParser<T extends Primitive<T>> {
    T parse(String[] primitiveArgs) throws PrimitiveParserException;
}