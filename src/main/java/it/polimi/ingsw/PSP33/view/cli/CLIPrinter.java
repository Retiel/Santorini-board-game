package it.polimi.ingsw.PSP33.view.cli;

import it.polimi.ingsw.PSP33.model.God;
import it.polimi.ingsw.PSP33.model.light_version.LightBoard;
import it.polimi.ingsw.PSP33.utils.Coord;

import java.util.List;

/**
 * Class which permit to print all the necessary information for the player (Map and lists that contain the possible choices)
 */
public class CLIPrinter {

    private StringBuilder floorInfo = new StringBuilder(50);
    private StringBuilder pawnInfo = new StringBuilder(50);

    /**
     * class that print all the info in the Board
     * @param board the light Board of the client
     */
    public void printBoard(LightBoard board){
        String separatorSx = "|   ";
        String centralSep = "  |   ";
        String separatorDx = "  |";

        for(int j=0; j<=4;j++) {
            floorInfo.append("| ");
            pawnInfo.append(separatorSx);
            for (int i = 0; i < 4; i++) {
                if (board.getGrid()[j][i].isRoof()) {
                    floorInfo.append("* ").append(board.getGrid()[j][i].getFloor()).append("   | ");
                } else floorInfo.append("  ").append(board.getGrid()[j][i].getFloor()).append("   | ");
                if (board.getGrid()[j][i].getOccupied() == null) {
                    pawnInfo.append("  " + centralSep);
                } else
                    pawnInfo.append(board.getGrid()[j][i].getOccupied().toString() + board.getGrid()[j][i].getOccupied().getNumber() + centralSep);
            }
            if (board.getGrid()[j][4].isRoof()) {
                floorInfo.append("* ").append(board.getGrid()[j][4].getFloor()).append(" ").append(separatorDx);
            } else floorInfo.append("  ").append(board.getGrid()[j][4].getFloor()).append(" ").append(separatorDx);
            if (board.getGrid()[j][4].getOccupied() == null) {
                pawnInfo.append("  " + separatorDx);
            } else
                pawnInfo.append(board.getGrid()[j][4].getOccupied().toString() + board.getGrid()[j][4].getOccupied().getNumber() + separatorDx);
            System.out.println("+ - - - + - - - + - - - + - - - + - - - +");
            System.out.println(floorInfo.toString());
            System.out.println(pawnInfo.toString());
            System.out.println("|       |       |       |       |       |");
            pawnInfo.setLength(0);
            floorInfo.setLength(0);

        }
        System.out.println("+ - - - + - - - + - - - + - - - + - - - +");
    }

    /**
     * method that prints the choices from number 1
     * @param choices the coordinates of possible moves
     */
    public void printList(List<Coord> choices){

        int counter = 1;
        for (Coord c : choices) {
            System.out.print(counter+") ");
            System.out.print(choices.get(counter-1).toString()+"\n");
            counter++;
        }
    }

    /**
     * method that prints the other possible choices continuing from the last number (previous list length)
     * @param choices the coordinates of possible moves
     * @param index the lenght of the previous list
     */
    public void printSecondList(List<Coord> choices, int index){
        int counter = 0;
        for (Coord c : choices) {
            index++;
            System.out.print(index+") ");
            System.out.print(choices.get(counter).toString()+"\n");
            counter++;
        }
    }

    /**
     *
     */
    public void printGodList(List<God> gods){
        int counter = 1;
        for(God god : gods){
            System.out.print(counter+") "+god.toString()+"\n");
            counter++;
        }
    }
}
