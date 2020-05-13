import javax.swing.*;
import java.awt.*;
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

    private static String[] GAME_BUTTON_NAMES = new String [] {
            "1) Double Decider vs. Double Observer",
            "2) Self above Tribe vs. Tribe above Self",
            "3) Reasons over Values vs. Values over Reasons",
            "4) Abstract over Physical vs. Physical over Abstract",
            "5) Consume over Blast vs. Blast over Consume",
            "6) Sleep over Play vs. Play over Sleep",
            "7) Masculine Sensing vs. Feminine Sensing",
            "8) Masculine Extroverted Decider vs. Feminine Extroverted Decider"
    };

    private static String[] INSTRUCTIONS = new String [] {
            "Welcome to the OP Binary Coin trainer!",
            "Please select from our menu options to start training!"
    };

    public static void main(String[] args) {
        populateData();

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                createAndShowGUI();
            }
        });
    }

    public static void createAndShowGUI() {
        JFrame frame = new JFrame("OP Binary Coin Trainer");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 300);

        Container pane = frame.getContentPane();
        pane.setLayout(new BoxLayout(pane, BoxLayout.Y_AXIS));

        for (String instruction : INSTRUCTIONS) {
            JLabel label = new JLabel(instruction, JLabel.CENTER);
            label.setAlignmentX(JLabel.CENTER_ALIGNMENT);
            pane.add(label);
        }

        for (String buttonName : GAME_BUTTON_NAMES) {
            JButton button = new JButton(buttonName);
            button.setAlignmentX(Component.CENTER_ALIGNMENT);
            pane.add(button);
        }

        frame.setVisible(true);
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
