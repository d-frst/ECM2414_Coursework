package cardgame.tests;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import cardgame.CardDeck;
import cardgame.Card;

public class CardDeckTest {

    private CardDeck cardDeck;
    private Card card;

    @Before
    public void setUp() {
        cardDeck = new CardDeck();
        card = new Card(2);
        cardDeck.addCard(card);
    }

    @Test
    public void testDeckInstantiation() {
        assertEquals(1, cardDeck.getDeckSize());
        assertEquals("2 ", cardDeck.printCards());
    }
}