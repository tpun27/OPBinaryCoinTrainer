import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ApplicationTest {
    @Test
    void populateSublistsTest() {
        Application.populateData();

        assertTrue(Application.getSingleDeciders().size() >= 59);
        assertTrue(Application.getSingleObservers().size() >= 56);
        assertTrue(Application.getConsumeOrBlastFirstList().size() >= 78);
        assertTrue(Application.getSleepOrPlayFirstList().size() >= 37);
        assertTrue(Application.getExtrovertedDeciderSplitList().size() == 4);
        assertTrue(Application.getSensingSplitList().size() == 4);

        assertTrue(Application.getExtrovertedDeciderSplitList().get(0).size() >= 30);
        assertTrue(Application.getExtrovertedDeciderSplitList().get(1).size() >= 24);
        assertTrue(Application.getExtrovertedDeciderSplitList().get(2).size() >= 32);
        assertTrue(Application.getExtrovertedDeciderSplitList().get(3).size() >= 30);
        assertTrue(Application.getSensingSplitList().get(0).size() >= 25);
        assertTrue(Application.getSensingSplitList().get(1).size() >= 34);
        assertTrue(Application.getSensingSplitList().get(2).size() >= 26);
        assertTrue(Application.getSensingSplitList().get(3).size() >= 31);

        Type intp = new Type("Bill Gates", "INTP", "FF-Ti/Ne-CS/P(B)");
        assertEquals(3, TypeParser.getExtrovertedDeciderIndex(intp));
        assertEquals(2, TypeParser.getSensingIndex(intp));

        Type isfj = new Type("Hannah Hart", "ISFJ", "MM-Si/Fe-BP/C(S)");
        assertEquals(1, TypeParser.getExtrovertedDeciderIndex(isfj));
        assertEquals(0, TypeParser.getSensingIndex(isfj));
    }

    @Test
    void shuffleTwoListsThenCombineIntoNewListTest() {
        Application.populateData();

        List<Type> singleDeciders = Application.getSingleDeciders();
        List<Type> singleObservers = Application.getSingleObservers();

        List<Type> gameList = Application.shuffleTwoListsThenCombineIntoNewList(singleDeciders, singleObservers);

        int index = 0;

        for (Type type : singleDeciders) {
            assertEquals(type, gameList.get(index));
            index++;
        }

        for (Type type : singleObservers) {
            assertEquals(type, gameList.get(index));
            index++;
        }
    }

    @Test
    void shuffleFourListsThenCombineIntoNewListTest() {
        Application.populateData();

        List<Type> sensors1 = Application.getSensingSplitList().get(0);
        List<Type> sensors2 = Application.getSensingSplitList().get(1);
        List<Type> sensors3 = Application.getSensingSplitList().get(2);
        List<Type> sensors4 = Application.getSensingSplitList().get(2);

        List<Type> gameList = Application.shuffleFourListsThenCombineIntoNewList(
                sensors1,
                sensors2,
                sensors3,
                sensors4);

        int index = 0;

        for (Type type : sensors1) {
            assertEquals(type, gameList.get(index));
            index++;
        }

        for (Type type : sensors2) {
            assertEquals(type, gameList.get(index));
            index++;
        }

        for (Type type : sensors3) {
            assertEquals(type, gameList.get(index));
            index++;
        }

        for (Type type : sensors4) {
            assertEquals(type, gameList.get(index));
            index++;
        }
    }

    @Test
    void generateAllTypesListAlphabeticalTest() {
        Application.populateData();

        assertEquals('A', Application.getAllTypesListAlphabetical().get(0).getName().charAt(0));
    }
}