package cardgame.tests;
import java.io.IOException;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import cardgame.CardGame;
import java.util.Arrays;


/*
TODO:
- win at beginning test: with and without preferred denomination
- before each move each player has 4 cards and after they still have 4 cards.
- at the end of the game each player has 4 cards
- check that only one player has won?
*/


// Test to see if a player will win immediatley if given a winning combo at the start (non preferred cards)

public class MockGameTest1 {
    private CardGame cardgame;

    @Before
    public void setUp() throws IOException{
        cardgame = new CardGame(2, "testCards2.txt");
        cardgame.play();
    }

    @Test(timeout = 3000)
    public void testPlayerOneWins() throws IOException{
        // Ensure only 1 player wins
        assertEquals(1, cardgame.getWinner());
        assertNotEquals(2, cardgame.getWinner());

        // Check table decks to prove no cards have been drawn and players stilll have 4 cards
        assertEquals(Arrays.asList(4, 4), cardgame.getTableDeckSizes());
        assertEquals(Arrays.asList(4, 4), cardgame.getPlayerDeckSizes());
    }
}
