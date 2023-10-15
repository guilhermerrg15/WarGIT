package Model;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class TerritoryTest {
    private Territory territory;

    @Before
    public void setUp() {
        territory = new Territory("Territorio X");
    }

    @Test
    public void testAddArmies() {
        territory.addArmies(5);
        assertEquals(5, territory.getArmies());
    }

    @Test
    public void testRemoveArmies() {
        territory.addArmies(10);
        territory.removeArmies(3);
        assertEquals(7, territory.getArmies());
    }

    @Test
    public void testSetOwner() {
        Player player = new Player("Guilherme", PlayerColor.RED);
        territory.setOwner(player);
        assertEquals(player, territory.getOwner());
    }

    @Test
    public void testAddNeighbour() {
        Territory neighbour = new Territory("Territorio Y");
        territory.addNeighbour(neighbour);
        assertTrue(territory.getNeighbours().contains(neighbour));
    }
}

