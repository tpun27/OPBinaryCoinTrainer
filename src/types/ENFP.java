package types;

import factory.AbstractTypeFactory;

public class ENFP extends Type {
    public ENFP(String name, String type) {
        super(name, type);
    }

    public static AbstractTypeFactory createFactory() {
        return new ENFPTypeFactory();
    }

    static class ENFPTypeFactory implements AbstractTypeFactory<ENFP> {
        public ENFP create(String name, String type) {
            return new ENFP(name, type);
        }
    }
}
