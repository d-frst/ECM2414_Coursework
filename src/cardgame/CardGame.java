package cardgame;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.io.IOException;
import java.util.concurrent.locks.*;
import java.util.Scanner;

public class CardGame {
    private int numPlayers;
    private String deckPath;
    private ArrayList<Thread> playerThreads = new ArrayList<Thread>();
    private ArrayList<TableDeck> tableDecks = new ArrayList<TableDeck>();
    private AtomicBoolean anyPlayerWon = new AtomicBoolean(false);
    private AtomicInteger winningPlayerNumber = new AtomicInteger(0);
    private ArrayList<Lock> tableDeckLocks = new ArrayList<Lock>();
    
    public CardGame(int numPlayers, String deckPath) {
        this.numPlayers = numPlayers;
        this.deckPath = deckPath;
        int result = initGame();
    }

    public int initGame() {
        CardDeck deck = new CardDeck();
        deck.readCardFile(numPlayers, deckPath);

        ArrayList<Player> players = new ArrayList<Player>();

        // make locks for table decks
        for (int i = 0; i < numPlayers; i++) {
            tableDeckLocks.add(new ReentrantLock(true));
        }

        // generate players
        for (int i = 0; i < numPlayers; i++) {
            try {
                players.add(new Player(
                    i + 1, 
                    anyPlayerWon, 
                    winningPlayerNumber, 
                    tableDeckLocks.get(i), 
                    tableDeckLocks.get((i + 1) % numPlayers)));   
            }
            catch (IOException e) {
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
            try {
                tableDecks.add(new TableDeck(i + 1));
            } catch (IOException e) {
                System.out.println("Error making a table deck");
                return -1;
            }
            
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

    public int getWinner() {
        return winningPlayerNumber.get();
    }

    public void play() throws IOException {
        // Start player threads
        for (Thread thread : playerThreads) {
            thread.start();
        }

        // wait for all players to finish
        for (Thread thread : playerThreads) {
            try {
                thread.join();
            } catch (InterruptedException e) {}
        }

        // output final table deck state
        for (TableDeck deck : tableDecks) {
            try {
                deck.write();
            } catch (IOException e) {
            }
        }
    }

    public static void main(String[] args) throws IOException {
        Scanner terminalInput = new Scanner(System.in);
        System.out.println("Please enter the number of players:");
        int numPlayers = Integer.parseInt(terminalInput.nextLine());
        System.out.println("Please enter the location of the pack to load:");
        String deckPath = terminalInput.nextLine();
        terminalInput.close();

        CardDeck deck = new CardDeck();
        // deck.readCardFile(numPlayers, deckPath);
        deck.generateCardFile(numPlayers);
        CardGame game = new CardGame(numPlayers, deckPath);
        game.play();
        System.out.println("player %d wins".formatted(game.getWinner()));
    }

    // For testing
    public int getNumPlayers() {
        return numPlayers;
    }
    
    public int getNumTableDecks() {
        return tableDecks.size();
    }

    public List<Integer> getTableDeckSizes() {
        ArrayList<Integer> lengths = new ArrayList<>();
        for (TableDeck deck : tableDecks) {
            lengths.add(deck.getNumCards());
        }
        return lengths;
    }
}
