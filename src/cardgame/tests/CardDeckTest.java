package src.cardgame.tests;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import src.cardgame.CardDeck;

public class CardDeckTest {

    private CardDeck cardDeck;

    @Before
    public void setUp() {
        cardDeck = new CardDeck();
    }

    @Test
    public void test() {
        assertTrue("Player test placeholder", true);
    }
}