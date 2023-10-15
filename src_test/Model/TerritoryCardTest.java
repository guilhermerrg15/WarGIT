package Model;

import static org.junit.Assert.*;
import org.junit.Test;

public class TerritoryCardTest {

    @Test
    public void testTerritoryCardInitialization() {
        TerritoryCard territoryCard = new TerritoryCard("Brazil", "Triangle");

        assertEquals("Brazil", territoryCard.getName());
        assertEquals("Triangle", territoryCard.getGeometricShape());
    }

    @Test
    public void testToString() {
        TerritoryCard territoryCard = new TerritoryCard("Canada", "Square");

        assertEquals("Canada (Square)", territoryCard.toString());
    }
}

