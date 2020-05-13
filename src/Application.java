import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

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

        switch (getOption()) {
            case 1:
                System.out.println(1);
                break;
        }
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

            extrovertedDeciderSplitList.get(getExtrovertedDeciderIndex(type)).add(type);
            sensingSplitList.get(getSensingIndex(type)).add(type);
        }
    }

    public static int getExtrovertedDeciderIndex(Type type) {
        int index = 0;

        for (String function : type.getSaviorFunctions()) {
            if (TypeParser.isExtrovertedDeciderFunction(function))
                return index;
            index++;
        }

        for (String function : type.getDemonFunctions()) {
            if (TypeParser.isExtrovertedDeciderFunction(function))
                return index;
            index++;
        }
        return -1;
    }

    public static int getSensingIndex(Type type) {
        int index = 0;

        for (String function : type.getSaviorFunctions()) {
            if (TypeParser.isSensingFunction(function))
                return index;
            index++;
        }

        for (String function : type.getDemonFunctions()) {
            if (TypeParser.isSensingFunction(function))
                return index;
            index++;
        }
        return -1;
    }

    public static int getOption() {
        int optionValue;
        Scanner optionScanner = new Scanner(System.in);

        while (true) {
            printOptions();
            optionValue = Character.getNumericValue(optionScanner.next().charAt(0));
            if ((optionValue >= 1) && (optionValue <= 8))
                break;
        }
        return optionValue;
    }

    public static void printOptions() {
        System.out.println("Welcome to the OP Binary Coin trainer!");
        System.out.println("Please select from our menu options.");
        System.out.println("More specifically, enter a number between 1 and 8:\n");
        System.out.println("1) Double Decider vs. Double Observer");
        System.out.println("2) Self above Tribe vs. Tribe above Self");
        System.out.println("3) Reasons over Values vs. Values over Reasons");
        System.out.println("4) Abstract over Physical vs. Physical over Abstract");
        System.out.println("5) Consume over Blast vs. Blast over Consume");
        System.out.println("6) Sleep over Play vs. Play over Sleep");
        System.out.println("7) Masculine Sensing vs. Feminine Sensing");
        System.out.println("8) Masculine Extroverted Decider vs. Feminine Extroverted Decider");
        System.out.println("");
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
