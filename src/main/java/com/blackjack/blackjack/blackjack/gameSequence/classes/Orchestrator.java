package com.blackjack.blackjack.blackjack.gameSequence.classes;

import com.blackjack.blackjack.blackjack.gameSequence.enums.MenuSelection;
import com.blackjack.blackjack.blackjack.gameSequence.enums.WagerType;
import com.blackjack.blackjack.blackjack.displayText.Text;
import java.util.InputMismatchException;
import java.util.Scanner;


public class Orchestrator {

    //Create objects
    Scanner scanner;
    Model model;
    UserInputMapping menuEnum;

    //Constructor
    public Orchestrator(Scanner scanner, Model model, UserInputMapping menuEnum) {
        this.scanner = scanner;
        this.model = model;
        this.menuEnum = menuEnum;
    }

    //Methods
    public void startGame() {
        gameSequence();
    }

    private void gameSequence() {

        boolean continuePlaying = true;

        while (continuePlaying) {

            if (setupNewGame()) {
                thePlay();
                theResults();
                if (!model.canContinuePlaying()) {
                    model.insufficientCreditsToContinue();
                    break;
                }
            } else {
                continuePlaying = false;
                scanner.close();
            }
        }
    }

    private boolean setupNewGame() {

        boolean continueSelection = true;
        boolean returnValue = true;

        if (model.isNewRound()) {
            model.menu(true);
        } else {
            model.menu(false);
        }

        while (continueSelection == true) {

            boolean allowContinue = false;
            int userInput = 0;

            while (!allowContinue){
                userInput = UserInputChecks.mainMenuCheck(scanner.next(), 1, 3);
                if (UserInputChecks.allowContinue(userInput)) {
                    allowContinue = true;
                }
            }

            MenuSelection modelReturn =  model.gameSequence(menuEnum.mainMenuSelection(userInput));

            switch (modelReturn) {
                case Play:
                    placeWager();
                    model.startNewRound();
                    continueSelection = false;
                    returnValue = true;
                    break;
                case Instructions:
                    continueSelection = true;
                    returnValue = true;
                    break;
                case Exit:
                    continueSelection = false;
                    returnValue = false;
                    break;
                default:
                    continueSelection = true;
                    break;
            }
        }
    return returnValue;
    }

    private void placeWager() {

        boolean initialWagerCheck = true;

        while (initialWagerCheck) {

            try {
                model.displayCredits();
                model.displayPlaceWager();

                boolean allowContinue = false;
                int userInput = 0;

                while (!allowContinue){
                    userInput = UserInputChecks.mainMenuCheck(scanner.next(), 0, Integer.MAX_VALUE);
                    if (UserInputChecks.allowContinue(userInput)) {
                        allowContinue = true;
                    }
                }

                if (model.creditCheck(WagerType.Initial, userInput)) {
                    model.placeWager(WagerType.Initial, userInput);
                    model.displayCredits();
                    initialWagerCheck = false;
                } else {
                    model.insufficientCredits();
                }
            } catch (InputMismatchException e) {
                System.out.println(Text.invalidEntry);
            }
        }
    }

    private void thePlay() {

        boolean continueRound = true;

        while (continueRound) {
            try {
                model.menuOptions();

                boolean allowContinue = false;
                int userInput = 0;

                while (!allowContinue){
                    userInput = UserInputChecks.mainMenuCheck(scanner.next(), 0, 5);
                    if (UserInputChecks.allowContinue(userInput)) {
                        allowContinue = true;
                    }
                }

                continueRound = model.playerHitStand(menuEnum.thePlaySelection(userInput));

            } catch (InputMismatchException e) {
                System.out.println(Text.invalidEntry);
            }
        }
        model.dealersTurn();
    }

    private void theResults() {
        model.determineRoundWinner();
        model.checkBlackjack();
        model.determinePayoutType();
        model.displayRoundResults();
        model.displayFinalScores();
        model.displayRoundWinner();
        model.getPayoutAmount();
        model.endRoundCleanup();
    }
}