package cardgame.tests;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.ArrayList;
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
        new cardgame = CardGame(2, )
    }

    @Test 
    
}
