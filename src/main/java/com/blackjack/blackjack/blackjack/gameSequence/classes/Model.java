package com.blackjack.blackjack.blackjack.gameSequence.classes;

import com.blackjack.blackjack.blackjack.gameSequence.enums.*;
import com.blackjack.blackjack.blackjack.displayText.Text;
import com.blackjack.blackjack.blackjack.cardsAndDecks.classes.PlayingCard;
import com.blackjack.blackjack.blackjack.cardsAndDecks.classes.CardFactory;
import java.util.ArrayList;
import java.util.Random;

public class Model {

    //Objects
    private Random random;
    private CardFactory cardFactory;
    private Player player;
    private StandardDealer dealer;
    private StandardGameDeck gameDeck;
    private Round round;
    private ThePointsCalculator pointsCalculator;

    //Constructor
    public Model(Random random, CardFactory cardFactory, Player player, StandardDealer dealer, StandardGameDeck gameDeck, Round round, ThePointsCalculator pointsCalculator) {
        this.random = random;
        this.cardFactory = cardFactory;
        this.player = player;
        this.dealer = dealer;
        this.gameDeck = gameDeck;
        this.round = round;
        this.pointsCalculator = pointsCalculator;
    }

    //Game Logic
    public boolean isNewRound() {
        return (round.getRound() == 0) ? true : false;
    }

    public void menu(boolean newGame) {
        Text.menu(newGame);
    }

    public MenuSelection gameSequence(MenuSelection menuSelection) {
        return player.gameSequence(menuSelection);
    }

    public void startNewRound() {
        gameDeck.setGameDeck(buildGameDeck());
        System.out.println(Text.dealingCards);
        initialDeal();
        printInitialDrawCards();
        System.out.println(Text.turn(false));
        player.handScoreDisplay();
        dealer.dealerHandScoreDisplay(false);
    }

    private ArrayList<PlayingCard> buildGameDeck() {
        return cardFactory.getDeckOfCards(gameDeck.deckType, gameDeck.numberOfDecks);
    }

    private void initialDeal() {
        //First PlayingCard
        player.playerHit(drawCard());
        dealer.dealerHit(drawCard());

        //Second PlayingCard
        player.playerHit(drawCard());
        dealer.dealerHit(drawCard());
    }

    private PlayingCard drawCard() {
        int elementToDraw = randomElementToDraw();
        PlayingCard playingCardDrawn = gameDeck.getGameDeck().get(elementToDraw);
        gameDeck.getGameDeck().remove(elementToDraw);
        return playingCardDrawn;
    }

    private int randomElementToDraw() {
        return random.nextInt(gameDeck.getGameDeck().size());
    }

    public void menuOptions() {
        player.menuOptions();
    }

    //Displays
    public boolean canContinuePlaying() {
        return player.getCredits() > 0 ? true : false;
    }

    public void insufficientCreditsToContinue() {
        System.out.println(Text.noCredits);
    }

    public void displayCredits() {
        player.displayCredits();
    }

    public void displayPlaceWager() {
        System.out.println(Text.placeBet);
    }

    public void insufficientCredits() {
        System.out.println(Text.insufficientCredits);
    }

    private void printInitialDrawCards() {
        player.printCard(0);
        dealer.printCard(0);
        player.printCard(1);
        dealer.printCard(1);
    }

    public void displayRoundResults() {
        System.out.println(Text.reset + Text.roundResults);
    }

    public void displayFinalScores() {
        if (player.getBusts()) {
            System.out.println(Text.reset + Text.playerBusted);
        } else {
            System.out.println(Text.reset + Text.finalScores(false, pointsCalculator.calculatePoints(player.getHand())));
        }

        if (dealer.getBusts()) {
            System.out.println(Text.reset + Text.dealerBusted + Text.line);
        } else {
            System.out.println(Text.reset + Text.finalScores(true, pointsCalculator.calculatePoints(dealer.getHand())) + Text.line);
        }
    }

