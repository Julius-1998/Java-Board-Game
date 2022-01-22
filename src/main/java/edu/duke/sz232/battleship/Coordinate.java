package edu.duke.sz232.battleship;

public class Coordinate {
    private final int row;
    private final int column;

    Coordinate(int row, int column) {
        this.row = row;
        this.column = column;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    /**
     * @param o //The other Coordinate object to compare to
     * @return boolean
     */
    @Override
    public boolean equals(Object o) {
        if (o.getClass().equals(getClass())) {
            Coordinate c = (Coordinate) o;
            return row == c.row && column == c.column;
        }
        return false;
    }

    @Override
    public String toString() {
        return "(" + row + ", " + column + ")";
    }

    @Override
    public int hashCode() {
        return toString().hashCode();
    }

    /**
     * Convert a string coordinate to int coordinate
     * 
     * @param descr
     */
    public Coordinate(String descr) {
        if (descr.length() != 2) {
            throw new IllegalArgumentException("illegal coordinate to transform:" + descr);
        }
        if (descr.charAt(0) > 'z' || descr.charAt(0) < 'A' || (descr.charAt(0) > 'Z' && descr.charAt(0) < 'a')
                || descr.charAt(1) < '0' || descr.charAt(1) > '9') {
            throw new IllegalArgumentException("illegal coordinate to transform:" + descr);
        }
        if (descr.charAt(0) >= 'a' && descr.charAt(0) <= 'z') {
            row = descr.charAt(0) - 'a';
        } else {
            row = descr.charAt(0) - 'A';

        }
        column = descr.charAt(1) - '0';

    }

}
