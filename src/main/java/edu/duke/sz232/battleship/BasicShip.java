package edu.duke.sz232.battleship;

import java.util.HashMap;

public abstract class BasicShip<T> implements Ship<T>{
    protected ShipDisplayInfo<T> myDisplayInfo;
    protected HashMap<Coordinate,Boolean> myPieces;
    

    public BasicShip(Iterable<Coordinate> where,ShipDisplayInfo<T> myDisplayInfo){
        myPieces = new HashMap<>();
        for (Coordinate coordinate : where) {
            myPieces.put(coordinate, false);
        }
        this.myDisplayInfo = myDisplayInfo;
    }
    @Override
    public boolean occupiesCoordinates(Coordinate where) {
        return myPieces.containsKey(where);
    }

    @Override
    public boolean isSunk() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void recordHitAt(Coordinate where) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public boolean wasHitAt(Coordinate where) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public T getDisplayInfoAt(Coordinate where) {
        //TODO
        return myDisplayInfo.getInfo(where, false);
    }
    
}
