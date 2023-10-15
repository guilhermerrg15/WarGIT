package Model;

import static org.junit.Assert.*;
import org.junit.Test;

public class ObjectiveCardTest {

    @Test
    public void testObjectiveCardInitialization() {
        ObjectiveCard objectiveCard = new ObjectiveCard("Conquistar a Europa", "Europe");

        assertEquals("Conquer Europe", objectiveCard.getDescription());
        assertFalse(objectiveCard.isCompleted());
        assertEquals("Europe", objectiveCard.getTarget());
    }

    @Test
    public void testSetCompleted() {
        ObjectiveCard objectiveCard = new ObjectiveCard("Conquistar a Europa", "Europe");

        assertFalse(objectiveCard.isCompleted());

        objectiveCard.setCompleted(true);

        assertTrue(objectiveCard.isCompleted());
    }
}

