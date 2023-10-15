package Model;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;


public class PlayerTest {
    private Player player;

    @Before
    public void setUp() {
        player = new Player("Guilherme", PlayerColor.RED);
    }

    @Test
    public void testAddArmies() {
        player.addArmies(5);
        assertEquals(5, player.getArmies());
    }

    @Test
    public void testRemoveArmies() {
        player.addArmies(10);
        player.removeArmies(3);
        assertEquals(7, player.getArmies());
    }

    @Test
    public void testAddTerritory() {
        Territory territory = new Territory("Território X");
        player.addTerritory(territory);
        assertTrue(player.getTerritories().contains(territory));
    }

    @Test
    public void testRemoveTerritory() {
        Territory territory = new Territory("Territory Y");
        player.addTerritory(territory);
        player.removeTerritory(territory);
        assertFalse(player.getTerritories().contains(territory));
    }
}
