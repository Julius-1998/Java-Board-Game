package edu.duke.sz232.battleship;

public class SimpleShipDisplayInfo<T> implements ShipDisplayInfo<T> {

    T myData;
    T onHit;
    public SimpleShipDisplayInfo(T myData,T onHit){
        this.myData = myData;
        this.onHit = onHit;
    }
    
    @Override
    public T getInfo(Coordinate where, boolean hit) {
        return hit?onHit:myData;
    }

}