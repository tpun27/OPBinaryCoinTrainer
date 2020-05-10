package types;

import factory.AbstractTypeFactory;

public class ISFJ extends Type {
    public ISFJ(String name, String type) {
        super(name, type);
    }

    public static AbstractTypeFactory createFactory() {
        return new ISFJTypeFactory();
    }

    static class ISFJTypeFactory implements AbstractTypeFactory<ISFJ> {
        public ISFJ create(String name, String type) {
            return new ISFJ(name, type);
        }
    }
}
