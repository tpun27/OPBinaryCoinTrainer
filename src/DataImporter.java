import factory.AbstractTypeFactory;
import factory.TypeFactoryFactory;
import types.Type;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;

public class DataImporter {
    private final String TYPE_DATA_FILE_NAME = "TypeData.csv";
    private final String COMMA_DELIMITER = ",";

    public final String[] MBTI_TYPES = new String[] {
            "ISFP",
            "INFP",
            "ISTP",
            "INTP",
            "ISFJ",
            "INFJ",
            "ISTJ",
            "INTJ",
            "ESFP",
            "ENFP",
            "ESTP",
            "ENTP",
            "ESFJ",
            "ENFJ",
            "ESTJ",
            "ENTJ"
    };

    public Map<String, List<Type>> readTypeData() {
        Map<String, List<Type>> allTypesMap = new HashMap<>();

        try (BufferedReader br = new BufferedReader(new FileReader(TYPE_DATA_FILE_NAME))) {
            String line;
            AbstractTypeFactory<Type> factory;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(COMMA_DELIMITER);
                String mbtiType = values[0];

                factory = TypeFactoryFactory.getTypeFactory(mbtiType);

                if (allTypesMap.containsKey(mbtiType))
                    allTypesMap.get(mbtiType).add(factory.create(values[1], values[2]));
                else {
                    List<Type> typeArray = new ArrayList<>();
                    typeArray.add(factory.create(values[1], values[2]));
                    allTypesMap.put(mbtiType, typeArray);
                }
            }
            return allTypesMap;
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return new HashMap<>();
    }
}
