package it.polimi.ingsw.PSP33.controller.rules.tools;

import it.polimi.ingsw.PSP33.utils.Coord;
import it.polimi.ingsw.PSP33.utils.enums.Gods;

import java.util.List;

/**
 * Class with all method for flag and boolean control
 */
public class DataControl {

    /**
     * Method that verify the input of the player
     * @param coord input to control
     * @param dataBuffer data bank with all data needed
     *
     * @return Boolean
     */
    public static boolean controlInput(Coord coord, DataBuffer dataBuffer){
        return dataBuffer.getCoordList().contains(coord) || dataBuffer.getCoordListGods().contains(coord);
    }

    /**
     * Method that verify the list are exclusive, the lists have no common object
     * @param list1 first list
     * @param list2 second list
     *
     * @return Boolean
     */
    public static boolean exclusiveList(List<Coord> list1, List<Coord> list2){
        for (Coord coord : list1){
            if (list2.contains(coord)) return false;
        }
        return true;
    }

    /**
     * Method to enable extraAction at the start of the turn
     * @param name name of the god to which enable the extraAction
     *
     * @return Boolean
     */
    public static boolean checkStart(Gods name){
        switch (name){
            case PROMETHEUS: return true;
            case NOGOD:
            default:
                return false;
        }
    }

    /**
     * Method to enable roof build at any level
     * @param name name of the god to which enable the feature
     *
     * @return Boolean
     */
    public static boolean checkBuild(Gods name){
        return name.equals(Gods.ATLAS);
    }

    /**
     * Method reset limiter at the turn rotation
     * @param name name of the god
     *
     * @return Boolean
     */
    public static boolean limitReset(Gods name){
        switch (name){
            case ATHENA:
            case NOGOD:
            default: return false;
        }
    }

}
