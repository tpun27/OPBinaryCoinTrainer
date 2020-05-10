package types;

import factory.AbstractTypeFactory;

public class INTJ extends Type {
    public INTJ(String name, String type) {
        super(name, type);
    }

    public static AbstractTypeFactory createFactory() {
        return new INTJTypeFactory();
    }

    static class INTJTypeFactory implements AbstractTypeFactory<INTJ> {
        public INTJ create(String name, String type) {
            return new INTJ(name, type);
        }
    }
}
