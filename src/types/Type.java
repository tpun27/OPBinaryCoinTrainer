package types;

import factory.AbstractTypeFactory;

public abstract class Type {
    String name;
    String type;

    Type(String name, String type) {
        this.name = name;
        this.type = type;
    }


}
