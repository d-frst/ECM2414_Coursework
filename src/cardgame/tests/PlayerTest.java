package src.cardgame.tests;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import src.cardgame.Player;

public class PlayerTest {

    private Player player;

    @Before
    public void setUp() throws Exception{
        player = new Player(1, new AtomicBoolean(false), new AtomicInteger(0), null, null);
    }

    @Test
    public void testPlayerInstantiation() {
        assertTrue("Player test placeholder", true);
        assertEquals(1, player.getPlayerNumber());
    }

}