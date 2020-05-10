package types;

import factory.AbstractTypeFactory;

public class INFJ extends Type {
    public INFJ(String name, String type) {
        super(name, type);
    }

    public static AbstractTypeFactory createFactory() {
        return new INFJTypeFactory();
    }

    static class INFJTypeFactory implements AbstractTypeFactory<INFJ> {
        public INFJ create(String name, String type) {
            return new INFJ(name, type);
        }
    }
}
