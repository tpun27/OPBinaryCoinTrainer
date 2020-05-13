import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class DataImporterTest {

    @Test
    void readTypeDataTest() {
        DataImporter dataImporter = new DataImporter();

        Map<String, Type> typeMap = dataImporter.readTypeData();
        assertTrue(typeMap.size() >= 115);
    }

}