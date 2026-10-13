import java.util.ArrayList;

public class TableDeck {
    private ArrayList<Card> cards = new ArrayList<Card>();

    public void discardCard(Card card) {
        // add card
        cards.add(card);
    }

    public Card drawCard() {
        // remove card
        return cards.remove(0);
    }

    public void printCards() {
        System.out.println(cards);
    }
}
