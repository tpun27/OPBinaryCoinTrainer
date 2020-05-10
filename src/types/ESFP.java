package types;

import factory.AbstractTypeFactory;

public class ESFP extends Type {
    public ESFP(String name, String type) {
        super(name, type);
    }

    public static AbstractTypeFactory createFactory() {
        return new ESFPTypeFactory();
    }

    static class ESFPTypeFactory implements AbstractTypeFactory<ESFP> {
        public ESFP create(String name, String type) {
            return new ESFP(name, type);
        }
    }
}
