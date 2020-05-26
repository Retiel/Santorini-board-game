package it.polimi.ingsw.PSP33.view.cli;

import it.polimi.ingsw.PSP33.model.God;
import it.polimi.ingsw.PSP33.model.light_version.LightModel;
import it.polimi.ingsw.PSP33.model.light_version.LightPlayer;
import it.polimi.ingsw.PSP33.utils.Coord;

import java.util.List;

/**
 * Class which permit to print all the necessary information for the player
 * (Map and lists that contain the possible choices)
 */
public class CLIPrinter {

    private StringBuilder floorInfo = new StringBuilder(50);
    private StringBuilder pawnInfo = new StringBuilder(50);
    private StringBuilder playersInfo = new StringBuilder(50);


    /**
     * class that print all the info in the Board
     * @param board the light Board of the client
     */
    public void printBoard(LightModel board){
        String separatorSx = "|  ";
        String centralSep = " |  ";
        String separatorDx = " |";

        for(LightPlayer lp : board.getPlayers()){
            playersInfo.append(lp.getColor().toString()).append(" = ").append(lp.getName()+"  ");
        }
        System.out.println(playersInfo);
        playersInfo.setLength(0);
        for(int j=0; j<=4;j++) {
            floorInfo.append("| ");
            pawnInfo.append(separatorSx);
            for (int i = 0; i < 4; i++) {
                if (board.getLightGrid()[j][i].isRoof()) {
                    floorInfo.append("* ").append(board.getLightGrid()[j][i].getFloor()).append(" | ");
                } else floorInfo.append("  ").append(board.getLightGrid()[j][i].getFloor()).append(" | ");
                if (board.getLightGrid()[j][i].getOccupied() == null) {
                    pawnInfo.append("  " + centralSep);
                } else
                    pawnInfo.append(board.getLightGrid()[j][i].getOccupied().toString() + board.getLightGrid()[j][i].getOccupied().getNumber() + centralSep);
            }
            if (board.getLightGrid()[j][4].isRoof()) {
                floorInfo.append("* ").append(board.getLightGrid()[j][4].getFloor()).append(separatorDx);
            } else floorInfo.append("  ").append(board.getLightGrid()[j][4].getFloor()).append(separatorDx);
            if (board.getLightGrid()[j][4].getOccupied() == null) {
                pawnInfo.append("  " + separatorDx);
            } else
                pawnInfo.append(board.getLightGrid()[j][4].getOccupied().toString() + board.getLightGrid()[j][4].getOccupied().getNumber() + separatorDx);
            System.out.println("+ - - + - - + - - + - - + - - +");
            System.out.println(floorInfo.toString());
            System.out.println(pawnInfo.toString());
            pawnInfo.setLength(0);
            floorInfo.setLength(0);

        }
        System.out.println("+ - - + - - + - - + - - + - - +");
    }

    /**
     * method that prints the choices from number 1
     * @param choices the coordinates of possible moves
     */
    public void printList(List<Coord> choices){

        int counter = 1;
        System.out.println("Default actions:");
        for (Coord c : choices) {
            System.out.print(counter+") ");
            System.out.print(c.toString()+"\t");
            counter++;
            if((counter-1)%5 == 0){
                System.out.print("\n");
            }
        }
        if((counter-1)%5 != 0){
            System.out.print("\n");
        }
    }

    /**
     * method that prints the other possible choices continuing from the last number (previous list length)
     * @param choices the coordinates of possible moves
     * @param index the lenght of the previous list
     */
    public void printSecondList(List<Coord> choices, int index) {

        if (choices.size() > 0) {

            System.out.println("God Action:");
            for (Coord c : choices) {
                index++;
                System.out.print(index + ") ");
                System.out.print(c.toString() + "\t");
                if ((index) % 5 == 0) {
                    System.out.print("\n");
                }
            }
            if ((index - 1) % 5 != 0) {
                System.out.print("\n");
            }
        }
    }

    /**
     * method that prints the possible choices for the setup of the god cards
     * @param gods the list of gods from which the player can choose
     */
    public void printGodList(List<God> gods){
        int counter = 1;
        for(God god : gods){
            System.out.println(counter+") "+god.toString());
            counter++;
        }
    }
}
