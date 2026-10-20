package cardgame.tests;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import cardgame.CardGame;
import cardgame.Card;
import cardgame.Player;
import cardgame.TableDeck;

public class CardGameTest {
    private CardGame cardgame;
    private String deckPath;
    private ArrayList<Thread> playerThreads = new ArrayList<Thread>();
    private ArrayList<TableDeck> tableDecks = new ArrayList<TableDeck>();
    private AtomicBoolean anyPlayerWon = new AtomicBoolean(false);
    private AtomicInteger winningPlayerNumber = new AtomicInteger(0);
    private ArrayList<Lock> tableDeckLocks = new ArrayList<Lock>();

    @Before
    public void setUp() {
        cardgame = new CardGame(2, "testCards1.txt");
    }

    @Test
    public void testGameInstantiation() {
        // Test 2 players have been made
        assertEquals(2, cardgame.getNumPlayers());
        // Test 2 decks have been made
        assertEquals(2, cardgame.getNumTableDecks())
        // Test decks have 4 cards each
        // Fancy code beacuse java doesn't like the list as a parameter
        assertEquals(Arrays.asList(4, 4), cardgame.getTableDeckSizes());
    }
}
