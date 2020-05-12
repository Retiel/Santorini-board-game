package it.polimi.ingsw.PSP33.view.cli;

import it.polimi.ingsw.PSP33.model.Board;
import it.polimi.ingsw.PSP33.model.God;
import it.polimi.ingsw.PSP33.model.light_version.LightBoard;
import it.polimi.ingsw.PSP33.utils.Coord;
import it.polimi.ingsw.PSP33.utils.enums.Gods;

import java.util.ArrayList;
import java.util.List;

/**
 * Class which permit to print all the necessary information for the player (Map and lists that contain the possible choices)
 */
public class CLIPrinter {

    private StringBuilder floorInfo = new StringBuilder(50);
    private StringBuilder pawnInfo = new StringBuilder(50);

    /**
     * class that print all the info in the Board
     * @param grid the light Board of the client
     */
    public void printBoard(LightBoard grid){
        String border = "+ - - - ";
        String empty = "|       ";
        String separatorSx = "|   ";
        String centralSep = "  |   ";
        String separatorDx = "  |";

        //read First Line info and print
        floorInfo.append("| ");
        pawnInfo.append(separatorSx);
        for(int i=0; i<4; i++){
            if(grid.getGrid()[0][i].isRoof()){
                floorInfo.append("* ").append(grid.getGrid()[0][i].getFloor()).append("   | ");
            }
            else floorInfo.append("  ").append(grid.getGrid()[0][i].getFloor()).append("   | ");
            if(grid.getGrid()[0][i].getOccupied() == null){
                pawnInfo.append("  " + centralSep);
            } else pawnInfo.append(grid.getGrid()[0][i].getOccupied().toString() + grid.getGrid()[0][i].getOccupied().getNumber() + centralSep);
        }
        if(grid.getGrid()[0][4].isRoof()){
            floorInfo.append("* ").append(grid.getGrid()[0][4].getFloor()).append(" ").append(separatorDx);
        }
        else floorInfo.append("  ").append(grid.getGrid()[0][4].getFloor()).append(" ").append(separatorDx);
        if(grid.getGrid()[0][4].getOccupied() == null){
            pawnInfo.append("  " + separatorDx);
        } else pawnInfo.append(grid.getGrid()[0][4].getOccupied().toString() + grid.getGrid()[0][4].getOccupied().getNumber() + separatorDx);
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
            if(grid.getGrid()[1][i].isRoof()){
                floorInfo.append("* ").append(grid.getGrid()[1][i].getFloor()).append("   | ");
            }
            else floorInfo.append("  ").append(grid.getGrid()[1][i].getFloor()).append("   | ");
            if(grid.getGrid()[1][i].getOccupied() == null){
                pawnInfo.append("  " + centralSep);
            } else pawnInfo.append(grid.getGrid()[1][i].getOccupied().toString() + grid.getGrid()[0][i].getOccupied().getNumber() + centralSep);
        }
        if(grid.getGrid()[1][4].isRoof()){
            floorInfo.append("* ").append(grid.getGrid()[1][4].getFloor()).append(" ").append(separatorDx);
        }
        else floorInfo.append("  ").append(grid.getGrid()[1][4].getFloor()).append(" ").append(separatorDx);
        if(grid.getGrid()[1][4].getOccupied() == null){
            pawnInfo.append("  " + separatorDx);
        } else pawnInfo.append(grid.getGrid()[1][4].getOccupied().toString() + grid.getGrid()[0][4].getOccupied().getNumber() + separatorDx);
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
            if(grid.getGrid()[2][i].isRoof()){
                floorInfo.append("* ").append(grid.getGrid()[2][i].getFloor()).append("   | ");
            }
            else floorInfo.append("  ").append(grid.getGrid()[2][i].getFloor()).append("   | ");
            if(grid.getGrid()[2][i].getOccupied() == null){
                pawnInfo.append("  " + centralSep);
            } else pawnInfo.append(grid.getGrid()[2][i].getOccupied().toString() + grid.getGrid()[0][i].getOccupied().getNumber() + centralSep);
        }
        if(grid.getGrid()[2][4].isRoof()){
            floorInfo.append("* ").append(grid.getGrid()[2][4].getFloor()).append(" ").append(separatorDx);
        }
        else floorInfo.append("  ").append(grid.getGrid()[2][4].getFloor()).append(" ").append(separatorDx);
        if(grid.getGrid()[2][4].getOccupied() == null){
            pawnInfo.append("  " + separatorDx);
        } else pawnInfo.append(grid.getGrid()[2][4].getOccupied().toString() + grid.getGrid()[0][4].getOccupied().getNumber() + separatorDx);

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
            if(grid.getGrid()[3][i].isRoof()){
                floorInfo.append("* ").append(grid.getGrid()[3][i].getFloor()).append("   | ");
            }
            else floorInfo.append("  ").append(grid.getGrid()[3][i].getFloor()).append("   | ");
            if(grid.getGrid()[3][i].getOccupied() == null){
                pawnInfo.append("  " + centralSep);
            } else pawnInfo.append(grid.getGrid()[3][i].getOccupied().toString() + grid.getGrid()[0][i].getOccupied().getNumber() + centralSep);
        }
        if(grid.getGrid()[3][4].isRoof()){
            floorInfo.append("* ").append(grid.getGrid()[3][4].getFloor()).append(" ").append(separatorDx);
        }
        else floorInfo.append("  ").append(grid.getGrid()[3][4].getFloor()).append(" ").append(separatorDx);
        if(grid.getGrid()[3][4].getOccupied() == null){
            pawnInfo.append("  " + separatorDx);
        } else pawnInfo.append(grid.getGrid()[3][4].getOccupied().toString() + grid.getGrid()[0][4].getOccupied().getNumber() + separatorDx);

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
            if(grid.getGrid()[4][i].isRoof()){
                floorInfo.append("* ").append(grid.getGrid()[4][i].getFloor()).append("   | ");
            }
            else floorInfo.append("  ").append(grid.getGrid()[4][i].getFloor()).append("   | ");
            if(grid.getGrid()[4][i].getOccupied() == null){
                pawnInfo.append("  " + centralSep);
            } else pawnInfo.append(grid.getGrid()[4][i].getOccupied().toString() + grid.getGrid()[0][i].getOccupied().getNumber() + centralSep);
        }
        if(grid.getGrid()[4][4].isRoof()){
            floorInfo.append("* ").append(grid.getGrid()[4][4].getFloor()).append(" ").append(separatorDx);
        }
        else floorInfo.append("  ").append(grid.getGrid()[4][4].getFloor()).append(" ").append(separatorDx);
        if(grid.getGrid()[4][4].getOccupied() == null){
            pawnInfo.append("  " + separatorDx);
        } else pawnInfo.append(grid.getGrid()[4][4].getOccupied().toString() + grid.getGrid()[0][4].getOccupied().getNumber() + separatorDx);

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
        for (Coord c : choices) {
            int counter = 1;
            System.out.println(counter+") ");
            System.out.println(choices.get(counter-1).toString()+"\n");
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
            System.out.println(index+") ");
            System.out.println(choices.get(counter).toString()+"\n");
            counter++;
        }
    }

    /**
     *
     */
    public void printGodList(List<God> gods){
        int counter = 1;
        for(God god : gods){
            System.out.println("\n"+counter+") "+god.toString()+"\n");
        }
    }
}
