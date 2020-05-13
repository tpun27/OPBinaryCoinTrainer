import org.junit.jupiter.api.Test;

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
        assertEquals(3, Application.getExtrovertedDeciderIndex(intp));
        assertEquals(2, Application.getSensingIndex(intp));

        Type isfj = new Type("Hannah Hart", "ISFJ", "MM-Si/Fe-BP/C(S)");
        assertEquals(1, Application.getExtrovertedDeciderIndex(isfj));
        assertEquals(0, Application.getSensingIndex(isfj));
    }
}