package Model;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class ContinentTest {

    private Continent continent;
    private List<Territory> territories;

    @Before
    public void setUp() {
        // Set up a sample continent and territories
        territories = new ArrayList<>();
        territories.add(new Territory("Territory1", "Continent1", new ArrayList<>()));
        territories.add(new Territory("Territory2", "Continent1", new ArrayList<>()));
        territories.add(new Territory("Territory3", "Continent1", new ArrayList<>()));

        continent = new Continent("Continent1", 3, territories);
    }

    @Test
    public void testGetName() {
        assertEquals("Continent1", continent.getName());
    }

    @Test
    public void testGetTerritories() {
        assertEquals(territories, continent.getTerritories());
    }

    @Test
    public void testGetBonusArmies() {
        assertEquals(3, continent.getBonusArmies());
    }

    @Test
    public void testGetColor() {
        // Assuming you have a getColor method in the Continent class
        // Update this based on your actual implementation
        // assertEquals(expectedColor, continent.getColor());
    }

    @Test
    public void testAddTerritory() {
        Territory newTerritory = new Territory("NewTerritory", "Continent1", new ArrayList<>());
        continent.addTerritory(newTerritory);

        assertTrue(continent.getTerritories().contains(newTerritory));
    }

    @Test
    public void testGetTerritory() {
        Territory territory = continent.getTerritory("Territory2");

        assertEquals("Territory2", territory.getName());
    }

    @Test
    public void testGetNumberTerritories() {
        assertEquals(3, continent.getNumberTerritories());
    }

    @Test
    public void testCheckContinentDomain() {
        // Assuming you have a Player class with a getColor method
        // Update this based on your actual implementation
        Player player = new Player("Player1", PlayerColor.VERMELHO, 1);

        for (Territory territory : territories) {
            territory.setOwner(player);
        }

        assertTrue(continent.checkContinentDomain(player));
        
        // Change ownership of one territory
        territories.get(0).setOwner(new Player("Player2", PlayerColor.AZUL, 2));

        assertFalse(continent.checkContinentDomain(player));
    }
}