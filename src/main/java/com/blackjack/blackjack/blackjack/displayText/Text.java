package com.blackjack.blackjack.blackjack.displayText;

import com.blackjack.blackjack.blackjack.cardsAndDecks.classes.PlayingCard;

import java.util.ArrayList;

public class Text {

    //Font
    public static final String reset = "\033[0m";  // Text Reset
    public static final String black = "\033[0;30m";   // black
    public static final String red = "\033[0;31m";     // red
    public static final String white = "\033[0;37m";   // white

    //MenuVariables
    public static String welcome = "Welcome to gameSequence \n";
    public static String thanks = "Thanks for playing";
    public static String menu = "Press 1 to Deal\nPress 2 to for Instructions \nPress 3 to Exit";

    //SettingsVariables
    public static String settingsMenu = "Press 1 to change how many decks used in the game\nPress 2 to reshuffle\nPress 3 to return to game";
    public static String decksInUse = "Decks in use: ";
    public static String changedecksInUse = "Enter number of decks to use and dealer will reshuffle:";

    //MiscellaneousVariables
    public static String invalidEntry = "Invalid Entry";
    public static String invalidEntryTryAgain = "Invalid Entry try again \n";
    public static String tryAgain = " try again \n";
    public static String points = " points";
    public static String line = "\n";
    public static String shuffling = "Shuffling...";

    //BettingVariables
    public static String credits = "Credits: ";
    public static String placeBet = "Enter your bet:";
    public static String insufficientCredits = "Insufficient credits \n";
    public static String noCredits = "No credits remaining. Better luck next time.";
    public static String payout = "Payout: ";

    //gameSequence GameVariables
    public static String dealerSecondCard = "Dealers second card is face down\n";
    public static String initialPlayOptions = "Press 1 to Hit\nPress 2 to Stay\nPress 3 to Double Down\nPress 4 to Split a Pair \nPress 5 to Purchase Insurance \n";
    public static String playOptions = "Press 1 to Hit\nPress 2 to Stay \n";
    public static String dealerHit = "Dealer has less than 17 points and must take a hit";
    public static String dealerStays = "Dealer stays \n";
    public static String dealingCards = "Dealing cards...\n";
    public static String insurancePurchased = "Insurance purchased";
    public static String insurancePaid = "Dealer had gameSequence. Insurance has been paid out.";
    public static String insuranceLost = "Dealer did not have gameSequence. Insurance lost.";
    public static String insuranceUnavailable = "Insurance unavailable";
    public static String splitUnavailable = "Cannot split";
    public static String playerBusts = "User busts \n";
    public static String dealerBusts = "Dealer busts \n";
    public static String playerBusted = "User busted";
    public static String dealerBusted = "Dealer busted";
    public static String roundResults = "Round Results:";
    public static String playerWins = "User Wins";
    public static String dealerWins = "Dealer Wins";
    public static String push = "Push";
    public static String printInstructions = "Learn the game on your own time\n";
    public static String cannotDoubleDown = "Cannot Double Down. See Instructions\n";
    public static String dealerBlackjack = "Dealer has gameSequence\n";
    public static String dealerNoBlackjack = "Dealer does not have gameSequence\n";


    //Methods
    public static void menu(boolean displayWelcome){

        if (displayWelcome){
            System.out.println(Text.reset + Text.welcome + Text.menu);
        } else {
            System.out.println(Text.reset + Text.menu);
        }

    }

    public static void printDrawCard(ArrayList hand, boolean isDealer, int elementToPrint) {

        String playerDealerForPrint;

        if (isDealer) {
            playerDealerForPrint = "Dealer drew a ";
        } else {
            playerDealerForPrint = "User drew a ";
        }

        //ArrayLists for Method
        ArrayList<PlayingCard> printHand = hand;

        //Print PlayingCard
        System.out.println(Text.reset + playerDealerForPrint + printHand.get(elementToPrint).displayCard());
    }

    public static String turn(boolean isDealer) {

        if (isDealer) {
            return "Dealers turn...\n";
        } else {
            return "Players turn...\n";
        }
    }

    public static void printHand(ArrayList hand, boolean isDealer, boolean displayAll) {

        String playerDealerForPrint;

        if (isDealer) {
            playerDealerForPrint = "Dealer's Hand: ";
        } else {
            playerDealerForPrint = "User's Hand: ";
        }

        //ArrayLists for Method
        ArrayList<PlayingCard> loopHand = hand;
        ArrayList<String> displayHand = new ArrayList<>();

        //Set numOfCardsToDisplay
        int numOfCardsToDisplay = 1;

        if (isDealer && displayAll || !isDealer && displayAll) {
            numOfCardsToDisplay = loopHand.size();
        }

        //Add Cards to displayHand
        for (int i = 0; i < numOfCardsToDisplay; i++) {
            displayHand.add(loopHand.get(i).displayCard());
        }

        //Print Hand
        if (numOfCardsToDisplay == 1) {
            System.out.println(Text.reset + playerDealerForPrint + displayHand.get(0) + reset + ", [face down]");
        } else {
            System.out.println(Text.reset + playerDealerForPrint + displayHand.toString().substring(1, displayHand.toString().length() - 1));
        }

    }

    public static void printHand(ArrayList hand, boolean isDealer) {
        printHand(hand, isDealer, true);
    }

    public static String printShowing(boolean isDealer, int points) {

        if (isDealer) {
            return "Dealer is showing " + points + " points \n";
        } else {
            return "User is showing " + points + " points \n";
        }
    }

    public static String finalScores(boolean isDealer, int score) {

        if (isDealer) {
            return "Dealer's score was " + score;
        } else {
            return "User's score was " + score;
        }
    }

    public static void displayPayout(int payoutAmount){
        System.out.println(Text.payout + payoutAmount + Text.line);
    }



}