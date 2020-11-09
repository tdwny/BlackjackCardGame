package com.blackjack.blackjack.blackjack.cardsAndDecks.classes;

import com.blackjack.blackjack.blackjack.cardsAndDecks.interfaces.Deck;

import java.util.ArrayList;

//This class creates a new CardsAndDecks object

public class DeckOfPlayingCards implements Deck {

    //Instance Arrays & Variables
    private boolean includeJokers;
    private ArrayList<PlayingCard> deckOfPlayingCards;
    private String[] suits = {"\u2665", "\u2666", "\u2664", "\u2667"}; //Heart, Diamond, Spade, Club
    private String[] standardRank = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};
    private String[] nonStandardRank = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "Joker"};
    private int[] standardPoints = {11, 2, 3, 4, 5, 6, 7, 8, 9, 10, 10, 10, 10};
    private int[] nonStandardPoints = {11, 2, 3, 4, 5, 6, 7, 8, 9, 10, 10, 10, 10, 0};

//    private String[] suits = {"\u2665", "\u2666", "\u2664"}; //, "\u2664", "\u2667"}; //Heart, Diamond, Spade, Club
//    private String[] standardRank = {"A", "10", "10"}; //, "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};
//    private String[] nonStandardRank = {"A", "10", "10"}; //, "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "Joker"};
//    private int[] standardPoints = {11, 10, 10}; //, 3, 4, 5, 6, 7, 8, 9, 10, 10, 10, 10};
//    private int[] nonStandardPoints = {11, 10, 10}; //, 3, 4, 5, 6, 7, 8, 9, 10, 10, 10, 10, 0};

    //Constructor input it used to determine if a standard or non-standard deck is required
    public DeckOfPlayingCards(boolean includeJokers, ArrayList arrayList) {
        this.includeJokers = includeJokers;
        this.deckOfPlayingCards = arrayList;
    }

    //Decision to build a standard or non-standard deck
    public ArrayList<PlayingCard> buildDeck() {
        if (includeJokers) {
            return buildNonStandardDeck();
        } else {
            return buildStandardDeck();
        }
    }

    //buildStandardDeck method does not include jokers
    private ArrayList<PlayingCard> buildStandardDeck() {

        for (int s = 0; s < suits.length; s++) {

            for (int i = 0; i < standardRank.length; i++) {

                boolean colorBlack = false;

                if (suits[s] == "\u2664" || suits[s] == "\u2667") {
                    colorBlack = true;
                }
                deckOfPlayingCards.add(new PlayingCard(suits[s], standardRank[i], colorBlack, standardPoints[i]));
            }
        }
        return deckOfPlayingCards;
    }

    //buildNonStandardDeck method that includes jokers
    private ArrayList<PlayingCard> buildNonStandardDeck() {

        for (int s = 0; s < suits.length; s++) {

            for (int i = 0; i < nonStandardRank.length; i++) {

                boolean colorBlack = false;

                if (suits[s] == "\u2664" || suits[s] == "\u2667") {
                    colorBlack = true;
                }
                deckOfPlayingCards.add(new PlayingCard(suits[s], nonStandardRank[i], colorBlack, nonStandardPoints[i]));
            }
        }
        return deckOfPlayingCards;
    }
}
