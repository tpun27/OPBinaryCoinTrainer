package factory;

import types.*;

import java.util.HashMap;

public class TypeFactoryFactory {
    static HashMap<String, AbstractTypeFactory<Type>> factoryMap;

    static {
        factoryMap = new HashMap<String, AbstractTypeFactory<Type>>() {{
            put("ENFJ", ENFJ.createFactory());
            put("ENFP", ENFP.createFactory());
            put("ENTJ", ENTJ.createFactory());
            put("ENTP", ENTP.createFactory());
            put("ESFJ", ESFJ.createFactory());
            put("ESFP", ESFP.createFactory());
            put("ESTJ", ESTJ.createFactory());
            put("ESTP", ESTP.createFactory());
            put("INFJ", INFJ.createFactory());
            put("INFP", INFP.createFactory());
            put("INTJ", INTJ.createFactory());
            put("INTP", INTP.createFactory());
            put("ISFJ", ISFJ.createFactory());
            put("ISFP", ISFP.createFactory());
            put("ISTJ", ISTJ.createFactory());
            put("ISTP", ISTP.createFactory());
        }};
    }

    public static AbstractTypeFactory<Type> getTypeFactory(String mbtiType) {
        if (!factoryMap.containsKey(mbtiType))
            throw new IllegalArgumentException("Invalid Mbti Type: " + mbtiType);
        return factoryMap.get(mbtiType);
    }
}
