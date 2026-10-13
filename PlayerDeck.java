import java.util.ArrayList;
import java.util.Random;

public class PlayerDeck {
    private ArrayList<Card> cards = new ArrayList<Card>();

    public void addCard(Card card) {
        // add card
        cards.add(card);
    }

    public Card removeCard(int playerNumber) {
        // collect indices cards of non-preferred denomination
        ArrayList<Integer> nonPreferredCards = new ArrayList<Integer>();
        for (int i = 0; i < cards.size(); i++) {
            Card current = cards.get(i);
            if (current.getValue() != playerNumber) {
                nonPreferredCards.add(i);
            }
        }

        int indexToRemove = getRandomIndex(nonPreferredCards);
        return cards.remove(indexToRemove);
    }

    public int getRandomIndex(ArrayList<Integer> nonPreferredCards) {
        Random r = new Random();
        return nonPreferredCards.get(r.nextInt(nonPreferredCards.size()));
    }

    public void printCards() {
        System.out.println(cards);
    }
}
