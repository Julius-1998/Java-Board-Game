package edu.duke.sz232.battleship;

import java.util.HashSet;

public class RectangleShip<T> extends BasicShip<T> {
    /**
     * Generate the coordinates of the ship by using the coordinate of upper left
     * corner
     * 
     * @param upperLeft
     * @param width
     * @param height
     * @return
     */
    static HashSet<Coordinate> makeCoords(Coordinate upperLeft, int width, int height) {
        HashSet<Coordinate> set = new HashSet<>();
        for (int w = 0; w < width; w++) {
            for (int h = 0; h < height; h++) {
                set.add(new Coordinate(h + upperLeft.getRow(), w + upperLeft.getColumn()));
            }
        }
        return set;
    }

    public RectangleShip(Coordinate upperLeft, int width, int height, ShipDisplayInfo<T> shipDisplayInfo) {
        super(makeCoords(upperLeft, width, height), shipDisplayInfo);
    }

    public RectangleShip(Coordinate upperLeft, int width, int height, T data, T onHit) {
        this(upperLeft, width, height, new SimpleShipDisplayInfo<T>(data, onHit));
    }

    public RectangleShip(Coordinate upperLeft, T data, T onHit) {
        this(upperLeft, 1, 1, data, onHit);
    }
}
