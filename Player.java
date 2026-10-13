// TODO: add checks for winning conditions

public class Player {
    private int playerNumber;
    private PlayerDeck playerDeck = new PlayerDeck();
    private TableDeck drawDeck = new TableDeck();
    private TableDeck discardDeck = new TableDeck();

    public Player(int playerNumber) {
        this.playerNumber = playerNumber;
    }

    public int getPlayerNumber() {
        return playerNumber;
    }

    // for game initialisation
    public void addToPlayerDeck(Card card) {
        playerDeck.addCard(card);
    }

    public void drawCard() {
        Card card = drawDeck.drawCard();
        playerDeck.addCard(card);
    }


    public void discardCard() {
        Card card = playerDeck.removeCard(playerNumber);
        discardDeck.discardCard(card);
    }

    public void setDrawDeck(TableDeck deck) {
        drawDeck = deck;
    }

    public void setDiscardDeck(TableDeck deck) {
        discardDeck = deck;
    }

}