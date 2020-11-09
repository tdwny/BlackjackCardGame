package com.blackjack.blackjack.blackjack.cardsAndDecks.classes;

//This class creates a new PlayingCard Object

import com.blackjack.blackjack.blackjack.cardsAndDecks.interfaces.Card;
import com.blackjack.blackjack.blackjack.cardsAndDecks.interfaces.Values;

public class PlayingCard implements Card, Values {

    //Instance Variables
    private String suit;
    private String rank;
    private boolean colorBlack;
    private int points;

    //Constructor
    public PlayingCard(String suit, String rank, boolean colorBlack, int points) {
        this.suit = suit;
        this.rank = rank;
        this.colorBlack = colorBlack;
        this.points = points;
    }

    //Getters
    public String getSuit() {
        return suit;
    }

    public String getRank() {
        return rank;
    }

    public int getPoints() {
        return points;
    }

    public boolean isColorBlack() {
        return colorBlack;
    }

    //getSingleCard will return the color (red or black), rank and suit
    public String displayCard() {
        if (colorBlack) {
            return "\033[0;37m" + this.rank + this.getSuit();
        } else {
            return "\033[0;31m" + this.rank + this.getSuit();
        }
    }

    public int pointValue(){
        return points;
    }

}