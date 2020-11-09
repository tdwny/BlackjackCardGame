package com.blackjack.blackjack.blackjack.gameSequence.interfaces;

import com.blackjack.blackjack.blackjack.cardsAndDecks.classes.PlayingCard;
import java.util.ArrayList;

public interface PointsCalculator {

    int calculatePoints(ArrayList<PlayingCard> hand, boolean isDealer, boolean showDealerHand);
    int calculatePoints(ArrayList<PlayingCard> hand, boolean isDealer);
    int calculatePoints(ArrayList<PlayingCard> hand);

}
