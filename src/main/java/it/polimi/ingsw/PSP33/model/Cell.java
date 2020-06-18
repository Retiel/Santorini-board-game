package it.polimi.ingsw.PSP33.model;

import it.polimi.ingsw.PSP33.utils.Coord;

/**
 * Cell class that holds all information related to the state of the single cell in the board
 */
public class Cell {

    /**
     * Coordinates of the cell
     */
    private final Coord coord;

    /**
     * Integer that represent the pieces of construction, except the roof
     */
    private int floor;

    /**
     * Boolean that represent the roof piece in the game
     */
    private boolean roof;

    /**
     * Reference to the pawn object that occupies the cell
     */
    private Pawn occupied;

    /**
     * Constructor of the class
     */
    public Cell(int coordX, int coordY) {
        this.floor = 0;
        this.roof = false;
        this.occupied = null;
        this.coord = new Coord(coordX, coordY);
    }

    /**
     * Method to get number of building in the cell
     *
     * @return Integer value between 0 and 3 (both included)
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
     * @return Boolean value (true ='there is a roof', false = 'no roof still')
     */
    public boolean isRoof() {
        return roof;
    }

    /**
     * Method to set if that someone build a roof in the cell
     * @param roof Boolean value (true ='there is a roof', false = 'no roof still')
     */
    public void setRoof(boolean roof) {
        this.roof = roof;
    }

    /**
     * Method to get the pawn that occupies the cell
     *
     * @return Pawn object
     */
    public Pawn getOccupied() {
        return occupied;
    }

    /**
     * Method to set the pawn that occupies the cell
     * @param occupied pawn that occupies the object
     */
    public void setOccupied(Pawn occupied) {
        this.occupied = occupied;
    }

    /**
     * Method to get the value x of the position
     *
     * @return Integer value
     */
    public int getCoordX() {
        return coord.getX();
    }

    /**
     * Method to get the value y of the position
     *
     * @return Integer value
     */
    public int getCoordY() {
        return coord.getY();
    }

    /**
     * Method to get the coortinates
     *
     * @return Coord class object
     */
    public Coord getCoord() {
        return coord.getLocation();
    }
}
