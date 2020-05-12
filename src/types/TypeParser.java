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
    }

    public static void deriveCoins(Type mbtiType) {

    }
}
