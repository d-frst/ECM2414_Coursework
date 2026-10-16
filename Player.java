import java.io.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.*;
import java.util.concurrent.TimeUnit;
import java.util.Random;

public class Player implements Runnable{
    private int playerNumber;
    private PlayerDeck playerDeck = new PlayerDeck();
    private TableDeck drawDeck;
    private TableDeck discardDeck;
    private File playerActionsFile;
    FileWriter playerWriter;
    private AtomicBoolean anyPlayerWon;
    private AtomicInteger winningPlayerNumber;
    private Lock drawLock;
    private Lock discardLock;

    public Player(int playerNumber, AtomicBoolean anyPlayerWon, AtomicInteger winningPlayerNumber, Lock drawLock, Lock discardLock) throws IOException{
        this.playerNumber = playerNumber;
        this.anyPlayerWon = anyPlayerWon;
        this.winningPlayerNumber = winningPlayerNumber;
        String filename = "player%d_output.txt".formatted(playerNumber);
        playerActionsFile = new File(filename);
        playerWriter = new FileWriter(filename, false);
        this.drawLock = drawLock;
        this.discardLock = discardLock;
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
        
        // check locks
        acquireLocks(drawLock, discardLock);
        

        int cardDrawn = drawCard();
        int cardDiscarded = discardCard();

        // unlock decks
        drawLock.unlock();
        discardLock.unlock();
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
    private void acquireLocks(Lock drawLock, Lock discardLock){
        boolean drawLockAcquired = false;
        boolean discardLockAcquired = false;

        // random number generator code
        Random r = new Random();

        
        while(!drawLockAcquired && !discardLockAcquired) {
            try {
                drawLockAcquired = drawLock.tryLock(r.nextInt(1000), TimeUnit.MILLISECONDS);
                discardLockAcquired = discardLock.tryLock(r.nextInt(1000), TimeUnit.MILLISECONDS);
            }
            catch (InterruptedException e) {}
        }
        
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