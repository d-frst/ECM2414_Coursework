import java.util.ArrayList;

// TODO: output deck at the end of the game to file

public class TableDeck {
    private int deckNumber;
    private ArrayList<Card> cards = new ArrayList<Card>();

    public TableDeck(int deckNumber) {
        this.deckNumber = deckNumber;
    }

    public void discardCard(Card card) {
        // add card
        cards.add(card);
    }

    public Card drawCard() {
        // remove card
        return cards.remove(0);
    }

    public int getDeckNumber() {
        return deckNumber;
    }

    public void printCards() {
        for (Card card : cards) {
            System.out.println(card.getValue());
        }
    }
}
