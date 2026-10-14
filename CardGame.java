import java.util.ArrayList;

public class CardGame {
    private int numPlayers;
    private ArrayList<Player> players = new ArrayList<Player>();
    private ArrayList<TableDeck> tableDecks = new ArrayList<TableDeck>();
    
    public CardGame(int numPlayers) {
        this.numPlayers = numPlayers;
        initGame();
    }

    public void initGame() {
        CardDeck deck = new CardDeck();
        deck.createDeck(numPlayers);

        // generate cards

        // generate players
        for (int i = 0; i < numPlayers; i++) {
            players.add(new Player(i + 1));
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
            tableDecks.add(new TableDeck());
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
    }
}
