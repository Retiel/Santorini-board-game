package it.polimi.ingsw.PSP33.model;

import it.polimi.ingsw.PSP33.utils.Coord;

/**
 * Cell class that holds all information related to the state of the single cell in the board
 */
public class Cell {

    /**
     * Light version of the class Cell
     */
    private final LightCell lightCell;

    /**
     * Reference to the pawn object that occupies the cell
     */
    private Pawn occupied;

    /**
     * Constructor of the class
     */
    public Cell(int coordX, int coordY) {

        this.lightCell = new LightCell(new Coord(coordX, coordY), 0, false);
        this.occupied = null;
    }

    /**
     * Method to get number of building in the cell
     *
     * @return Integer value between 0 and 3 (both included)
     */
    public int getFloor() {
        return lightCell.getFloor();
    }

    /**
     * Method to modify the number of the building
     * @param floor number fo buildings (excluded the roof)
     */
    public void setFloor(int floor) {
        this.lightCell.setFloor(floor);
    }

    /**
     * Method to verify if there is a roof in the building
     *
     * @return Boolean value (true ='there is a roof', false = 'no roof still')
     */
    public boolean isRoof() {
        return lightCell.isRoof();
    }

    /**
     * Method to set if that someone build a roof in the cell
     * @param roof Boolean value (true ='there is a roof', false = 'no roof still')
     */
    public void setRoof(boolean roof) {
        this.lightCell.setRoof(roof);
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
        this.lightCell.setLightPawn(occupied.getLightPawn());
    }

    /**
     * Method to get the value x of the position
     *
     * @return Integer value
     */
    public int getCoordX() {
        return lightCell.getCoord().getX();
    }

    /**
     * Method to get the value y of the position
     *
     * @return Integer value
     */
    public int getCoordY() {
        return lightCell.getCoord().getY();
    }

    /**
     * Method to get the coortinates
     *
     * @return Coord class object
     */
    public Coord getCoord() {
        return lightCell.getCoord().getLocation();
    }

    /**
     * Coordinates of the Cell light version
     */
    public LightCell getLightCell() {
        return lightCell;
    }

    public class LightCell{

        /**
         * Coordinates of the cell
         */
        private final Coord coord;

        /**
         * Integer that represent the pieces of contruction, except the roof
         */
        private int floor;

        /**
         * Boolean that represent the roof piece in the game
         */
        private boolean roof;

        /**
         * Data of the LightPawn
         */
        private Pawn.LightPawn lightPawn;

        /**
         * Constructor
         */
        public LightCell(Coord coord, int floor, boolean roof) {
            this.coord = coord;
            this.floor = floor;
            this.roof = roof;
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
         * Method to get the coortinates
         *
         * @return Coord class object
         */
        public Coord getCoord() {
            return coord.getLocation();
        }

        /**
         * Method to get the Light Pawn
         *
         * @return LightPawn class object
         */
        public Pawn.LightPawn getLightPawn() {
            return lightPawn;
        }

        /**
         * Method to get the Light Pawn
         * @param lightPawn light version of the pawn
         */
        public void setLightPawn(Pawn.LightPawn lightPawn) {
            this.lightPawn = lightPawn;
        }
    }
}
