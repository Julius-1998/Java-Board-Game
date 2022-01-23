package edu.duke.sz232.battleship;
public interface ShipDisplayInfo<T> {
       /**
        * Get the display info of certain block
        * @param where coordinate of certain block
        * @param hit
        * @return T return the displayinfo of block
        */
       public T getInfo(Coordinate where, boolean hit);
}