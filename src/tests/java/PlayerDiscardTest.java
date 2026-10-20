package tests.java;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import main.java.Player;
import main.java.TableDeck;
import main.java.Card;


import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

// Test to check that the player always discards the non-preferred card

public class PlayerDiscardTest {
    private Player player1;
    private TableDeck drawDeck;
    private TableDeck discardDeck;

    @Before
    public void setUp() throws Exception{
        // setup player
        player1 = new Player(1, 
            new AtomicBoolean(false), 
            new AtomicInteger(0), 
            null, 
            null
        );
        // setup discard deck and draw deck
        drawDeck = new TableDeck(1);
        discardDeck = new TableDeck(2);
        player1.setDrawDeck(drawDeck);
        player1.setDiscardDeck(discardDeck);

        // Give the draw deck a card
        drawDeck.discardCard(new Card(1));

    }

    @Test 
    public void testDiscardBehaviour() {
        // Create player's hand
        player1.addToPlayerDeck(new Card(1));
        player1.addToPlayerDeck(new Card(1));
        player1.addToPlayerDeck(new Card(1));
        player1.addToPlayerDeck(new Card(5));

        player1.drawCard();
        int cardDiscarded = player1.discardCard();

        // Check player discarded 5
        assertEquals(5, cardDiscarded);
        // Check player has only 1's in their deck
        assertEquals("1 1 1 1 ", player1.printPlayerDeck());
        // Check discard Pile has card
        assertEquals("5", discardDeck.getCards());
    }
    
}
