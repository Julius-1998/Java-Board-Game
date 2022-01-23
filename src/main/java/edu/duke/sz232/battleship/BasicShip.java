package edu.duke.sz232.battleship;

import java.util.HashMap;

public abstract class BasicShip<T> implements Ship<T>{
    protected ShipDisplayInfo<T> myDisplayInfo;
    protected HashMap<Coordinate,Boolean> myPieces;//false means not hit
    
    /**
     * Constructor for BasicShip
     * @param where The set of coordinates of the ship
     * @param myDisplayInfo The display info of ship
     */
    public BasicShip(Iterable<Coordinate> where,ShipDisplayInfo<T> myDisplayInfo){
        myPieces = new HashMap<>();
        for (Coordinate coordinate : where) {
            myPieces.put(coordinate, false);
        }
        this.myDisplayInfo = myDisplayInfo;
    }

    /**
     * Check if the coordinate is within the range of ship's coordinates
     * @param c The coordiate to be determined
     */
    protected void checkCoordinateInThisShip(Coordinate c){
        if(!myPieces.containsKey(c)){
            throw new IllegalArgumentException("Accessing coordinate out of the ship");
        }
    }

    /**
     * Check if the ship contains certain coordinate
     * @param where The coordiate to be determined
     */
    @Override
    public boolean occupiesCoordinates(Coordinate where) {
        return myPieces.containsKey(where);
    }

    /**
     * Check if the ship is sunk by looping over it's coordinates
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
     * @param where the ship's block
     */
    @Override
    public void recordHitAt(Coordinate where) {
        checkCoordinateInThisShip(where);
        myPieces.put(where, true);
    }

    /**
     * Check if the ship's hit at coordinate
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
    public T getDisplayInfoAt(Coordinate where) {
        checkCoordinateInThisShip(where);
        return myDisplayInfo.getInfo(where, myPieces.get(where));
    }
    
}
