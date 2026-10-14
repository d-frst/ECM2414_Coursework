import java.util.ArrayList;
import java.util.Random;

// TODO: output hand when game ends
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

    public boolean cardsMatch() {
        int valueToCheck = cards.get(0).getValue();
        int matching = 0;
        for (Card card : cards) {
            if (card.getValue() == valueToCheck) {
                matching++;
            }
        }

        return matching == 4;
    }

    public void printCards() {
        for (Card card : cards) {
            System.out.println(card.getValue());
        }
    }
}
