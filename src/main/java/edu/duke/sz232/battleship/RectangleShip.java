package edu.duke.sz232.battleship;

import java.util.HashSet;

public class RectangleShip<T> extends BasicShip<T> {
    final String name;

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
    /**
     * Constructor for rectangleShip, calling super methods
     * @param name  The name of the ship
     * @param upperLeft The upperLeft coordinate of the ship
     * @param width  ship's width
     * @param height  ship's height
     * @param myDisplayInfo Display info of the ship
     */
    public RectangleShip(String name, Coordinate upperLeft, int width, int height, ShipDisplayInfo<T> myDisplayInfo) {
        super(makeCoords(upperLeft, width, height), myDisplayInfo);
        this.name = name;
    }

    /**
     * Constructor for rectangleShip, calling other constructor
     * 
     */
    public RectangleShip(String name, Coordinate upperLeft, int width, int height, T data, T onHit) {
        this(name, upperLeft, width, height, new SimpleShipDisplayInfo<T>(data, onHit));
    }

    public RectangleShip(Coordinate upperLeft, T data, T onHit) {
        this("testship", upperLeft, 1, 1, data, onHit);
    }

    @Override
    public String getName() {
        return this.name;
    }
}
