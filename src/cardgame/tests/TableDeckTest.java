package cardgame.tests;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.io.IOException;

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
        // Create a tabledeck and chack you can add a card
        assertEquals("2", tableDeck.getCards());
        assertEquals(1, tableDeck.getDeckNumber());
    }

    // Checks that the tabledecks exhibit first in first out behaviour
    @Test public void testTableDeckIsFifo() throws IOException{
        TableDeck deck = new TableDeck(2);

        // Cards deposited at bottom
        deck.discardCard(new Card(1));
        deck.discardCard(new Card(2));
        deck.discardCard(new Card(3));
        
        // Cards drawn from top
        assertEquals(1, deck.drawCard().getValue());
        assertEquals(2, deck.drawCard().getValue());
        assertEquals(3, deck.drawCard().getValue());

        assertEquals(0, deck.getNumCards());
    }
}
