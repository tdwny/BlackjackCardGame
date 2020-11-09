package com.blackjack.blackjack.blackjack.gameSequence.classes;

import com.blackjack.blackjack.blackjack.cardsAndDecks.classes.PlayingCard;
import java.util.ArrayList;

public class StandardGameDeck {

    int numberOfDecks = 1;
    final String deckType = "standarddeck";
    ArrayList<PlayingCard> gameDeck;

    public StandardGameDeck(ArrayList<PlayingCard> gameDeck) {
        this.gameDeck = gameDeck;
    }

    //Getters
    public int getNumberOfDecks() {
        return numberOfDecks;
    }

    public ArrayList<PlayingCard> getGameDeck() {
        return gameDeck;
    }

    //Setters
    public void setNumberOfDecks(int numberOfDecks) {
        this.numberOfDecks = numberOfDecks;
    }

    public void setGameDeck(ArrayList<PlayingCard> gameDeck) {
        this.gameDeck = gameDeck;
    }
}
