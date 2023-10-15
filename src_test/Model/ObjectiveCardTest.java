package Model;

import static org.junit.Assert.*;
import org.junit.Test;

public class ObjectiveCardTest {

    @Test
    public void testObjectiveCardInitialization() {
        ObjectiveCard objectiveCard = new ObjectiveCard("Conquistar 24 territórios a sua escolha", "Território Alvo");

        assertEquals("Conquistar 24 territórios a sua escolha", objectiveCard.getDescription());
        assertFalse(objectiveCard.isCompleted());
        assertEquals("Território Alvo", objectiveCard.getTarget());
    }

    @Test
    public void testSetCompleted() {
        ObjectiveCard objectiveCard = new ObjectiveCard("Conquistar na totalidade a Asia e a Africa", "Território alvo");

        assertFalse(objectiveCard.isCompleted());

        objectiveCard.setCompleted(true);

        assertTrue(objectiveCard.isCompleted());
    }
}

