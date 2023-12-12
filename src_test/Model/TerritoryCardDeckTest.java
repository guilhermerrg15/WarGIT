package Model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class TerritoryCardDeckTest {

    private TerritoryCardDeck cardDeck;

    @Before
    public void setUp() {
        cardDeck = new TerritoryCardDeck();
    }

    @Test
    public void testShuffleCards() {
        List<TerritoryCard> initialOrder = cardDeck.getCards();

        cardDeck.shuffleCards();

        List<TerritoryCard> shuffledOrder = cardDeck.getCards();
        assertFalse(initialOrder.equals(shuffledOrder));
    }

    @Test
    public void testPickRandomCard() {
        Player player = new Player("Player1", PlayerColor.VERMELHO, 1);

        cardDeck.pickRandomCard(player);

        assertEquals(1, player.getCard().size());
        
        assertEquals(52, cardDeck.getCards().size()); 
    }

    @Test
    public void testReturnCard() {

        TerritoryCard pickedCard = cardDeck.getCards().get(0);
        
        cardDeck.returnCard(pickedCard);

        assertTrue(cardDeck.getCards().contains(pickedCard));
    }

}