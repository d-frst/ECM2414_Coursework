import java.util.ArrayList;
import java.util.*;

public class CardDeck {
    private ArrayList<Card> cards = new ArrayList<Card>();

    public void createDeck(int numPlayers) {
        // each player has 8 cards of their own denomination in total
        for (int j = 0 ; j < 8; j++) {
            for (int i = 1 ; i <= numPlayers; i++) {
                cards.add(new Card(i));
            }
        }
        Collections.shuffle(cards);
    }

    public void printCards() {
        System.out.println(cards);
    }
}
