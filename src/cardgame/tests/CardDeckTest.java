package src.cardgame.tests;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import src.cardgame.CardDeck;
import src.cardgame.Card;

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
        assertEquals("2 ", cardDeck.printCards());
    }
}