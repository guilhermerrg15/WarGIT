package Model;

import static org.junit.Assert.*;
import org.junit.Test;

public class TerritoryCardTest {

    @Test
    public void testTerritoryCardInitialization() {
        int id = 1;
        String name = "Territorio X";
        Shape shape = Shape.Square;
        TerritoryCard territoryCard = new TerritoryCard(id,name, shape);

        assertEquals(name, territoryCard.getName());
        assertEquals(shape, territoryCard.getShape());
    }

    @Test
    public void testTerritoryCardToString() {
        int id = 1;
        String name = "Territorio Y";
        Shape shape = Shape.Square;
        TerritoryCard territoryCard = new TerritoryCard(id,name, shape);

        String expectedString = name + " (" + shape + ")";
        assertEquals(expectedString, territoryCard.toString());
    }

}


