package cardgame.tests;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import cardgame.Card;

public class CardTest {
    private Card card;

    @Before
    public void setUp() {
        card = new Card(2);
    }

    @Test 
    public void testGetValue() {
        assertEquals(2, card.getValue());
    }
}
