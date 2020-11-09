package com.blackjack.blackjack.blackjack.gameSequence.classes;

import com.blackjack.blackjack.blackjack.gameSequence.enums.MenuSelection;
import com.blackjack.blackjack.blackjack.gameSequence.enums.PayoutType;
import com.blackjack.blackjack.blackjack.gameSequence.enums.WagerType;
import com.blackjack.blackjack.blackjack.gameSequence.interfaces.UserPlayer;
import com.blackjack.blackjack.blackjack.displayText.Text;
import com.blackjack.blackjack.blackjack.cardsAndDecks.classes.PlayingCard;

import java.util.ArrayList;

public class Player implements UserPlayer {

    private String playerName = "User";
    private ArrayList<PlayingCard> hand;
    private ArrayList<PlayingCard> splitHand;
    private int credits = 100;
    private PayoutType payoutType;
    private int payoutAmount;
    private boolean busts = false;
    private boolean hasBlackjack = false;
    private ThePointsCalculator pointsCalculator;
    private int costOfInsurance;

    //Constructor
    public Player(ArrayList<PlayingCard> hand, ArrayList<PlayingCard> splitHand, ThePointsCalculator pointsCalculator) {
        this.hand = hand;
        this.splitHand = splitHand;
        this.pointsCalculator = pointsCalculator;
    }

    //Getters
    public String getPlayerName() {
        return playerName;
    }

    public ArrayList<PlayingCard> getHand() {
        return hand;
    }

    public int getCredits() {
        return credits;
    }

    public boolean getBusts() {
        return busts;
    }

    public int getPayoutAmount() {
        return payoutAmount;
    }

    public PayoutType getPayoutType() {
        return payoutType;
    }

    public boolean getHasBlackjack() {
        return hasBlackjack;
    }

    //Setters
    public void setCredits(int credits) {
        this.credits = credits;
    }

    public void setBusts(boolean busts) {
        this.busts = busts;
    }

    public void setPayoutAmount(int amount) {
        this.payoutAmount = amount;
    }

    public void setPayoutType(PayoutType type) {
        this.payoutType = type;
    }

    public void setHasBlackjack(boolean hasIt) {
        this.hasBlackjack = hasIt;
    }

    //Game Logic
    public MenuSelection gameSequence(MenuSelection menuSelection) {
        switch (menuSelection) {
            case Play:
                return MenuSelection.Play;
            case Instructions:
                System.out.println(Text.reset + Text.printInstructions);
                return MenuSelection.Instructions;
            case Exit:
                System.out.println(Text.thanks);
                return MenuSelection.Exit;
            default:
                System.out.println(Text.invalidEntryTryAgain);
                return MenuSelection.Instructions;
        }
    }

    public void playerHit(PlayingCard card) {
        getHand().add(card);
    }

    public boolean bustCheck(ArrayList<PlayingCard> hand) {
        return (pointsCalculator.calculatePoints(getHand()) > 21) ? true : false;
    }

    public boolean blackjackCheck(ArrayList<PlayingCard> hand) {
        if ((hand.get(0).getRank() == "A" && hand.get(1).pointValue() == 10 ||
                hand.get(1).getRank() == "A" && hand.get(0).pointValue() == 10) &&
                hand.size() == 2) {
            return true;
        }
        return false;
    }

    public void playerBlackjack() {
        if (blackjackCheck(getHand())) {
            setHasBlackjack(true);
        }
    }

    public void bustAction() {
        setBusts(true);
        displayBusts();
    }

    //Displays
    public void displayCredits() {
        System.out.println(Text.credits + getCredits() + "\n");
    }

    public void printCard(int elementToDraw) {
        Text.printDrawCard(getHand(), false, elementToDraw);
    }

    public void handScoreDisplay() {
        Text.printHand(getHand(), false);
        System.out.println(Text.reset + Text.printShowing(false, pointsCalculator.calculatePoints(getHand(), false)));
    }

    public void menuOptions() {
        if (getHand().size() > 2) {
            System.out.println(Text.playOptions);
        } else {
            System.out.println(Text.initialPlayOptions);
        }
    }

    public void displayBusts() {
        System.out.println(Text.reset + Text.playerBusts);
    }

    public void displayInsufficientCredits() {
        System.out.println(Text.insufficientCredits);
    }

    //Betting
    public boolean creditCheck(WagerType wagerType, int wager) {

        switch (wagerType) {
            case Initial:
                return (getCredits() - wager) >= 0 ? true : false;
            case DoubleDownOrSplit:
                return (getCredits() - wager) >= 0 ? true : false;
            default:
                return false;
        }

    }

    public void placeWager(WagerType wagerType, int wager) {
        switch (wagerType) {
            case Initial:
                setPayoutAmount(wager);
                setCredits(getCredits() - wager);
                break;
            case DoubleDownOrSplit:
                setPayoutAmount(getPayoutAmount() + wager);
                setCredits(getCredits() - wager);
                break;
//            case Insurance:
//                setCredits(getCredits() - wager);
//                break;
            default:
                break;
        }
    }

    //Play
    public void reusuableHit(PlayingCard card) {
        playerHit(card);
        Text.printDrawCard(getHand(), false, getHand().size() - 1);
        handScoreDisplay();
    }

    public boolean hit(PlayingCard card) {
        reusuableHit(card);
        if (bustCheck(getHand()) == true) {
            bustAction();
            return false;
        }
        return true;
    }

    public boolean stay() {
        return false;
    }

    public boolean doubleDown(PlayingCard card) {
        if (creditCheck(WagerType.Initial, getPayoutAmount())) {
            placeWager(WagerType.DoubleDownOrSplit, getPayoutAmount());
            reusuableHit(card);
            if (bustCheck(getHand()) == true) {
                bustAction();
            }
            return false;
        } else {
            displayInsufficientCredits();
            return true;
        }
    }

    public boolean splitPair() {
        if (getHand().size() == 2) {
            System.out.println("Split a pair");
            return true;
        } else {
            System.out.println(Text.invalidEntry);
            return true;
        }
    }

    public boolean purchaseInsurance() {
        System.out.println("Insurance");
        return true;
    }
}