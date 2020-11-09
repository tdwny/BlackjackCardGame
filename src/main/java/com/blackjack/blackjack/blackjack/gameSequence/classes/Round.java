package com.blackjack.blackjack.blackjack.gameSequence.classes;

import com.blackjack.blackjack.blackjack.gameSequence.enums.RoundWinner;

public class Round {

    int round = 0;
    RoundWinner winner;

    public Round() {
    }

    //Getters
    public int getRound() {
        return round;
    }

    public RoundWinner getWinner() {
        return winner;
    }

    //Setters
    public void setRound(int round) {
        this.round = round;
    }

    public void setWinner(RoundWinner winner) {
        this.winner = winner;
    }

}
