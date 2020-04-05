package it.polimi.ingsw.PSP33.model;

/**
 * Cell class that hold all information related to the state of the single cell in the board
 */
public class Cell {

    /**
     * Integer that represent the pieces of contruction, except the roof
     */
    private int floor;

    /**
     * Boolean that represent the roof piece in the game
     */
    private boolean roof;

    /**
     * Constructor of the class
     */
    public Cell() {
        this.floor = 0;
        this.roof = false;
    }

    /**
     * Method to get number of building in the cell
     *
     * @return Integer value
     */
    public int getFloor() {
        return floor;
    }

    /**
     * Method to modify the number of the building
     * @param floor number fo buildings (excluded the roof)
     */
    public void setFloor(int floor) {
        this.floor = floor;
    }

    /**
     * Method to verify if there is a roof in the building
     *
     * @return Boolean
     */
    public boolean isRoof() {
        return roof;
    }

    /**
     * Method to set if that someone build a roof in the cell
     * @param roof boolean value (true ='there is a roof', false = 'no roof still')
     */
    public void setRoof(boolean roof) {
        this.roof = roof;
    }
}
