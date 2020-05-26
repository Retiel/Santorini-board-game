package it.polimi.ingsw.PSP33.utils;

public class Coord {

    private final int x;

    private final int y;

    public Coord() {
        this(-1, -1);
    }

    public Coord(Coord coord) {
        this(coord.x, coord.y);
    }

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

