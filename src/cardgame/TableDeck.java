package cardgame;
import java.util.ArrayList;
import java.io.*;

public class TableDeck {
    private final int deckNumber;
    private ArrayList<Card> cards = new ArrayList<Card>();
    private FileWriter deckWriter;

    public TableDeck(int deckNumber) throws IOException {
        this.deckNumber = deckNumber;
        this.deckWriter = new FileWriter("deck%d_output.txt".formatted(this.deckNumber));
    }

    public void discardCard(Card card) {
        // add card
        cards.add(card);
    }

    public Card drawCard() {
        // remove card
        if (!cards.isEmpty()) {
            return cards.remove(0);
        }
        return null;
    }

    public int getDeckNumber() {
        return deckNumber;
    }

    public void printCards() {
        for (Card card : cards) {
            System.out.println(card.getValue());
        }
    }

    public String getCards() {
        String str = "";
        for (Card card : cards) {
            str += "%d ".formatted(card.getValue());
        }

        return str.strip();

    }

    public void write() throws IOException {
        deckWriter.write("deck%d contents: %s".formatted(deckNumber, getCards()));
        deckWriter.close();
    }
}
