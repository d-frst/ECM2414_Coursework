package tests.java;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import main.java.Card;

public class CardTest {
    private Card card;

    @Before
    public void setUp() {
        // Test you can create a card
        card = new Card(2);
    }

    @Test 
    public void testGetValue() {
        assertEquals(2, card.getValue());
    }
}
