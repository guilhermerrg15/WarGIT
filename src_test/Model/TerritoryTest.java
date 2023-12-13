package Model;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

public class TerritoryTest {

    private Territory territory;

    @Before
    public void setUp() {
        List<String> neighbors = new ArrayList<>();
        neighbors.add("Neighbor1");
        neighbors.add("Neighbor2");

        territory = new Territory("Territorio Teste", "Continente Teste", neighbors);
    }

    @Test
    public void testGetName() {
        assertEquals("Territorio Teste", territory.getName());
    }

    @Test
    public void testGetContinent() {
        assertEquals("Continente Teste", territory.getContinent());
    }

    @Test
    public void testSetAndGetOwner() {
        Player owner = new Player("Dono", PlayerColor.VERDE, 1);
        territory.setOwner(owner);
        assertEquals(owner, territory.getOwner());
    }

    @Test
    public void testGetArmies() {
        assertEquals(0, territory.getArmies());
    }

    @Test
    public void testSetAndGetArmies() {
        territory.setArmies(5);
        assertEquals(5, territory.getArmies());
    }

    @Test
    public void testAlterarQndExercitos() {
        assertTrue(territory.changeNumArmies(3));
        assertEquals(3, territory.getArmies());

        assertTrue(territory.changeNumArmies(-2));
        assertEquals(1, territory.getArmies());

        assertFalse(territory.changeNumArmies(-5)); // Trying to subtract more armies than available
        assertEquals(1, territory.getArmies());
    }

    @Test
    public void testRemoveArmies() {
        territory.setArmies(5);
        territory.removeArmies(3);
        assertEquals(2, territory.getArmies());

        territory.removeArmies(5); // Trying to remove more armies than available
        assertEquals(0, territory.getArmies());
    }

    @Test
    public void testGetNeighbours() {
        List<String> neighbors = territory.getNeighbours();
        assertEquals(2, neighbors.size());
        assertTrue(neighbors.contains("Neighbor1"));
        assertTrue(neighbors.contains("Neighbor2"));
    }

    @Test
    public void testIsNeighbor() {
        assertTrue(territory.isNeighbor("Neighbor1"));
        assertTrue(territory.isNeighbor("Neighbor2"));
        assertFalse(territory.isNeighbor("NonExistentNeighbor"));
    }

    @Test
    public void testGetCor() {
        Player owner = new Player("Owner", PlayerColor.VERDE, 1);
        territory.setOwner(owner);
        assertEquals(PlayerColor.VERDE, territory.getCor());
    }

}