package Model;

import static org.junit.Assert.*;
import org.junit.Test;

public class TerritoryCardTest {

    @Test
    public void testTerritoryCardInitialization() {
        String name = "Territorio X";
//        Shape shape = Shape.Square;
//        TerritoryCard territoryCard = new TerritoryCard(name, shape);
        TerritoryCard territoryCard = new TerritoryCard(name);

        assertEquals(name, territoryCard.getName());
//        assertEquals(shape, territoryCard.getShape());
    }

    @Test
    public void testTerritoryCardToString() {
        String name = "Territorio Y";
//        Shape shape = Shape.Square;
        TerritoryCard territoryCard = new TerritoryCard(name);

//        String expectedString = name + " (" + shape + ")";
        String expectedString = name ;
        assertEquals(expectedString, territoryCard.toString());
    }

}