    public void displayRoundWinner() {
        if (round.getWinner() == RoundWinner.Dealer) {
            System.out.println(Text.reset + Text.dealerWins + Text.line);
        } else if (round.getWinner() == RoundWinner.Player) {
            System.out.println(Text.reset + Text.playerWins + Text.line);
        } else if (round.getWinner() == RoundWinner.Dealer) {
            System.out.println(Text.reset + Text.dealerWins + Text.line);
        } else if (round.getWinner() == RoundWinner.Push) {
            System.out.println(Text.reset + Text.push + Text.line);
        } else {
            System.out.println(Text.reset + Text.playerWins + Text.line);
        }
    }

    private void displayPayout(int payoutAmount) {
        Text.displayPayout(payoutAmount);
    }

    //Players
    public boolean creditCheck(WagerType wagerType, int wager) {
        return player.creditCheck(wagerType, wager);
    }

    public void placeWager(WagerType wagerType, int wager) {
        player.placeWager(wagerType, wager);
    }

    public boolean playerHitStand(ThePlay selection) {
        switch (selection) {
            case Hit:
                return player.hit(drawCard());
            case Stay:
                return player.stay();
            case DoubleDown:
                return player.doubleDown(drawCard());
            case Split:
                return player.splitPair();
            case Insurance:
                return player.purchaseInsurance();
            default:
                System.out.println(Text.invalidEntry + Text.tryAgain);
        }
        return true;
    }

    //Dealer
    public void dealersTurn() {

        boolean dealersTurn = dealer.dealersMove();

        if (dealersTurn) {
            while (dealersTurn) {
                dealer.dealersTurn(drawCard());
                dealersTurn = dealer.dealersMove();
            }
        } else {
            dealer.endDealerTurn();
        }
    }

    //Round Winner
    public void determineRoundWinner() {
        if (player.getBusts()) {
            round.setWinner(RoundWinner.Dealer);
        } else if (dealer.getBusts()) {
            round.setWinner(RoundWinner.Player);
        } else if (pointsCalculator.calculatePoints(player.getHand()) < pointsCalculator.calculatePoints(dealer.getHand())) {
            round.setWinner(RoundWinner.Dealer);
        } else if (pointsCalculator.calculatePoints(player.getHand()) == pointsCalculator.calculatePoints(dealer.getHand())) {
            round.setWinner(RoundWinner.Push);
        } else {
            round.setWinner(RoundWinner.Player);
        }
    }

    //Payouts
    public void getPayoutAmount() {
        if (round.getWinner() == RoundWinner.Player || round.getWinner() == RoundWinner.Push) {
            int payoutAmount = determinePayoutAmount();
            displayPayout(payoutAmount);
            player.setCredits(player.getCredits() + payoutAmount);
        }
    }

    public void checkBlackjack() {
        player.playerBlackjack();
    }

    public void determinePayoutType() {
        if (player.getHasBlackjack()) {
            player.setPayoutType(PayoutType.ThreeToOne);
        } else if (round.getWinner() == RoundWinner.Dealer) {
            player.setPayoutType(PayoutType.Loss);
        } else if (round.getWinner() == RoundWinner.Player) {
            player.setPayoutType(PayoutType.OneToOne);
        } else if (round.getWinner() == RoundWinner.Push) {
            player.setPayoutType(PayoutType.Push);
        }
    }

    private int determinePayoutAmount() {

        if (player.getPayoutType() == PayoutType.Push) {
            return player.getPayoutAmount();
        } else if (player.getPayoutType() == PayoutType.OneToOne) {
            return player.getPayoutAmount() * 2;
        } else if (player.getPayoutType() == PayoutType.ThreeToOne) {
            return player.getPayoutAmount() * 3;
        }
        return 0;
    }

    //End Round Cleanup
    public void endRoundCleanup() {
        round.setRound(round.getRound() + 1);
        player.setBusts(false);
        dealer.setBusts(false);
        player.getHand().clear();
        dealer.getHand().clear();
        player.setPayoutAmount(0);
        round.setWinner(RoundWinner.Push);
        player.setHasBlackjack(false);
    }

}