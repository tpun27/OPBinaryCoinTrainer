import org.junit.jupiter.api.Test;
import types.INTP;
import types.Type;
import types.TypeParser;

import static org.junit.jupiter.api.Assertions.*;

class TypeParserTest {
    @Test
    void parseRawTypeTest() {
        Type intp = new INTP("Bill Gates", "FF-Ti/Ne-CS/P(B)");
        TypeParser.parseRawType(intp);

        String[] saviorFunctions = intp.getSaviorFunctions();
        String[] demonFunctions = intp.getDemonFunctions();

        char[] saviorAnimals = intp.getSaviorAnimals();
        char[] demonAnimals = intp.getDemonAnimals();

        assertEquals(saviorFunctions[0], "Ti");
        assertEquals(saviorFunctions[1], "Ne");
        assertEquals(demonFunctions[0], "Si");
        assertEquals(demonFunctions[1], "Fe");

        assertEquals(saviorAnimals[0], 'C');
        assertEquals(saviorAnimals[1], 'S');
        assertEquals(demonAnimals[0], 'P');
        assertEquals(demonAnimals[1], 'B');
    }

}