import java.util.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;


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

    public void generateCardFile(int numPlayers) {
        // Open file
        try (FileWriter myWriter = new FileWriter("cards.txt", true)) {
            createDeck(numPlayers)
            for (int i = 0 ; i < cards.size(); i++) {
                Card currentCard = cards.get(i);
                int cardNumber = currentCard.getValue();
                String cardValue = "%d\n";
                String formattedValue = String.format(cardValue, cardNumber);
                myWriter.write(formattedValue);
            }
        }
        catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();     
        } 
    }

    public void readCardFile(int numPlayers) {
        // Open cards.txt
        File cardsFile = new File("cards.txt");
        try (Scanner myReader = new Scanner(cardsFile)) {
            // read over whole file
            while (myReader.hasNextLine()) {
                // get string and turn into card
                String cardValueString = myReader.nextLine();
                int cardValue = Integer.parseInt(cardValueString);
                Card newCard = new Card(cardValue);
                cards.add(newCard);
            }
            myReader.close();
        }
        catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public Card drawCard() {
        return cards.remove(0);
    }

    public int getDeckSize() {
        return cards.size();
    }

    public void printCards() {
        System.out.println(cards);
    }
}
