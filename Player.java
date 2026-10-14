// TODO: add checks for winning conditions

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Player {
    private int playerNumber;
    private PlayerDeck playerDeck = new PlayerDeck();
    private TableDeck drawDeck = new TableDeck();
    private TableDeck discardDeck = new TableDeck();
    private File playerActionsFile;
    FileWriter playerWriter;

    public Player(int playerNumber) throws IOException{
        this.playerNumber = playerNumber;
        String filename = "player%d_output.txt".formatted(playerNumber);
        playerActionsFile = new File(filename);
        
        playerWriter = new FileWriter(filename, true);

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

    public void writeToFile(String string){

    }

}