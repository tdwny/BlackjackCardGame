package com.blackjack.blackjack.blackjack.cardsAndDecks.classes;

import com.blackjack.blackjack.blackjack.cardsAndDecks.interfaces.APlayingCard;

import java.util.Random;

public class SinglePlayingCard implements APlayingCard {

    //Random class to generate constructor inputs
    private Random random;

    //Instance Variables
    boolean includeJokers;
    private String[] suits = {"\u2665", "\u2666", "\u2664", "\u2667"};
    private String[] standardRank = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};
    private String[] nonStandardRank = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "Joker"};
    private int[] standardPoints = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 10, 10, 10};
    private int[] nonStandardPoints = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 10, 10, 10, 0};

    public SinglePlayingCard(boolean includeJokers, Random random) {
        this.includeJokers = includeJokers;
        this.random = random;
    }

    //Decision to build a standard or non-standard deck
    public PlayingCard buildCard() {
        if (includeJokers) {
            return buildStandardCard();
        } else {
            return buildNonStandardCard();
        }
    }

    //buildStandardCard method will not include jokers
    private PlayingCard buildStandardCard() {
        String suitTemp = suits[random.nextInt(suits.length)];
        int tempElement = random.nextInt(standardRank.length);
        PlayingCard playingCard = new PlayingCard(suitTemp, standardRank[tempElement], (suitTemp == "\u2664" || suitTemp == "\u2667") ? true : false, standardPoints[tempElement]);
        return playingCard;
    }

    //buildNonStandardCard method will include jokers
    private PlayingCard buildNonStandardCard() {
        String suitTemp = suits[random.nextInt(suits.length)];
        int tempElement = random.nextInt(standardRank.length);
        PlayingCard playingCard = new PlayingCard(suitTemp, nonStandardRank[tempElement], (suitTemp == "\u2664" || suitTemp == "\u2667") ? true : false, nonStandardPoints[tempElement]);
        return playingCard;
    }
}
