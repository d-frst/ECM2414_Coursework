package cardgame.tests;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import cardgame.PlayerDeck;
import cardgame.Card;

public class PlayerDeckTest {

    private PlayerDeck playerDeck;
    private Card card;

    @Before
    public void setUp() {
        playerDeck = new PlayerDeck();
        card = new Card(2);
        playerDeck.addCard(card);
    }

    @Test
    public void testDeckInstantiation() {
        assertEquals("2 ", playerDeck.printCards());
    }
}