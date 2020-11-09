package com.blackjack.blackjack.blackjack.gameSequence.classes;

import com.blackjack.blackjack.blackjack.gameSequence.enums.MenuSelection;
import com.blackjack.blackjack.blackjack.gameSequence.enums.ThePlay;
import com.blackjack.blackjack.blackjack.gameSequence.interfaces.InputMapping;

import java.util.HashMap;

public class UserInputMapping implements InputMapping {

    HashMap<Integer, MenuSelection> mainMenuSelectionMap;
    HashMap<Integer, ThePlay> thePlaySelectionMap;

    public UserInputMapping(HashMap<Integer, MenuSelection> hashMap1, HashMap<Integer, ThePlay> hashMap2) {
        this.mainMenuSelectionMap = hashMap1;
        this.thePlaySelectionMap = hashMap2;
    }

    public MenuSelection mainMenuSelection(int menuSelection){

        mainMenuSelectionMap.put(1, MenuSelection.Play);
        mainMenuSelectionMap.put(2, MenuSelection.Instructions);
        mainMenuSelectionMap.put(3, MenuSelection.Exit);

        return mainMenuSelectionMap.get(menuSelection);

    }

    public ThePlay thePlaySelection(int menuSelection){

        thePlaySelectionMap.put(1, ThePlay.Hit);
        thePlaySelectionMap.put(2, ThePlay.Stay);
        thePlaySelectionMap.put(3, ThePlay.DoubleDown);
        thePlaySelectionMap.put(4, ThePlay.Split);
        thePlaySelectionMap.put(5, ThePlay.Insurance);

        return thePlaySelectionMap.get(menuSelection);

    }

}
