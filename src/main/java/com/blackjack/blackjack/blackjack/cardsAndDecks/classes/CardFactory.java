package com.blackjack.blackjack.blackjack.cardsAndDecks.classes;

import com.blackjack.blackjack.blackjack.cardsAndDecks.interfaces.PlayingCardFactory;
import java.util.ArrayList;
import java.util.Random;

public class CardFactory implements PlayingCardFactory {

    public PlayingCard getSingleCard(String criteria) {
        try {
            if (criteria.toLowerCase().equals("standardcard")) {
                return new SinglePlayingCard(false, new Random()).buildCard();
            }
            if (criteria.toLowerCase().equals("nonstandardcard")) {
                return new SinglePlayingCard(true, new Random()).buildCard();
            }
        } catch (Exception e) {
            System.out.println("Invalid Input");
        }
        return null;
    }

    public ArrayList<PlayingCard> getDeckOfCards(String criteria, int numberOfDecks) {
        try {
            if (criteria.toLowerCase().equals("standarddeck")) {
                ArrayList<PlayingCard> deck = new ArrayList<>();
                for (int i = 0; i < numberOfDecks; i++) {
                    ArrayList<PlayingCard> tempDeck = new DeckOfPlayingCards(false, new ArrayList()).buildDeck();
                    for (int n = 0; n < tempDeck.size(); n++) {
                        deck.add(tempDeck.get(n));
                    }
                }
                return deck;
            }
            if (criteria.equals("nonstandarddeck")) {
                ArrayList<PlayingCard> deck = new ArrayList<>();
                for (int i = 0; i < numberOfDecks; i++) {
                    ArrayList<PlayingCard> tempDeck = new DeckOfPlayingCards(true, new ArrayList()).buildDeck();
                    for (int n = 0; n < tempDeck.size(); n++) {
                        deck.add(tempDeck.get(n));
                    }
                }
                return deck;
            }
        } catch (Exception e) {
            System.out.println("Invalid Input");
        }
        return null;
    }
}