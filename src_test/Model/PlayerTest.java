package Model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

public class PlayerTest {

    private Player player;

    @Before
    public void setUp() {
        player = new Player("Player1", PlayerColor.VERMELHO, 1);
    }

    @Test
    public void testConstructor() {
        assertEquals("Player1", player.getName());
        assertEquals(PlayerColor.VERMELHO, player.getColor());
        assertEquals(1, player.getIndex());
        assertEquals(0, player.getArmies());
        assertNotNull(player.getConqueredTerritories());
        assertNotNull(player.getCard());
        assertEquals(0, player.getTerritoryNumber());
        assertNull(player.getObjective());
    }

    @Test
    public void testConquer24TerritoriesObjective() {
        Conquer24TerritoriesObjectiveCard objectiveCard = new Conquer24TerritoriesObjectiveCard("Conquer24");

        player.receiveObjective(objectiveCard);

        // Initially, the player should not fulfill the objective
        assertFalse(objectiveCard.checkStatus());

        // Add 24 territories to the player
        for (int i = 0; i < 24; i++) {
            player.addTerritorio(new Territory("Territory" + i, "Continent1", new ArrayList<>()));
        }

        // Now, the player should fulfill the objective
        assertTrue(objectiveCard.checkStatus());
    }

    @Test
    public void testAddTerritorio() {
        Territory territory = new Territory("Territory A", "Continent A", new ArrayList<>());
        player.addTerritorio(territory);

        assertEquals(1, player.getTerritoryNumber());
        assertTrue(player.getConqueredTerritories().contains(territory));
    }

    @Test
    public void testLoseTerritory() {
        Territory territory = new Territory("Territory B", "Continent B", new ArrayList<>());
        player.addTerritorio(territory);

        player.loseTerritory(territory);

        assertEquals(0, player.getTerritoryNumber());
        assertFalse(player.getConqueredTerritories().contains(territory));
    }

    @Test
    public void testAddCard() {
        assertTrue(player.addCard(new TerritoryCard("Card1", Shape.Circle, "Continent", "ImageName")));
        assertEquals(1, player.getCard().size());

        // Attempt to add more than 5 cards
        for (int i = 0; i < 5; i++) {
            player.addCard(new TerritoryCard("Card" + (i + 2), Shape.Triangle, "Continent", "ImageName"));
        }

        assertFalse(player.addCard(new TerritoryCard("CardX", Shape.Square, "Continent", "ImageName")));
        assertEquals(5, player.getCard().size());
    }

    @Test
    public void testGetCards() {
        for (int i = 0; i < 5; i++) {
            player.addCard(new TerritoryCard("Card" + (i + 1), Shape.Circle, "Continent", "ImageName"));
        }
        assertEquals(5, player.getCard().size());
    }

}
