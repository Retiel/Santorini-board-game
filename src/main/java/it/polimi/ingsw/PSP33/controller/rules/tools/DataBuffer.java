package it.polimi.ingsw.PSP33.controller.rules.tools;

import it.polimi.ingsw.PSP33.model.Cell;
import it.polimi.ingsw.PSP33.model.Pawn;
import it.polimi.ingsw.PSP33.utils.Coord;

import java.util.ArrayList;
import java.util.List;

/**
 * Class to bank data for different usage in the TurnFlow, simple version of data memory
 *
 */
public class DataBuffer {

    private String action;
    private List<Coord> coordList;
    private List<Coord> coordListGods;

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public List<Coord> getCoordList() {
        return new ArrayList<>(coordList);
    }

    public void setCoordList(List<Coord> coordList) {
        this.coordList = coordList;
    }

    public List<Coord> getCoordListGods() {
        return coordListGods;
    }

    public void setCoordListGods(List<Coord> coordListGods) {
        this.coordListGods = coordListGods;
    }

}
