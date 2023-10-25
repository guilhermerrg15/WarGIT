package Model;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class MatchTest {

    private Match match;
    private List<Player> players;
    private Map map;

    @Before
    public void setUp() {
        // Initialize test data
        map = Map.mapGenerator(); // Create a map object using the provided mapGenerator
        players = new ArrayList<>();

        // Create some players
        Player player1 = new Player("Player 1", PlayerColor.RED);
        Player player2 = new Player("Player 2", PlayerColor.BLUE);
        players.add(player1);
        players.add(player2);

        // Create the match
        match = new Match(players, map);
    }

    @Test
    public void testHasStarted() {
        assertFalse(match.hasStarted());
        match.startMatch();
        assertTrue(match.hasStarted());
    }

    @Test
    public void testGetPlayers() {
        List<Player> matchPlayers = match.getPlayers();
        assertEquals(players, matchPlayers);
        assertNotSame(players, matchPlayers);
    }

    @Test
    public void testGiveRandomTerritoriesToPlayers() {
        match.startMatch();
        for (Player player : players) {
            assertTrue(player.getTerritories().size() > 0);
        }
    }

    @Test
public void testDistributeArmiesToPlayers() {
    
}



    @Test
    public void testDistributeContinentalArmiesToPlayers() {
        // Write test cases to verify the distribution of armies to players who own entire continents
        // ...
    }

    @Test
    public void testDoCardsTrade() {
        // Write test cases to verify card trading behavior
        // ...
    }

    @Test
    public void testCalculateTradeBonus() {
        // Write test cases to verify the calculation of trade bonus
        // ...
    }
}



