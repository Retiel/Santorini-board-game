package it.polimi.ingsw.PSP33.view;

import it.polimi.ingsw.PSP33.view.cli.CLI;

public class ViewFactory {

    public static View getView(int selection) {
        switch (selection) {
            case 1:
                return new View(); //CLI

            case 2:
                return null; //GUI

            default:
                return new View();
        }
    }
}
