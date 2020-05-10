package types;

import factory.AbstractTypeFactory;

public class ESTP extends Type {
    public ESTP(String name, String type) {
        super(name, type);
    }

    public static AbstractTypeFactory createFactory() {
        return new ESTPTypeFactory();
    }

    static class ESTPTypeFactory implements AbstractTypeFactory<ESTP> {
        public ESTP create(String name, String type) {
            return new ESTP(name, type);
        }
    }
}
