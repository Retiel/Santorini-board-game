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
        String border = "+ - - - ";
        String empty = "|       ";
        String separatorSx = "|   ";
        String centralSep = "  |   ";
        String separatorDx = "  |";

        //read First Line info and print
        floorInfo.append("| ");
        pawnInfo.append(separatorSx);
        for(int i=0; i<4; i++){
            if(board.getGrid()[0][i].isRoof()){
                floorInfo.append("* ").append(board.getGrid()[0][i].getFloor()).append("   | ");
            }
            else floorInfo.append("  ").append(board.getGrid()[0][i].getFloor()).append("   | ");
            if(board.getGrid()[0][i].getOccupied() == null){
                pawnInfo.append("  " + centralSep);
            } else pawnInfo.append(board.getGrid()[0][i].getOccupied().toString() + board.getGrid()[0][i].getOccupied().getNumber() + centralSep);
        }
        if(board.getGrid()[0][4].isRoof()){
            floorInfo.append("* ").append(board.getGrid()[0][4].getFloor()).append(" ").append(separatorDx);
        }
        else floorInfo.append("  ").append(board.getGrid()[0][4].getFloor()).append(" ").append(separatorDx);
        if(board.getGrid()[0][4].getOccupied() == null){
            pawnInfo.append("  " + separatorDx);
        } else pawnInfo.append(board.getGrid()[0][4].getOccupied().toString() + board.getGrid()[0][4].getOccupied().getNumber() + separatorDx);
        System.out.println(border + border + border + border + border + "+");
        System.out.println(floorInfo.toString());
        System.out.println(pawnInfo.toString());
        System.out.println(empty + empty + empty + empty + empty + "|");
        pawnInfo.setLength(0);
        floorInfo.setLength(0);

        //read Second Line info and print

        floorInfo.append("| ");
        pawnInfo.append(separatorSx);
        for(int i=0; i<4; i++){
            if(board.getGrid()[1][i].isRoof()){
                floorInfo.append("* ").append(board.getGrid()[1][i].getFloor()).append("   | ");
            }
            else floorInfo.append("  ").append(board.getGrid()[1][i].getFloor()).append("   | ");
            if(board.getGrid()[1][i].getOccupied() == null){
                pawnInfo.append("  " + centralSep);
            } else pawnInfo.append(board.getGrid()[1][i].getOccupied().toString() + board.getGrid()[1][i].getOccupied().getNumber() + centralSep);
        }
        if(board.getGrid()[1][4].isRoof()){
            floorInfo.append("* ").append(board.getGrid()[1][4].getFloor()).append(" ").append(separatorDx);
        }
        else floorInfo.append("  ").append(board.getGrid()[1][4].getFloor()).append(" ").append(separatorDx);
        if(board.getGrid()[1][4].getOccupied() == null){
            pawnInfo.append("  " + separatorDx);
        } else pawnInfo.append(board.getGrid()[1][4].getOccupied().toString() + board.getGrid()[0][4].getOccupied().getNumber() + separatorDx);
        System.out.println(border + border + border + border + border + "+");
        System.out.println(floorInfo.toString());
        System.out.println(pawnInfo.toString());
        System.out.println(empty + empty + empty + empty + empty + "|");
        pawnInfo.setLength(0);
        floorInfo.setLength(0);

        //read Third Line info and print

        floorInfo.append("| ");
        pawnInfo.append(separatorSx);
        for(int i=0; i<4; i++){
            if(board.getGrid()[2][i].isRoof()){
                floorInfo.append("* ").append(board.getGrid()[2][i].getFloor()).append("   | ");
            }
            else floorInfo.append("  ").append(board.getGrid()[2][i].getFloor()).append("   | ");
            if(board.getGrid()[2][i].getOccupied() == null){
                pawnInfo.append("  " + centralSep);
            } else pawnInfo.append(board.getGrid()[2][i].getOccupied().toString() + board.getGrid()[0][i].getOccupied().getNumber() + centralSep);
        }
        if(board.getGrid()[2][4].isRoof()){
            floorInfo.append("* ").append(board.getGrid()[2][4].getFloor()).append(" ").append(separatorDx);
        }
        else floorInfo.append("  ").append(board.getGrid()[2][4].getFloor()).append(" ").append(separatorDx);
        if(board.getGrid()[2][4].getOccupied() == null){
            pawnInfo.append("  " + separatorDx);
        } else pawnInfo.append(board.getGrid()[2][4].getOccupied().toString() + board.getGrid()[0][4].getOccupied().getNumber() + separatorDx);

        System.out.println(border + border + border + border + border + "+");
        System.out.println(floorInfo.toString());
        System.out.println(pawnInfo.toString());
        System.out.println(empty + empty + empty + empty + empty + "|");
        pawnInfo.setLength(0);
        floorInfo.setLength(0);

        //read Fourth Line info and print

        floorInfo.append("| ");
        pawnInfo.append(separatorSx);
        for(int i=0; i<4; i++){
            if(board.getGrid()[3][i].isRoof()){
                floorInfo.append("* ").append(board.getGrid()[3][i].getFloor()).append("   | ");
            }
            else floorInfo.append("  ").append(board.getGrid()[3][i].getFloor()).append("   | ");
            if(board.getGrid()[3][i].getOccupied() == null){
                pawnInfo.append("  " + centralSep);
            } else pawnInfo.append(board.getGrid()[3][i].getOccupied().toString() + board.getGrid()[0][i].getOccupied().getNumber() + centralSep);
        }
        if(board.getGrid()[3][4].isRoof()){
            floorInfo.append("* ").append(board.getGrid()[3][4].getFloor()).append(" ").append(separatorDx);
        }
        else floorInfo.append("  ").append(board.getGrid()[3][4].getFloor()).append(" ").append(separatorDx);
        if(board.getGrid()[3][4].getOccupied() == null){
            pawnInfo.append("  " + separatorDx);
        } else pawnInfo.append(board.getGrid()[3][4].getOccupied().toString() + board.getGrid()[0][4].getOccupied().getNumber() + separatorDx);

        System.out.println(border + border + border + border + border + "+");
        System.out.println(floorInfo.toString());
        System.out.println(pawnInfo.toString());
        System.out.println(empty + empty + empty + empty + empty + "|");
        pawnInfo.setLength(0);
        floorInfo.setLength(0);

        //read Fifth Line info and print

        floorInfo.append("| ");
        pawnInfo.append(separatorSx);
        for(int i=0; i<4; i++){
            if(board.getGrid()[4][i].isRoof()){
                floorInfo.append("* ").append(board.getGrid()[4][i].getFloor()).append("   | ");
            }
            else floorInfo.append("  ").append(board.getGrid()[4][i].getFloor()).append("   | ");
            if(board.getGrid()[4][i].getOccupied() == null){
                pawnInfo.append("  " + centralSep);
            } else pawnInfo.append(board.getGrid()[4][i].getOccupied().toString() + board.getGrid()[0][i].getOccupied().getNumber() + centralSep);
        }
        if(board.getGrid()[4][4].isRoof()){
            floorInfo.append("* ").append(board.getGrid()[4][4].getFloor()).append(" ").append(separatorDx);
        }
        else floorInfo.append("  ").append(board.getGrid()[4][4].getFloor()).append(" ").append(separatorDx);
        if(board.getGrid()[4][4].getOccupied() == null){
            pawnInfo.append("  " + separatorDx);
        } else pawnInfo.append(board.getGrid()[4][4].getOccupied().toString() + board.getGrid()[0][4].getOccupied().getNumber() + separatorDx);

        System.out.println(border + border + border + border + border + "+");
        System.out.println(floorInfo.toString());
        System.out.println(pawnInfo.toString());
        System.out.println(empty + empty + empty + empty + empty + "|");
        System.out.println(border + border + border + border + border + "+");
        pawnInfo.setLength(0);
        floorInfo.setLength(0);

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
            System.out.print("\n"+counter+") "+god.toString());
            counter++;
        }
    }
}
