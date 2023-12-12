package Model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


import org.junit.Before;
import org.junit.Test;

public class GameTest {

    private Game game;
    private Player player1;
    private Player player2;
    private Player player3;

    @Before
    public void setUp() {
        game = Game.getInstance();
        player1 = new Player("Player1", PlayerColor.VERMELHO, 1);
        player2 = new Player("Player2", PlayerColor.AZUL, 2);
        player3 = new Player("Player 3", PlayerColor.VERDE, 3);
    }

    @Test
    public void testAddPlayer() {
        assertTrue(game.addPlayer(player1));
        assertTrue(game.addPlayer(player2));
        assertTrue(game.addPlayer(player3));
        assertFalse(game.addPlayer(player1));
        assertFalse(game.addPlayer(player2));
        assertFalse(game.addPlayer(player3));
    }

    @Test
    public void testInitiateGame() {
        assertTrue(game.addPlayer(player1));
        assertTrue(game.addPlayer(player2));
        assertTrue(game.addPlayer(player3));

        assertTrue(game.initiateGame());

        assertEquals(17, player1.getTerritoryNumber());
        assertEquals(17, player2.getTerritoryNumber());
        assertEquals(17, player3.getTerritoryNumber());
    }
}
