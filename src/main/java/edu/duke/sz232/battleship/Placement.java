package edu.duke.sz232.battleship;

public class Placement {
    private final Coordinate where;
    private final char orientation;

    /**
     * Constructor for placement
     * @param coordinate    The upper left coordinate of the placement
     * @param orientationChar   H or V, indicates the orientation of the ship
     */
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

    /**
     * return the coordinate's string and the orientation, like "(1,2)V"
     */
    @Override
    public String toString() {
        return where.toString() + orientation;
    }

    /**
     * Calling string's hashCode
     */
    @Override
    public int hashCode() {
        return toString().hashCode();
    }

    /**
     * Override equals method 
     */
    @Override
    public boolean equals(Object o){
        if (o.getClass().equals(getClass())) {
            Placement p = (Placement) o;
            return getWhere().equals(p.getWhere()) &&  getOrientation()== p.getOrientation();
        }
        return false;
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
