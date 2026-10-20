package cardgame.tests;
import java.io.IOException;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import cardgame.CardGame;


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

    @Test
    public void testPlayerOneWins() throws IOException{
        assertEquals(1, cardgame.getWinner());
        assertNotEquals(2, cardgame.getWinner());
    }
}
