package it.polimi.ingsw.PSP33.controller.rules.buffer_control;

import it.polimi.ingsw.PSP33.model.Cell;
import it.polimi.ingsw.PSP33.utils.Coord;

import java.util.List;

public class DataControl {


    public static void controlEffect(){

    }

    public static boolean controlInput(Coord coord, DataBuffer dataBuffer){
        return dataBuffer.getCoordList().contains(coord) || dataBuffer.getCoordListGods().contains(coord);
    }

    public static boolean ExclusiveList(List<Coord> list1, List<Coord> list2){
        for (Coord coord : list1){
            if (list2.contains(coord)) return false;
        }
        return true;
    }


}
