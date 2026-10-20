package main.tests;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import cardgame.Player;
import cardgame.Card;
import cardgame.TableDeck;

public class PlayerTest {

    private Player player;
    private Card card1;
    private Card card2;
    private TableDeck drawDeck;
    private TableDeck discardDeck;

    @Before
    public void setUp() throws Exception{
        // setup player
        player = new Player(1, 
            new AtomicBoolean(false), 
            new AtomicInteger(0), 
            null, 
            null
        );
        card1 = new Card(1);
        card2 = new Card(2);

        // Give player a card
        player.addToPlayerDeck(card1);

        // Create a draw deck and discard deck for the player
        drawDeck = new TableDeck(1);
        discardDeck = new TableDeck(2);

        // Set the player's draw and discard deck
        player.setDrawDeck(drawDeck);
        player.setDiscardDeck(discardDeck);
        
        // Put one card in draw deck
        drawDeck.discardCard(card2);

        // Player draws a card then discards one
        player.drawCard();
        player.discardCard();
    }

    @Test
    public void testPlayerInstantiation() {
        assertEquals(1, player.getPlayerNumber());
        assertEquals("1 ", player.printPlayerDeck());
    }

    @Test 
    public void testPlayerCanDrawAndDiscard() {
        // Check player has 1 card "1"
        assertEquals("1 ", player.printPlayerDeck());

        // Check the decks have the right cards
        assertEquals("2", discardDeck.getCards());
        assertEquals("", drawDeck.getCards());
    }
}