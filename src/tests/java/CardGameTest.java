package tests.java;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import main.java.CardGame;

// Runs a normal game with 2 players to check instantiation and invarients

public class CardGameTest {
    private CardGame cardgame;
    @Before
    public void setUp(){
        cardgame = new CardGame(2, "testCards1.txt");
    }

    @Test
    public void testGameInstantiation() {
        // Test 2 players have been made
        assertEquals(2, cardgame.getNumPlayers());
        // Test 2 decks have been made
        assertEquals(2, cardgame.getNumTableDecks());
        
        
        // Test decks have 4 cards each
        // Fancy code beacuse java doesn't like the list as a parameter
        assertEquals(Arrays.asList(4, 4), cardgame.getTableDeckSizes());
        // Test each player has 4 cards at start
        assertEquals(Arrays.asList(4, 4), cardgame.getPlayerDeckSizes());
        // Test 8 cards in tabledecks at start of game
        assertEquals(8, cardgame.getTableDeckSizes().stream().mapToInt(Integer::intValue).sum());
    }

    // Test Invariants
    @Test(timeout = 5000)
    public void testInvarients() throws IOException{
        cardgame.play();
        // Test each player has 4 cards at end
        assertEquals(Arrays.asList(4, 4), cardgame.getPlayerDeckSizes());
        // Test 8 cards in system at end of game
        assertEquals(8, cardgame.getTableDeckSizes().stream().mapToInt(Integer::intValue).sum());
    }

    // Test player output files and the deck files were created
    @Test
    public void testFilesCraeted() throws IOException{
        cardgame.play();

        File playerFile1 = new File("player" + 1 + "_output.txt");
        assertTrue(playerFile1.exists());
        File playerFile2 = new File("player" + 2 + "_output.txt");
        assertTrue(playerFile2.exists());

        File deckFile1 = new File("deck1_output.txt");
        File deckFile2 = new File("deck2_output.txt");
        assertTrue(deckFile1.exists());
        assertTrue(deckFile2.exists());
    }

}
