package it.polimi.ingsw.PSP33.view.cli;

import it.polimi.ingsw.PSP33.model.Board;
import it.polimi.ingsw.PSP33.utils.Coord;

import java.util.ArrayList;

/**
 * Class which permit to print all the necessary information for the player (Map and lists that contain the possible choices)
 */
public class CLIPrinter {

    private StringBuilder floorInfo = new StringBuilder(50);
    private StringBuilder pawnInfo = new StringBuilder(50);

    public void printBoard(Board grid){
        String border = "+ - - - ";
        String empty = "|       ";
        String separatorSx = "|   ";
        String centralSep = "   |   ";
        String separatorDx = "   |";

        //read First Line info and print
        floorInfo.append("| ");
        pawnInfo.append(separatorSx);
        for(int i=0; i<4; i++){
            if(grid.getGrid()[0][i].isRoof()){
                floorInfo.append("* ").append(grid.getGrid()[0][i].getFloor()).append("   | ");
            }
            else floorInfo.append("  ").append(grid.getGrid()[0][i].getFloor()).append("   | ");
            if(grid.getGrid()[0][i].getOccupied() == null){
                pawnInfo.append(" " + centralSep);
            } else pawnInfo.append(grid.getGrid()[0][i].getOccupied().toString() + centralSep);
        }
        if(grid.getGrid()[0][4].isRoof()){
            floorInfo.append("* ").append(grid.getGrid()[0][4].getFloor()).append(separatorDx);
        }
        else floorInfo.append("  ").append(grid.getGrid()[0][4].getFloor()).append(separatorDx);
        if(grid.getGrid()[0][4].getOccupied() == null){
            pawnInfo.append(" " + separatorDx);
        } else pawnInfo.append(grid.getGrid()[0][4].getOccupied().toString() + separatorDx);
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
                pawnInfo.append(" " + centralSep);
            } else pawnInfo.append(grid.getGrid()[1][i].getOccupied().toString() + centralSep);
        }
        if(grid.getGrid()[1][4].isRoof()){
            floorInfo.append("* ").append(grid.getGrid()[1][4].getFloor()).append(separatorDx);
        }
        else floorInfo.append("  ").append(grid.getGrid()[1][4].getFloor()).append(separatorDx);
        if(grid.getGrid()[1][4].getOccupied() == null){
            pawnInfo.append(" " + separatorDx);
        } else pawnInfo.append(grid.getGrid()[1][4].getOccupied().toString() + separatorDx);
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
                pawnInfo.append(" " + centralSep);
            } else pawnInfo.append(grid.getGrid()[2][i].getOccupied().toString() + centralSep);
        }
        if(grid.getGrid()[2][4].isRoof()){
            floorInfo.append("* ").append(grid.getGrid()[2][4].getFloor()).append(separatorDx);
        }
        else floorInfo.append("  ").append(grid.getGrid()[2][4].getFloor()).append(separatorDx);
        if(grid.getGrid()[2][4].getOccupied() == null){
            pawnInfo.append(" " + separatorDx);
        } else pawnInfo.append(grid.getGrid()[2][4].getOccupied().toString() + separatorDx);

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
                pawnInfo.append(" " + centralSep);
            } else pawnInfo.append(grid.getGrid()[3][i].getOccupied().toString() + centralSep);
        }
        if(grid.getGrid()[3][4].isRoof()){
            floorInfo.append("* ").append(grid.getGrid()[3][4].getFloor()).append(separatorDx);
        }
        else floorInfo.append("  ").append(grid.getGrid()[3][4].getFloor()).append(separatorDx);
        if(grid.getGrid()[3][4].getOccupied() == null){
            pawnInfo.append(" " + separatorDx);
        } else pawnInfo.append(grid.getGrid()[3][4].getOccupied().toString() + separatorDx);

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
                pawnInfo.append("P" + centralSep);
            } else pawnInfo.append(grid.getGrid()[4][i].getOccupied().toString() + centralSep);
        }
        if(grid.getGrid()[4][4].isRoof()){
            floorInfo.append("* ").append(grid.getGrid()[4][4].getFloor()).append(separatorDx);
        }
        else floorInfo.append("  ").append(grid.getGrid()[4][4].getFloor()).append(separatorDx);
        if(grid.getGrid()[4][4].getOccupied() == null){
            pawnInfo.append("P" + separatorDx);
        } else pawnInfo.append(grid.getGrid()[4][4].getOccupied().toString() + separatorDx);

        System.out.println(border + border + border + border + border + "+");
        System.out.println(floorInfo.toString());
        System.out.println(pawnInfo.toString());
        System.out.println(empty + empty + empty + empty + empty + "|");
        System.out.println(border + border + border + border + border + "+");
        pawnInfo.setLength(0);
        floorInfo.setLength(0);

    }

    public void printList(ArrayList<Coord> choices){
        for (Coord c : choices) {
            int counter = 1;
            System.out.println(counter+") ");
            System.out.println(choices.get(counter-1).toString()+"\n");
        }
    }
    public void printSecondList(ArrayList<Coord> choices, int index){
        int counter = 0;
        for (Coord c : choices) {
            System.out.println(index+") ");
            System.out.println(choices.get(counter).toString()+"\n");
            counter++;
            index++;
        }
    }
}
