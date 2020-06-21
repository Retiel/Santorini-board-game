package it.polimi.ingsw.PSP33.view;

import it.polimi.ingsw.PSP33.view.cli.CLI;
import it.polimi.ingsw.PSP33.view.gui.GUI;

public class ViewFactory {

    public static AbstractView getView(int selection) {
        switch (selection) {
            case 1:
                return new CLI(); //CLI

            case 2:
                return new GUI(); //GUI

            default:
                return new CLI();
        }
    }
}
