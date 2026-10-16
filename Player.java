import java.io.*;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.*;

public class Player implements Runnable{
    private int playerNumber;
    private PlayerDeck playerDeck = new PlayerDeck();
    private TableDeck drawDeck;
    private TableDeck discardDeck;
    private FileWriter playerWriter;
    private AtomicBoolean anyPlayerWon;
    private AtomicInteger winningPlayerNumber;
    private Lock drawLock;
    private Lock discardLock;

    public Player(int playerNumber, AtomicBoolean anyPlayerWon, AtomicInteger winningPlayerNumber, Lock drawLock, Lock discardLock) throws IOException{
        this.playerNumber = playerNumber;
        this.anyPlayerWon = anyPlayerWon;
        this.winningPlayerNumber = winningPlayerNumber;
        String filename = "player%d_output.txt".formatted(playerNumber);
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
        try{writeFinalState(); writeOutput();}
        catch(IOException e) {
            System.out.println(e);
        }
    }

    private void writeStartingHand() throws IOException {
        playerWriter.write("player %d initial hand %s\n".formatted(playerNumber, playerDeck.printCards()));
    }

    private void writeFinalState() throws IOException {
        if (winningPlayerNumber.get() != playerNumber) {
            playerWriter.write("player %d has informed player %d that player %d has won\n".formatted(winningPlayerNumber.get(), playerNumber, winningPlayerNumber.get()));
        }
        playerWriter.write("player %d exits\nplayer %d hand: %s".formatted(playerNumber, playerNumber, playerDeck.printCards()));
    }

    public int getPlayerNumber() {
        return playerNumber;
    }

    // for game initialisation
    public void addToPlayerDeck(Card card) {
        playerDeck.addCard(card);
    }

    private int drawCard() {
        Card card = drawDeck.drawCard();
        if (card != null) {
            playerDeck.addCard(card);
            return card.getValue();
        }
        return -1;
    }

    private boolean checkWin() {
        return playerDeck.cardsMatch();
    }

    private void play() throws IOException {
        if (anyPlayerWon.get()) return;

        boolean won_1 = checkWin();

        if (won_1) {
            anyPlayerWon.set(true);
            winningPlayerNumber.set(playerNumber);
            playerWriter.write("player %d wins\n".formatted(playerNumber));
            return;
        }
        
        // check locks
        int result = acquireLocks(drawLock, discardLock);
        if (result == 0) {

            if (anyPlayerWon.get()) return;

            // cardDrawn or cardDiscarded being -1 means failure to perform the operation when one or both decks are empty
            int cardDrawn = drawCard();
            int cardDiscarded = -1;
            // don't bother discarding if a card couldn't be drawn
            if (cardDrawn != -1) {
                cardDiscarded = discardCard();
            }

            // unlock decks
            drawLock.unlock();
            discardLock.unlock();

            if (anyPlayerWon.get()) return;
            boolean won_2 = checkWin();
            if (cardDrawn != -1 && cardDiscarded != -1) {
                playerWriter.write("player %d draws a %d from deck %d\n".formatted(playerNumber, cardDrawn, drawDeck.getDeckNumber()));
                playerWriter.write("player %d discards a %d to deck %d\n".formatted(playerNumber, cardDiscarded, discardDeck.getDeckNumber()));   
            }

            if (won_2) {
                anyPlayerWon.set(true);
                winningPlayerNumber.set(playerNumber);
                playerWriter.write("player %d wins\n".formatted(playerNumber));
            }
        }
        
    }

    private int acquireLocks(Lock drawLock, Lock discardLock){
        boolean drawLockAcquired = false;
        boolean discardLockAcquired = false;

        // random number generator code
        Random r = new Random();

        // important to not attempt getting a lock if someone has won already
        while(!(drawLockAcquired && discardLockAcquired) && !anyPlayerWon.get()) {
            try {
                drawLockAcquired = drawLock.tryLock(r.nextInt(1000), TimeUnit.MILLISECONDS);
                discardLockAcquired = discardLock.tryLock(r.nextInt(1000), TimeUnit.MILLISECONDS);
            }
            catch (InterruptedException e) {}
        }

        // another player wins while trying to get a lock
        if (anyPlayerWon.get()) {
            if (drawLockAcquired) {
                drawLock.unlock();
            }
            if (discardLockAcquired) {
                discardLock.unlock();
            }
            return -1;
        }

        return 0;
        
    }

    private int discardCard() {
        Card card = playerDeck.removeCard(playerNumber);
        if (card != null) {
            discardDeck.discardCard(card);
            return card.getValue();
        }
        return -1;
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

    private void writeOutput() throws IOException {
        playerWriter.close();
    }

}