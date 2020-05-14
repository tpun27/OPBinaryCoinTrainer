import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Application {
    private static Map<String, Type> allTypesMap;
    private static List<Type> singleDeciders;
    private static List<Type> singleObservers;
    private static List<Type> consumeOrBlastFirstList;
    private static List<Type> sleepOrPlayFirstList;
    // these lists split De and Sensing
    private static List<List<Type>> extrovertedDeciderSplitList;
    private static List<List<Type>> sensingSplitList;

    private static int NUMBER_OF_GROUPS = 4;

    public static void main(String[] args) {
        populateData();

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                MainGameScreen.createAndShowGUI();
            }
        });
    }

    public static void populateData() {
        allTypesMap = DataImporter.readTypeData();

        singleDeciders = new ArrayList<>();
        singleObservers = new ArrayList<>();
        consumeOrBlastFirstList = new ArrayList<>();
        sleepOrPlayFirstList = new ArrayList<>();

        extrovertedDeciderSplitList = new ArrayList<>(NUMBER_OF_GROUPS);
        sensingSplitList = new ArrayList<>(NUMBER_OF_GROUPS);
        for (int i = 0; i < NUMBER_OF_GROUPS; i++) {
            extrovertedDeciderSplitList.add(new ArrayList<Type>());
            sensingSplitList.add(new ArrayList<Type>());
        }

        for (Type type : allTypesMap.values()) {
            char dominantLetter = type.getSaviorFunctions()[0].charAt(0);

            // populate decider/observer lists
            if (TypeParser.isTorF(dominantLetter))
                singleDeciders.add(type);
            else
                singleObservers.add(type);

            // populate Animal lists
            char dominantAnimal = type.getSaviorAnimals()[0];
            if (TypeParser.isBorC(dominantAnimal))
                consumeOrBlastFirstList.add(type);
            else
                sleepOrPlayFirstList.add(type);

            // populate Gender lists
            extrovertedDeciderSplitList.get(TypeParser.getExtrovertedDeciderIndex(type)).add(type);
            sensingSplitList.get(TypeParser.getSensingIndex(type)).add(type);
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

    public static List<List<Type>> getExtrovertedDeciderSplitList() {
        return extrovertedDeciderSplitList;
    }

    public static List<List<Type>> getSensingSplitList() {
        return sensingSplitList;
    }
}