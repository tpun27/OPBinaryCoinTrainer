package types;

import factory.AbstractTypeFactory;

public class INFP extends Type {
    public INFP(String name, String type) {
        super(name, type);
    }

    public static AbstractTypeFactory createFactory() {
        return new INFPTypeFactory();
    }

    static class INFPTypeFactory implements AbstractTypeFactory<INFP> {
        public INFP create(String name, String type) {
            return new INFP(name, type);
        }
    }
}
