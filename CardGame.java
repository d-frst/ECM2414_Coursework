import java.util.ArrayList;
import java.io.IOException;

public class CardGame {
    private int numPlayers;
    private ArrayList<Player> players = new ArrayList<Player>();
    private ArrayList<TableDeck> tableDecks = new ArrayList<TableDeck>();
    
    public CardGame(int numPlayers) {
        this.numPlayers = numPlayers;
        int result = initGame();
        System.out.println(result);
    }

    public int initGame() {
        CardDeck deck = new CardDeck();

        // generate cards
        deck.readCardFile(numPlayers);

        // generate players
        for (int i = 0; i < numPlayers; i++) {
            try {
                players.add(new Player(i + 1));    
            } catch (IOException e) {
                System.out.println("Error creating a new player");
                return -1;
            }
            
        }

        // distribute cards to players
        for (int i = 0; i < numPlayers * 4; i++) {
            int currentPlayerNumber = (i % numPlayers);
            Player currentPlayer = players.get(currentPlayerNumber);
            Card cardtoAdd = deck.drawCard();
            currentPlayer.addToPlayerDeck(cardtoAdd);
        }

        // generate TableDecks
        for (int i = 0; i < numPlayers; i++) {
            tableDecks.add(new TableDeck(i + 1));
        }

        // add cards to table decks
        int currentDeckNumber = 0;
        while (deck.getDeckSize() > 0) {
            Card cardtoAdd = deck.drawCard();
            TableDeck currentDeck = tableDecks.get(currentDeckNumber);
            currentDeck.discardCard(cardtoAdd);
            currentDeckNumber = (currentDeckNumber + 1) % numPlayers;
        }

        // link decks to players
        for (Player player : players) {
            TableDeck drawDeck = tableDecks.get((player.getPlayerNumber() - 1) % numPlayers);
            TableDeck discardDeck = tableDecks.get((player.getPlayerNumber()) % numPlayers);
            player.setDrawDeck(drawDeck);
            player.setDiscardDeck(discardDeck);
        }

        return 0;
    }

    public void play() throws IOException {
        boolean anyPlayerWon = false;
        int currentPlayerNumber = 0;

        while (anyPlayerWon == false) {
            Player currentPlayer = players.get(currentPlayerNumber);
            anyPlayerWon = currentPlayer.play();
            currentPlayerNumber = (currentPlayerNumber + 1) % numPlayers;
        }

        for (Player player : players) {
            player.writeOutput();
        }
    }

    public void debugPrint() {
        for (Player player : players) {
            System.out.println("player %d deck".formatted(player.getPlayerNumber()));
            player.printPlayerDeck();
        }
        for (TableDeck deck : tableDecks) {
            System.out.println("table deck");
            deck.printCards();
        }
    }

    public static void main(String[] args) throws IOException {
        CardDeck deck = new CardDeck();
        deck.generateCardFile(2);
        CardGame game = new CardGame(2);
        game.play();
        game.debugPrint();
    }
}
