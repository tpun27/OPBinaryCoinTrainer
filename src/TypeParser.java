import java.util.HashMap;
import java.util.Map;

public class TypeParser {
    static Map<String, String> functionMap;
    static String[] extravertedDeciderFunctions;
    static String[] sensingFunctions;

    static {
        functionMap = new HashMap<String, String>() {{
            put("Ti", "Fe");
            put("Te", "Fi");
            put("Ni", "Se");
            put("Ne", "Si");
            put("Si", "Ne");
            put("Se", "Ni");
            put("Fi", "Te");
            put("Fe", "Ti");
        }};
    }

    static {
        extravertedDeciderFunctions = new String[] {
                "Te",
                "Fe"
        };
    }

    static {
        sensingFunctions = new String[] {
                "Si",
                "Se"
        };
    }

    // Type will be '-' delimited
    // e.g. FF-Ti/Ne-CS/P(B)
    public static void parseRawType(Type type) {
        String[] typeSplit = type.getType512String().split("-");

        // Parse functions
        String functionsRaw = typeSplit[1];
        String[] functionsRawArray = functionsRaw.split("/");
        String[] saviorFunctions = type.getSaviorFunctions();
        String[] demonFunctions = type.getDemonFunctions();
        // Logic is slightly complicated for the sake of reusing the Strings in functionMap
        demonFunctions[0] = functionMap.get(functionsRawArray[1]);
        demonFunctions[1] = functionMap.get(functionsRawArray[0]);
        saviorFunctions[0] = functionMap.get(demonFunctions[1]);
        saviorFunctions[1] = functionMap.get(demonFunctions[0]);

        // Parse animals
        String animalsRaw = typeSplit[2];
        char[] saviorAnimals = type.getSaviorAnimals();
        char[] demonAnimals = type.getDemonAnimals();
        saviorAnimals[0] = animalsRaw.charAt(0);
        saviorAnimals[1] = animalsRaw.charAt(1);
        demonAnimals[0] = animalsRaw.charAt(3);
        demonAnimals[1] = animalsRaw.charAt(5);

        String gendersRaw = typeSplit[0];
        char sensoryGender = gendersRaw.charAt(0);
        char extrovertedDeciderGender = gendersRaw.charAt(1);

        if (isM(sensoryGender))
            type.setMasculineSensing(true);
        if (isM(extrovertedDeciderGender))
            type.setMasculineExtrovertedDecider(true);
    }

    public static void deriveAndSetCoins(Type type) {
        setSingleDeciderCoin(type);
        setFunctionCoins(type);
        setAnimalCoins(type);
    }

    public static void setSingleDeciderCoin(Type type) {
        // Get first letter of first function
        char leadLetter = type.getSaviorFunctions()[0].charAt(0);

        if (isTorF(leadLetter))
            type.setSingleDecider(true);
    }

    public static void setFunctionCoins(Type type) {
        String[] saviorFunctions = type.getSaviorFunctions();

        for (String function : saviorFunctions) {
            char leadLetter = function.charAt(0);
            char secondLetter = function.charAt(1);

            if (isTorF(leadLetter)) {
                if (isLowercaseI(secondLetter))
                    type.setSelfAboveTribe(true);
                if (leadLetter == 'T')
                    type.setReasonAboveValue(true);
            }

            if (isNorS(leadLetter)) {
                if (isLowercaseI(secondLetter))
                    type.setOrganizeAboveGather(true);
                if (leadLetter == 'N')
                    type.setAbstractOverPhysical(true);
            }
        }
    }

    public static void setAnimalCoins(Type type) {
        char[] saviorAnimals = type.getSaviorAnimals();

        for (char saviorAnimal : saviorAnimals) {
            if (saviorAnimal == 'C')
                type.setConsumeOverBlast(true);
            if (saviorAnimal == 'S')
                type.setSleepOverPlay(true);
        }
    }

    public static boolean isExtrovertedDeciderFunction(String f) {
        for (String function : extravertedDeciderFunctions) {
            if (function.equals(f))
                return true;
        }
        return false;
    }

    public static boolean isSensingFunction(String f) {
        for (String function : sensingFunctions) {
            if (function.equals(f))
                return true;
        }
        return false;
    }

    public static int getExtrovertedDeciderIndex(Type type) {
        int index = 0;

        for (String function : type.getSaviorFunctions()) {
            if (isExtrovertedDeciderFunction(function))
                return index;
            index++;
        }

        for (String function : type.getDemonFunctions()) {
            if (isExtrovertedDeciderFunction(function))
                return index;
            index++;
        }
        return -1;
    }

    public static int getSensingIndex(Type type) {
        int index = 0;

        for (String function : type.getSaviorFunctions()) {
            if (isSensingFunction(function))
                return index;
            index++;
        }

        for (String function : type.getDemonFunctions()) {
            if (isSensingFunction(function))
                return index;
            index++;
        }
        return -1;
    }

    public static boolean isTorF(char c) {
        if (c == 'T' || c == 'F')
                return true;
        return false;
    }

    public static boolean isNorS(char c) {
        if (c == 'N' || c == 'S')
            return true;
        return false;
    }

    public static boolean isLowercaseI(char c) {
        if (c == 'i')
            return true;
        return false;
    }

    public static boolean isM(char c) {
        if (c == 'M')
            return true;
        return false;
    }

    public static boolean isBorC(char c) {
        if ((c == 'B') || (c == 'C'))
            return true;
        return false;
    }
}