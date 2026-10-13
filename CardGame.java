import java.util.ArrayList;

public class CardGame {
    private int numPlayers;
    private ArrayList<Player> players = new ArrayList<Player>();
    private ArrayList<CardDeck> tableDecks = new ArrayList<CardDeck>();
    
    public CardGame(int numPlayers) {
        this.numPlayers = numPlayers;
    }

    public void initGame() {
        CardDeck deck = new CardDeck();
        deck.createDeck(numPlayers);
    }
}
