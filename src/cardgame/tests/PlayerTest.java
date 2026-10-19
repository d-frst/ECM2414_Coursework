package cardgame.tests;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import cardgame.Player;
import cardgame.Card;

public class PlayerTest {

    private Player player;
    private Card card;

    @Before
    public void setUp() throws Exception{
        player = new Player(1, new AtomicBoolean(false), new AtomicInteger(0), null, null);
        card = new Card(2);
        player.addToPlayerDeck(card);
        // would be nice to set up a mock draw deck and discard deck here
    }

    @Test
    public void testPlayerInstantiation() {
        assertTrue("Player test placeholder", true);
        assertEquals(1, player.getPlayerNumber());
    }
}