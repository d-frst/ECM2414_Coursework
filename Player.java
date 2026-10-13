public class Player {
    private int playerNumber;
    private PlayerDeck playerDeck;
    private TableDeck drawDeck;
    private TableDeck discardDeck;

    public Player(int playerNumber) {
        this.playerNumber = playerNumber;
    }

    public void drawCard() {
        Card card = drawDeck.drawCard();
        playerDeck.addCard(card);
    }


    public void discardCard() {
        Card card = playerDeck.removeCard(playerNumber);
        discardDeck.discardCard(card);
    }

}