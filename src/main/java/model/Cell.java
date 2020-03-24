package model;

/**
 * Cell class
 */
public class Cell {

    private int floor;
    private boolean roof;

    public Cell() {
        this.floor = 0;
        this.roof = false;
    }

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public boolean isRoof() {
        return roof;
    }

    public void setRoof(boolean roof) {
        this.roof = roof;
    }
}
