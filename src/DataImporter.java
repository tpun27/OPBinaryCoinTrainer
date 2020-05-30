import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

public class DataImporter {
//    private static String TYPE_DATA_FILE_NAME = "./resources/TypeData.csv";
private static String TYPE_DATA_FILE_NAME = "/TypeData.csv";
    private static String COMMA_DELIMITER = ",";

    public static Map<String, Type> readTypeData() {
        Map<String, Type> allTypesMap = new HashMap<>();

        try (InputStream inputStream = DataImporter.class.getResourceAsStream(TYPE_DATA_FILE_NAME);
             BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
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