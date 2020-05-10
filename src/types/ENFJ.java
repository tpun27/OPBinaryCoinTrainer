package types;

import factory.AbstractTypeFactory;

public class ENFJ extends Type {
    public ENFJ(String name, String type) {
        super(name, type);
    }

    public static AbstractTypeFactory createFactory() {
        return new ENFJTypeFactory();
    }

    static class ENFJTypeFactory implements AbstractTypeFactory<ENFJ> {
        public ENFJ create(String name, String type) {
            return new ENFJ(name, type);
        }
    }
}
