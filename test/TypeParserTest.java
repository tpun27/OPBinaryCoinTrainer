import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TypeParserTest {
    @Test
    void parseRawTypeTest() {
        Type intp = new Type("Bill Gates", "INTP", "FF-Ti/Ne-CS/P(B)");
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

    @Test
    void deriveAndSetCoinsTest() {
        Type intp = new Type("Bill Gates", "INTP", "FF-Ti/Ne-CS/P(B)");
        TypeParser.parseRawType(intp);
        TypeParser.deriveAndSetCoins(intp);

        assertEquals(intp.isSingleDecider(), true);
        assertEquals(intp.isSelfAboveTribe(), true);
        assertEquals(intp.isOrganizeAboveGather(), false);
        assertEquals(intp.isReasonAboveValue(), true);
        assertEquals(intp.isAbstractOverPhysical(), true);
        assertEquals(intp.isConsumeOverBlast(), true);
        assertEquals(intp.isSleepOverPlay(), true);
        assertEquals(intp.isMasculineSensing(), false);
        assertEquals(intp.isMasculineExtrovertedDecider(), false);

        Type isfj = new Type("Hannah Hart", "ISFJ", "MM-Si/Fe-BP/C(S)");
        TypeParser.parseRawType(isfj);
        TypeParser.deriveAndSetCoins(isfj);

        assertEquals(isfj.isSingleDecider(), false);
        assertEquals(isfj.isSelfAboveTribe(), false);
        assertEquals(isfj.isOrganizeAboveGather(), true);
        assertEquals(isfj.isReasonAboveValue(), false);
        assertEquals(isfj.isAbstractOverPhysical(), false);
        assertEquals(isfj.isConsumeOverBlast(), false);
        assertEquals(isfj.isSleepOverPlay(), false);
        assertEquals(isfj.isMasculineSensing(), true);
        assertEquals(isfj.isMasculineExtrovertedDecider(), true);
    }
}