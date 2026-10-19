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
        
    }

    @Test
    public void testDeckInstantiation() {
        assertEquals("2", tableDeck.getCards());
        assertEquals(1, tableDeck.getDeckNumber());
    }
}
