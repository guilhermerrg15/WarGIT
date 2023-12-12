package Model;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

public class MapTest {

    private Map map;
    
    @Before
    public void setUp() {
        map = Map.getMap();
    }

    @Test
    public void testGetMapInstance() {
        assertNotNull(map);
        Map secondMap = Map.getMap();
        assertEquals("Should return the same instance", map, secondMap);
    }

    @Test
    public void testFindTerritory() {
        Territory foundTerritory = map.findTerritory("Brasil");
        assertNotNull("Brasil should be found", foundTerritory);

        Territory notFoundTerritory = map.findTerritory("Random Territory");
        assertNull("Random territory not found", notFoundTerritory);
    }

    @Test
    public void testGetNumberTerritories() {
        int expectedTotalTerritories = 51;
        assertEquals(expectedTotalTerritories, map.getNumberTerritories());
    }

    @Test
    public void testFindContinent() {
        Continent foundContinent = map.findContinent("South America");
        assertNotNull("South America should be found", foundContinent);

        Continent notFoundContinent = map.findContinent("Nonexistent");
        assertNull("Nonexistent continent should not be found", notFoundContinent);
    }

    @Test
    public void testGetTerritoriesList() {
        ArrayList<Territory> territoriesList = map.getTerritoriesList();
        assertNotNull("Territories list should not be null", territoriesList);

    }

}
