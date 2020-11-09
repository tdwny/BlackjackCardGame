package com.blackjack.blackjack.blackjack.pointCalculation.interfaces;

import com.blackjack.blackjack.blackjack.cardsAndDecks.classes.PlayingCard;
import java.util.ArrayList;

public interface PlayingCardCalculator {

    int calculatePoints(ArrayList<PlayingCard> hand);

}
