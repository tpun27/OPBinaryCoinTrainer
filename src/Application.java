import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Application {
    private static Map<String, Type> allTypesMap;
    private static List<Type> singleDeciders;
    private static List<Type> singleObservers;
    private static List<Type> consumeOrBlastFirstList;
    private static List<Type> sleepOrPlayFirstList;

    public static void main(String[] args) {
        populateData();
    }

    public static void populateData() {
        allTypesMap = DataImporter.readTypeData();

        singleDeciders = new ArrayList<>();
        singleObservers = new ArrayList<>();
        consumeOrBlastFirstList = new ArrayList<>();
        sleepOrPlayFirstList = new ArrayList<>();

        for (Type type : allTypesMap.values()) {
            char dominantLetter = type.getSaviorFunctions()[0].charAt(0);
            if (TypeParser.isTorF(dominantLetter))
                singleDeciders.add(type);
            else
                singleObservers.add(type);

            char dominantAnimal = type.getSaviorAnimals()[0];
            if (TypeParser.isBorC(dominantAnimal))
                consumeOrBlastFirstList.add(type);
            else
                sleepOrPlayFirstList.add(type);
        }
    }

    public static Map<String, Type> getAllTypesMap() {
        return allTypesMap;
    }

    public static List<Type> getSingleDeciders() {
        return singleDeciders;
    }

    public static List<Type> getSingleObservers() {
        return singleObservers;
    }

    public static List<Type> getConsumeOrBlastFirstList() {
        return consumeOrBlastFirstList;
    }

    public static List<Type> getSleepOrPlayFirstList() {
        return sleepOrPlayFirstList;
    }
}
