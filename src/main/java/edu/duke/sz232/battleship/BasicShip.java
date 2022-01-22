package edu.duke.sz232.battleship;

import java.util.HashMap;

public abstract class BasicShip<T> implements Ship<T>{
    protected ShipDisplayInfo<T> myDisplayInfo;
    protected HashMap<Coordinate,Boolean> myPieces;//false means not hit
    

    public BasicShip(Iterable<Coordinate> where,ShipDisplayInfo<T> myDisplayInfo){
        myPieces = new HashMap<>();
        for (Coordinate coordinate : where) {
            myPieces.put(coordinate, false);
        }
        this.myDisplayInfo = myDisplayInfo;
    }

    protected void checkCoordinateInThisShip(Coordinate c){
        if(!myPieces.containsKey(c)){
            throw new IllegalArgumentException("Accessing coordinate out of the ship");
        }
    }

    @Override
    public boolean occupiesCoordinates(Coordinate where) {
        return myPieces.containsKey(where);
    }

    @Override
    public boolean isSunk() {
        boolean isSunk = true;
        for (boolean b : myPieces.values()) {
            isSunk = isSunk & b;
        }
        return isSunk;
    }

    @Override
    public void recordHitAt(Coordinate where) {
        checkCoordinateInThisShip(where);
        myPieces.put(where, true);
    }

    @Override
    public boolean wasHitAt(Coordinate where) {
        checkCoordinateInThisShip(where);
        return myPieces.get(where);
    }

    @Override
    public T getDisplayInfoAt(Coordinate where) {
        checkCoordinateInThisShip(where);
        return myDisplayInfo.getInfo(where, myPieces.get(where));
    }
    
}
