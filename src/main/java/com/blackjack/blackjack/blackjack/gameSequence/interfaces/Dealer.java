package com.blackjack.blackjack.blackjack.gameSequence.interfaces;

import com.blackjack.blackjack.blackjack.cardsAndDecks.classes.PlayingCard;
import java.util.ArrayList;

public interface Dealer {

    //Getters
    ArrayList<PlayingCard> getHand();
    boolean getBusts();

    //Setters
    void setBusts(boolean busts);

    //Methods
    void dealerHit(PlayingCard card);
    void printCard(int elementToDraw);
    void dealerHandScoreDisplay(boolean showHand);
    boolean dealersMove();
    void dealersTurn(PlayingCard card);
    void dealerHitAndDisplay(PlayingCard card);
    boolean forceDealerHit();

}
