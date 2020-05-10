package types;

import factory.AbstractTypeFactory;

public class ENTJ extends Type {
    public ENTJ(String name, String type) {
        super(name, type);
    }

    public static AbstractTypeFactory createFactory() {
        return new ENTJTypeFactory();
    }

    static class ENTJTypeFactory implements AbstractTypeFactory<ENTJ> {
        public ENTJ create(String name, String type) {
            return new ENTJ(name, type);
        }
    }
}
