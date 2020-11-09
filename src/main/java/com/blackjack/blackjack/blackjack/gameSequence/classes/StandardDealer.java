package com.blackjack.blackjack.blackjack.gameSequence.classes;

import com.blackjack.blackjack.blackjack.gameSequence.interfaces.Dealer;
import com.blackjack.blackjack.blackjack.displayText.Text;
import com.blackjack.blackjack.blackjack.cardsAndDecks.classes.PlayingCard;

import java.util.ArrayList;

public class StandardDealer implements Dealer {

    private String playerName = "Dealer";
    private ArrayList<PlayingCard> hand;
    private boolean busts = false;
    private ThePointsCalculator pointsCalculator;
    private int forceHitOn = 17;
    private boolean hasBlackjack = false;

    public StandardDealer(ArrayList<PlayingCard> hand, ThePointsCalculator pointsCalculator) {
        this.hand = hand;
        this.pointsCalculator = pointsCalculator;
    }

    //Getters
    public ArrayList<PlayingCard> getHand() {
        return hand;
    }
    public boolean getBusts() {
        return busts;
    }

    public boolean getHasBlackjack() {
        return hasBlackjack;
    }

    //Setters
    public void setBusts(boolean busts) {
        this.busts = busts;
    }

    public void setHasBlackjack(boolean hasBlackjack) {
        this.hasBlackjack = hasBlackjack;
    }

    //Displays
    public void printCard(int elementToDraw) {
        if (elementToDraw != 1) {
            Text.printDrawCard(getHand(), true, elementToDraw);
        } else {
            System.out.println(Text.reset + Text.dealerSecondCard);
        }
    }

    public void dealerHandScoreDisplay(boolean showHand) {
        if (showHand) {
            Text.printHand(getHand(), true, true);
            System.out.println(Text.reset + Text.printShowing(true, pointsCalculator.calculatePoints(getHand(), true)));
        } else {
            Text.printHand(getHand(), true, false);
            System.out.println(Text.reset + Text.printShowing(true, pointsCalculator.calculatePoints(getHand(), true, false)));
        }
    }

    public void dealerHitAndDisplay(PlayingCard card) {
        dealerHit(card);
        Text.printDrawCard(getHand(), true, getHand().size() - 1);
    }


    //BJ Check for Insurance
    public boolean blackjackCheck(ArrayList<PlayingCard> hand) {
        if ((hand.get(0).getRank() == "A" && hand.get(1).pointValue() == 10 ||
                hand.get(1).getRank() == "A" && hand.get(0).pointValue() == 10) &&
                hand.size() == 2) {
            return true;
        }
        return false;
    }

    public boolean dealerBlackjack() {
        if (blackjackCheck(getHand())) {
            setHasBlackjack(true);
        }
        return getHasBlackjack();
    }

    public boolean insuranceAllowed(){
        if (getHand().get(0).getRank() == "A" && hand.size() == 2) {
            return true;
        }
        return false;
    }

    //Dealer Decision/Logic
    public void dealerHit(PlayingCard card) {
        getHand().add(card);
    }

    public boolean dealersMove(){
        boolean forceDealerHitCheck = forceDealerHit();

        while (forceDealerHitCheck){
            return true;
        }
        return false;
    }

    public boolean forceDealerHit() {
        return (pointsCalculator.calculatePoints(getHand(), true, true) < forceHitOn) ? true : false;
    }

    //Play
    public void dealersTurn(PlayingCard card) {

        System.out.println(Text.turn(true));
        dealerHandScoreDisplay(true);

        System.out.println(Text.dealerHit);
        dealerHitAndDisplay(card);
        dealerHandScoreDisplay(true);

        if (pointsCalculator.calculatePoints(getHand(), true, true) > 21) {
            System.out.println(Text.dealerBusts);
            setBusts(true);
        }
    }

    public void endDealerTurn(){

        dealerHandScoreDisplay(true);
        System.out.println(Text.dealerStays);


    }

}