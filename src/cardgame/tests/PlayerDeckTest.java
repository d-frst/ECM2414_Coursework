package src.cardgame.tests;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import src.cardgame.PlayerDeck;

public class PlayerDeckTest {

    private PlayerDeck playerDeck;

    @Before
    public void setUp() {
        playerDeck = new PlayerDeck();
    }

    @Test
    public void testDeckInstantiation() {
        assertTrue("Player test placeholder", true);
    }
}