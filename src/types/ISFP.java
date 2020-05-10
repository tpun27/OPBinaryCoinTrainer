package types;

import factory.AbstractTypeFactory;

public class ISFP extends Type {
    public ISFP(String name, String type) {
        super(name, type);
    }

    public static AbstractTypeFactory createFactory() {
        return new ISFPTypeFactory();
    }

    static class ISFPTypeFactory implements AbstractTypeFactory<ISFP> {
        public ISFP create(String name, String type) {
            return new ISFP(name, type);
        }
    }
}
