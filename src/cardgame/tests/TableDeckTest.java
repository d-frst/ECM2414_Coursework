package cardgame.tests;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import cardgame.TableDeck;
import cardgame.Card;

public class TableDeckTest {

    private TableDeck tableDeck;
    private Card card;

    @Before
    public void setUp() throws Exception{
        tableDeck = new TableDeck(1);
        card = new Card(2);
        tableDeck.discardCard(card);
    }

    @Test
    public void testDeckInstantiation() {
        assertEquals("2", tableDeck.getCards());
        assertEquals(1, tableDeck.getDeckNumber());
    }

    @Test
    public void testDrawCard() {
        tableDeck.drawCard();
        assertEquals("", tableDeck.getCards());
    }
}
