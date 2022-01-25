package edu.duke.sz232.battleship;

import java.util.HashMap;

/**
 * This is the abstract class for all kinds of ships
 * Realizing basic methods of ships
 */
public abstract class BasicShip<T> implements Ship<T> {
    protected ShipDisplayInfo<T> myDisplayInfo;
    protected HashMap<Coordinate, Boolean> myPieces;// false means not hit
    protected ShipDisplayInfo<T> enemyDisplayInfo;

    /**
     * Constructor for BasicShip
     * 
     * @param where         The set of coordinates of the ship
     * @param myDisplayInfo The display info of ship
     */
    public BasicShip(Iterable<Coordinate> where, ShipDisplayInfo<T> myDisplayInfo,
            ShipDisplayInfo<T> enemyDisplayInfo) {
        myPieces = new HashMap<>();
        for (Coordinate coordinate : where) {
            myPieces.put(coordinate, false);
        }
        this.myDisplayInfo = myDisplayInfo;
        this.enemyDisplayInfo = enemyDisplayInfo;
    }

    /**
     * Check if the coordinate is within the range of ship's coordinates
     * 
     * @param c The coordiate to be determined
     */
    protected void checkCoordinateInThisShip(Coordinate c) {
        if (!myPieces.containsKey(c)) {
            throw new IllegalArgumentException("Accessing coordinate out of the ship");
        }
    }

    /**
     * Check if the ship contains certain coordinate
     * 
     * @param where The coordiate to be determined
     */
    @Override
    public boolean occupiesCoordinates(Coordinate where) {
        return myPieces.containsKey(where);
    }

    /**
     * Check if the ship is sunk by looping over it's coordinates
     * 
     * @return boolean
     */
    @Override
    public boolean isSunk() {
        boolean isSunk = true;
        for (boolean b : myPieces.values()) {
            isSunk = isSunk & b;
        }
        return isSunk;
    }

    /**
     * Mark the ship's block as hit at certain coordinate
     * 
     * @param where the ship's block
     */
    @Override
    public void recordHitAt(Coordinate where) {
        checkCoordinateInThisShip(where);
        myPieces.put(where, true);
    }

    /**
     * Check if the ship's hit at coordinate
     * 
     * @param where the ship's block
     */
    @Override
    public boolean wasHitAt(Coordinate where) {
        checkCoordinateInThisShip(where);
        return myPieces.get(where);
    }

    /**
     * Get the display info at certain coordinate
     */
    @Override
    public T getDisplayInfoAt(Coordinate where, boolean myShip) {
        checkCoordinateInThisShip(where);
        if (myShip) {
            return myDisplayInfo.getInfo(where, myPieces.get(where));
        } else {
            return enemyDisplayInfo.getInfo(where, myPieces.get(where));
        }
    }

    /**
     * Get all of the Coordinates that this Ship occupies.
     * 
     * @return An Iterable with the coordinates that this Ship occupies
     */
    @Override
    public Iterable<Coordinate> getCoordinates() {
        return myPieces.keySet();
    }

}
