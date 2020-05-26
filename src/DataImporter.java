import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;

public class DataImporter {
//    private static String TYPE_DATA_FILE_NAME = "TypeData.csv";
    private static String TYPE_DATA_FILE_NAME = "TypeDataTest.csv";
    private static String COMMA_DELIMITER = ",";

    public static Map<String, Type> readTypeData() {
        Map<String, Type> allTypesMap = new HashMap<>();

        try (BufferedReader br = new BufferedReader(new FileReader(TYPE_DATA_FILE_NAME))) {
            String line;
            Type type;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(COMMA_DELIMITER);

                type = new Type(values[1], values[0], values[2]);
                allTypesMap.put(values[1], type);
            }
            return allTypesMap;
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return new HashMap<>();
    }
}