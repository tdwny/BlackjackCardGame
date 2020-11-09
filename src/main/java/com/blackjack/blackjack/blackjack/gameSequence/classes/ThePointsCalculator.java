package com.blackjack.blackjack.blackjack.gameSequence.classes;

import com.blackjack.blackjack.blackjack.gameSequence.interfaces.PointsCalculator;
import com.blackjack.blackjack.blackjack.pointCalculation.classes.AceHighOrLow;
import com.blackjack.blackjack.blackjack.cardsAndDecks.classes.PlayingCard;
import java.util.ArrayList;

public class ThePointsCalculator implements PointsCalculator {

    private AceHighOrLow aceHighOrLow;

    public ThePointsCalculator(AceHighOrLow aceHighOrLow) {
        this.aceHighOrLow = aceHighOrLow;
    }

    public int calculatePoints(ArrayList<PlayingCard> hand, boolean isDealer, boolean showDealerHand) {
        if (!isDealer || (isDealer && showDealerHand)) {
            return aceHighOrLow.calculatePoints(hand);
        } else {
            return hand.get(0).pointValue();
        }
    }

    public int calculatePoints(ArrayList<PlayingCard> hand, boolean isDealer) {
        return calculatePoints(hand, isDealer, true);
    }

    public int calculatePoints(ArrayList<PlayingCard> hand) {
        return calculatePoints(hand, false, true);
    }

}
