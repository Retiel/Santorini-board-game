package it.polimi.ingsw.PSP33.utils;

/**
 * Custom two dimensional coordinates class
 */
public class Coord {

    /**
     * X coordinate
     */
    private final int x;

    /**
     * Y coordinate
     */
    private final int y;

    public Coord(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX(){
        return x;
    }

    public int getY() {
        return y;
    }

    public Coord getLocation() {
        return new Coord(x, y);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Coord) {
            Coord coord = (Coord) obj;
            return (x == coord.x) && (y == coord.y);
        }
        return super.equals(obj);
    }

    @Override
    public String toString() {
        return "[x=" + x + ",y=" + y + "]";
    }
}

