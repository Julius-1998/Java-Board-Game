package edu.duke.sz232.battleship;

public class Placement {
    private final Coordinate where;
    private final char orientation;

    Placement(Coordinate coordinate, char orientationChar) {
        this.where = coordinate;
        if (orientationChar == 'V' || orientationChar == 'H') {
            orientation = orientationChar;
        } else if (orientationChar == 'v' || orientationChar == 'h') {
            orientation = Character.toUpperCase(orientationChar);
        } else {
            throw new IllegalArgumentException("Illegal orientation Char: " + orientationChar);
        }
    }

    public char getOrientation() {
        return orientation;
    }

    public Coordinate getWhere() {
        return where;
    }

    @Override
    public String toString() {
        return where.toString() + orientation;
    }

    @Override
    public int hashCode() {
        return toString().hashCode();
    }

    /**
     * Construct Placement by string
     * 
     * @param s
     */
    Placement(String s) {
        if (s.length() != 3) {
            throw new IllegalArgumentException("Illegal conversion from string to Coordinate: " + s);
        }
        where = new Coordinate(s.substring(0, 2));
        Character orientationChar = s.charAt(2);
        if (orientationChar == 'V' || orientationChar == 'H') {
            orientation = orientationChar;
        } else if (orientationChar == 'v' || orientationChar == 'h') {
            orientation = Character.toUpperCase(orientationChar);
        } else {
            throw new IllegalArgumentException("Illegal conversion from string to Coordinate: " + s);
        }
    }

}
