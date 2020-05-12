package types;

import java.util.HashMap;
import java.util.Map;

public class TypeParser {
    static Map<String, String> functionMap;

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

    // Type will be '-' delimited
    // e.g. FF-Ti/Ne-CS/P(B)
    public static void parseRawType(Type mbtiType) {
        String[] typeSplit = mbtiType.getType().split("-");

        // Parse functions
        String functionsRaw = typeSplit[1];
        String[] functionsRawArray = functionsRaw.split("/");
        String[] saviorFunctions = mbtiType.getSaviorFunctions();
        String[] demonFunctions = mbtiType.getDemonFunctions();
        // Logic is slightly complicated for the sake of reusing the Strings in functionMap
        demonFunctions[0] = functionMap.get(functionsRawArray[1]);
        demonFunctions[1] = functionMap.get(functionsRawArray[0]);
        saviorFunctions[0] = functionMap.get(demonFunctions[1]);
        saviorFunctions[1] = functionMap.get(demonFunctions[0]);

        // Parse animals
        String animalsRaw = typeSplit[2];
        char[] saviorAnimals = mbtiType.getSaviorAnimals();
        char[] demonAnimals = mbtiType.getDemonAnimals();
        saviorAnimals[0] = animalsRaw.charAt(0);
        saviorAnimals[1] = animalsRaw.charAt(1);
        demonAnimals[0] = animalsRaw.charAt(3);
        demonAnimals[1] = animalsRaw.charAt(5);

        String gendersRaw = typeSplit[0];
        char sensoryGender = gendersRaw.charAt(0);
        char extrovertedDeciderGender = gendersRaw.charAt(1);

        if (isM(sensoryGender))
            mbtiType.setMasculineSensing(true);
        if (isM(extrovertedDeciderGender))
            mbtiType.setMasculineExtrovertedDecider(true);
    }

    public static void deriveAndSetCoins(Type mbtiType) {
        setSingleDeciderCoin(mbtiType);
        setFunctionCoins(mbtiType);
        setAnimalCoins(mbtiType);
    }

    public static void setSingleDeciderCoin(Type mbtiType) {
        // Get first letter of first function
        char leadLetter = mbtiType.getSaviorFunctions()[0].charAt(0);

        if (isTorF(leadLetter))
            mbtiType.setSingleDecider(true);
    }

    public static void setFunctionCoins(Type mbtiType) {
        String[] saviorFunctions = mbtiType.getSaviorFunctions();

        for (String function : saviorFunctions) {
            char leadLetter = function.charAt(0);
            char secondLetter = function.charAt(1);

            if (isTorF(leadLetter)) {
                if (isLowercaseI(secondLetter))
                    mbtiType.setSelfAboveTribe(true);
                if (leadLetter == 'T')
                    mbtiType.setReasonAboveValue(true);
            }

            if (isNorS(leadLetter)) {
                if (isLowercaseI(secondLetter))
                    mbtiType.setOrganizeAboveGather(true);
                if (leadLetter == 'N')
                    mbtiType.setAbstractOverPhysical(true);
            }
        }
    }

    public static void setAnimalCoins(Type mbtiType) {
        char[] saviorAnimals = mbtiType.getSaviorAnimals();

        for (char saviorAnimal : saviorAnimals) {
            if (saviorAnimal == 'C')
                mbtiType.setConsumeOverBlast(true);
            if (saviorAnimal == 'S')
                mbtiType.setSleepOverPlay(true);
        }
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
}
