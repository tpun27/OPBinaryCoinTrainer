package factory;

import types.Type;

public interface AbstractTypeFactory<T extends Type> {
    T create(String name, String type);
}
