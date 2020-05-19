import javax.swing.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Application {
    private static Map<String, Type> allTypesMap;
    private static List<Type> allTypesListAlphabetical;
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
                GameScreens.createAndShowGUI();
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

        generateAllTypesListAlphabetical();
    }

    public static void generateAllTypesListAlphabetical() {
        allTypesListAlphabetical = new ArrayList<>(allTypesMap.size());

        List<String> keys = new ArrayList<>(allTypesMap.keySet());
        Collections.sort(keys);

        for (String key : keys) {
            allTypesListAlphabetical.add(allTypesMap.get(key));
        }
    }

    public static List<Type> shuffleTwoListsThenCombineIntoNewList(List<Type> l1, List<Type> l2) {
        Collections.shuffle(l1);
        Collections.shuffle(l2);

        List<Type> combinedList = Stream.of(l1, l2)
                .flatMap(x -> x.stream())
                .collect(Collectors.toList());

        return combinedList;
    }

    public static List<Type> shuffleFourListsThenCombineIntoNewList(
            List<Type> l1, List<Type> l2, List<Type> l3, List<Type> l4) {
        Collections.shuffle(l1);
        Collections.shuffle(l2);
        Collections.shuffle(l3);
        Collections.shuffle(l4);

        List<Type> combinedList = Stream.of(l1, l2, l3, l4)
                .flatMap(x -> x.stream())
                .collect(Collectors.toList());

        return combinedList;
    }

    public static List<Type> generateShuffledAllTypesList() {
        List shuffledList = new ArrayList(allTypesListAlphabetical);
        Collections.shuffle(shuffledList);
        return shuffledList;
    }

    public static List<Type> generateDeciderThenObserverProgressiveList() {
        return shuffleTwoListsThenCombineIntoNewList(singleDeciders, singleObservers);
    }

    public static List<Type> generateObserverThenDeciderProgressiveList() {
        return shuffleTwoListsThenCombineIntoNewList(singleObservers, singleDeciders);
    }

    public static List<Type> generateSensingProgressiveList() {
        return shuffleFourListsThenCombineIntoNewList(
                sensingSplitList.get(0),
                sensingSplitList.get(1),
                sensingSplitList.get(2),
                sensingSplitList.get(3)
        );
    }

    public static List<Type> generateExtrovertedDeciderProgressiveList() {
        return shuffleFourListsThenCombineIntoNewList(
                extrovertedDeciderSplitList.get(0),
                extrovertedDeciderSplitList.get(1),
                extrovertedDeciderSplitList.get(2),
                extrovertedDeciderSplitList.get(3)
        );
    }

    public static Map<String, Type> getAllTypesMap() {
        return allTypesMap;
    }

    public static List<Type> getAllTypesListAlphabetical() {
        return allTypesListAlphabetical;
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