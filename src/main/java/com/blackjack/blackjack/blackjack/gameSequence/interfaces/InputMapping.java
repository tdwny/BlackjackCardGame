package com.blackjack.blackjack.blackjack.gameSequence.interfaces;

import com.blackjack.blackjack.blackjack.gameSequence.enums.MenuSelection;
import com.blackjack.blackjack.blackjack.gameSequence.enums.ThePlay;

public interface InputMapping {

    MenuSelection mainMenuSelection(int menuSelection);
    ThePlay thePlaySelection(int menuSelection);

}
