package types;

import factory.AbstractTypeFactory;

public class INTP extends Type {
    public INTP(String name, String type) {
        super(name, type);
    }

    public static AbstractTypeFactory createFactory() {
        return new INTPTypeFactory();
    }

    static class INTPTypeFactory implements AbstractTypeFactory<INTP> {
        public INTP create(String name, String type) {
            return new INTP(name, type);
        }
    }
}
