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
    }
}