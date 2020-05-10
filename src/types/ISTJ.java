package types;

import factory.AbstractTypeFactory;

public class ISTJ extends Type {
    public ISTJ(String name, String type) {
        super(name, type);
    }

    public static AbstractTypeFactory createFactory() {
        return new ISTJTypeFactory();
    }

    static class ISTJTypeFactory implements AbstractTypeFactory<ISTJ> {
        public ISTJ create(String name, String type) {
            return new ISTJ(name, type);
        }
    }
}
