//package com.blackjack.blackjack.blackjack.company;
//
//import com.blackjack.blackjack.blackjack.gameSequence.classes.*;
//import com.blackjack.blackjack.blackjack.cardsAndDecks.classes.CardFactory;
//import com.blackjack.blackjack.blackjack.pointCalculation.classes.AceHighOrLow;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.Random;
//import java.util.Scanner;
//
//public class Main {
//
//    public static void main(String[] args) {
//        Orchestrator controller = new Orchestrator(new Scanner(System.in), new Model(new Random(), new CardFactory(), new Player(new ArrayList<>(), new ArrayList<>(), new ThePointsCalculator(new AceHighOrLow())), new StandardDealer(new ArrayList<>(), new ThePointsCalculator(new AceHighOrLow())), new StandardGameDeck(new ArrayList<>()), new Round(), new ThePointsCalculator(new AceHighOrLow())), new UserInputMapping(new HashMap<>(), new HashMap<>()));
//        controller.startGame();
//
//    }
//}