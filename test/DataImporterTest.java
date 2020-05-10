import org.junit.jupiter.api.Test;
import types.Type;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class DataImporterTest {

    String[] mbtiTypes = new String[] {
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

    @Test
    void readTypeDataTest() {
        DataImporter dataImporter = new DataImporter();

        Map<String, List<Type>> typeMap = dataImporter.readTypeData();
        assertEquals(16, typeMap.size());

        for (String mbtiType : mbtiTypes) {
            assertEquals(true, typeMap.get(mbtiType).size() > 0);
        }
    }

}