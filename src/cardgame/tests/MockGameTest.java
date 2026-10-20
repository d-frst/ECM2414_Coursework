package cardgame.tests;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import cardgame.CardGame;
import cardgame.CardDeck;
import cardgame.Card;
import cardgame.Player;
import cardgame.PlayerDeck;
import cardgame.TableDeck;

/*
TODO:
- win at beginning test: with and without preferred denomination
- before each move each player has 4 cards and after they still have 4 cards.
- at the end of the game each player has 4 cards
- check that only one player has won?
*/

public class MockGameTest {
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
}
