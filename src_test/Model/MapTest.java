package Model;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class MapTest {
    private Map map;

    @Before
    public void setUp() {
        map = Map.mapGenerator();
    }

    @Test
    public void testFindTerritory() {
        Territory territory = map.findTerritory("Argentina");
        assertNotNull(territory);
        assertEquals("Argentina", territory.getName());
    }

    @Test
    public void testGetNumberTerritories() {
        int numTerritories = map.getNumberTerritories();
        int expectedTerritoryCount = 51;
        assertEquals(expectedTerritoryCount, numTerritories);
    }

    @Test
    public void testFindContinent() {
        Continent continent = map.findContinent("Africa");
        assertNotNull(continent);
        assertEquals("Africa", continent.getName());
    }

}

