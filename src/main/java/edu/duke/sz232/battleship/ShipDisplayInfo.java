package edu.duke.sz232.battleship;
public interface ShipDisplayInfo<T> {
       public T getInfo(Coordinate where, boolean hit);
}