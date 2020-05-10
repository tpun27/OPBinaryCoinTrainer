package types;

import factory.AbstractTypeFactory;

public class ENTP extends Type {
    public ENTP(String name, String type) {
        super(name, type);
    }

    public static AbstractTypeFactory createFactory() {
        return new ENTPTypeFactory();
    }

    static class ENTPTypeFactory implements AbstractTypeFactory<ENTP> {
        public ENTP create(String name, String type) {
            return new ENTP(name, type);
        }
    }
}
