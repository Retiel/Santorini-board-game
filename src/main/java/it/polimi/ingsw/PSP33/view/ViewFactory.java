package it.polimi.ingsw.PSP33.view;

import it.polimi.ingsw.PSP33.view.cli.CLI;

public class ViewFactory {

    public static AbstractView getView(int selection) {
        switch (selection) {
            case 1:
                return new CLI(); //CLI

            case 2:
                return null; //GUI

            default:
                return new CLI();
        }
    }
}
