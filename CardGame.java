import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.io.IOException;

public class CardGame {
    private int numPlayers;
    private ArrayList<Thread> playerThreads = new ArrayList<Thread>();
    private ArrayList<TableDeck> tableDecks = new ArrayList<TableDeck>();
    private AtomicBoolean anyPlayerWon = new AtomicBoolean(false);
    private AtomicInteger winningPlayerNumber = new AtomicInteger(0);

    
    public CardGame(int numPlayers) {
        this.numPlayers = numPlayers;
        int result = initGame();
        System.out.println(result);
    }

    public int initGame() {
        CardDeck deck = new CardDeck();

        // generate cards
        deck.readCardFile(numPlayers);


        ArrayList<Player> players = new ArrayList<Player>();


        // generate players
        for (int i = 0; i < numPlayers; i++) {
            try {
                players.add(new Player(i + 1, anyPlayerWon, winningPlayerNumber));   
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
        
        // Turn players into threads
        for (Player player : players) {
            Thread playerThread = new Thread(player);
            playerThreads.add(playerThread);
        }

        return 0;
    }

    public void play() throws IOException {
        // Start player threads
        for (Thread thread : playerThreads) {
            thread.start();
        }
    }

    // public void debugPrint() {
    //     for (Player player : playerThreads) {
    //         System.out.println("player %d deck".formatted(player.getPlayerNumber()));
    //         player.printPlayerDeck();
    //     }
    //     for (TableDeck deck : tableDecks) {
    //         System.out.println("table deck");
    //         deck.printCards();
    //     }
    // }

    public static void main(String[] args) throws IOException {
        CardDeck deck = new CardDeck();
        deck.generateCardFile(2);
        CardGame game = new CardGame(2);
        game.play();
        //game.debugPrint();
    }
}
