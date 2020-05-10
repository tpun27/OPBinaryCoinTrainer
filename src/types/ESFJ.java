package types;

import factory.AbstractTypeFactory;

public class ESFJ extends Type {
    public ESFJ(String name, String type) {
        super(name, type);
    }

    public static AbstractTypeFactory createFactory() {
        return new ESFJTypeFactory();
    }

    static class ESFJTypeFactory implements AbstractTypeFactory<ESFJ> {
        public ESFJ create(String name, String type) {
            return new ESFJ(name, type);
        }
    }
}
