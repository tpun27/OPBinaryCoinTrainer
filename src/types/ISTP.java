package types;

import factory.AbstractTypeFactory;

public class ISTP extends Type {
    public ISTP(String name, String type) {
        super(name, type);
    }

    public static AbstractTypeFactory createFactory() {
        return new ISTPTypeFactory();
    }

    static class ISTPTypeFactory implements AbstractTypeFactory<ISTP> {
        public ISTP create(String name, String type) {
            return new ISTP(name, type);
        }
    }
}
