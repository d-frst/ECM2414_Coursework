import java.io.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;


public class Player implements Runnable{
    private int playerNumber;
    private PlayerDeck playerDeck = new PlayerDeck();
    private TableDeck drawDeck;
    private TableDeck discardDeck;
    private File playerActionsFile;
    FileWriter playerWriter;
    private AtomicBoolean anyPlayerWon;
    private AtomicInteger winningPlayerNumber;

    public Player(int playerNumber, AtomicBoolean anyPlayerWon, AtomicInteger winningPlayerNumber) throws IOException{
        this.playerNumber = playerNumber;
        this.anyPlayerWon = anyPlayerWon;
        this.winningPlayerNumber = winningPlayerNumber;
        String filename = "player%d_output.txt".formatted(playerNumber);
        playerActionsFile = new File(filename);
        playerWriter = new FileWriter(filename, true);
    }

    @Override 
    public void run(){
        try{writeStartingHand();}
        catch(IOException e) {System.out.println(e);}
        while (anyPlayerWon.get() == false) {
            try {play();}
            catch(IOException e) {
                System.out.println(e);
            }
        }
        try{writeOutput();}
        catch(IOException e) {
            System.out.println(e);
        }
    }

    public void writeStartingHand() throws IOException {
        playerWriter.write("player 1 initial hand %s\n".formatted(playerDeck.printCards()));
    }

    public int getPlayerNumber() {
        return playerNumber;
    }

    // for game initialisation
    public void addToPlayerDeck(Card card) {
        playerDeck.addCard(card);
    }

    public int drawCard() {
        Card card = drawDeck.drawCard();
        playerDeck.addCard(card);
        return card.getValue();
    }

    public boolean checkWin() {
        return playerDeck.cardsMatch();
    }

    public boolean play() throws IOException {
        boolean won_1 = checkWin();

        if (won_1) {
            anyPlayerWon.set(true);
            winningPlayerNumber.set(playerNumber);
            playerWriter.write("player %d wins\nplayer %d exits".formatted(playerNumber, playerNumber));
            return true;
        }

        int cardDrawn = drawCard();
        int cardDiscarded = discardCard();
        boolean won_2 = checkWin();
        playerWriter.write("player %d draws a %d from deck %d\n".formatted(playerNumber, cardDrawn, drawDeck.getDeckNumber()));
        playerWriter.write("player %d discards a %d to deck %d\n".formatted(playerNumber, cardDiscarded, discardDeck.getDeckNumber()));

        if (won_2) {
            anyPlayerWon.set(true);
            winningPlayerNumber.set(playerNumber);
            playerWriter.write("player %d wins\nplayer %d exits".formatted(playerNumber, playerNumber));
            return true;
        }

        return false;
    }

    public int discardCard() {
        Card card = playerDeck.removeCard(playerNumber);
        discardDeck.discardCard(card);
        return card.getValue();
    }

    public void setDrawDeck(TableDeck deck) {
        drawDeck = deck;
    }

    public void setDiscardDeck(TableDeck deck) {
        discardDeck = deck;
    }

    public void printPlayerDeck() {
        playerDeck.printCards();
    }

    public void writeOutput() throws IOException {
        playerWriter.close();
    }

}