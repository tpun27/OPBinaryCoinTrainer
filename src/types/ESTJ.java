package types;

import factory.AbstractTypeFactory;

public class ESTJ extends Type {
    public ESTJ(String name, String type) {
        super(name, type);
    }

    public static AbstractTypeFactory createFactory() {
        return new ESTJTypeFactory();
    }

    static class ESTJTypeFactory implements AbstractTypeFactory<ESTJ> {
        public ESTJ create(String name, String type) {
            return new ESTJ(name, type);
        }
    }
}
