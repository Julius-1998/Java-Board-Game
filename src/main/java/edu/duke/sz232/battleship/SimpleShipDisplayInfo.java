package edu.duke.sz232.battleship;

public class SimpleShipDisplayInfo<T> implements ShipDisplayInfo<T> {

    T myData;
    T onHit;

    /**
     * Initialize the display info for ship
     * @param myData
     * @param onHit
     */
    public SimpleShipDisplayInfo(T myData,T onHit){
        this.myData = myData;
        this.onHit = onHit;
    }
    
    /**
     * Return the display info for a certain block
     * @param where 
     * @param hit True if the block is hit
     */
    @Override
    public T getInfo(Coordinate where, boolean hit) {
        return hit?onHit:myData;
    }

}