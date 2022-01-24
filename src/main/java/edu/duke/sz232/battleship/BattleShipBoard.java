package edu.duke.sz232.battleship;

import java.util.ArrayList;

/**
 * Representing the board
 */
public class BattleShipBoard<T> implements Board<T> {
    private final int width;
    private final int height;
    private final ArrayList<Ship<T>> myShips;
    private final PlacementRuleChecker<T> placementChecker;

    /**
     * chain to the other constructor
     * initiate placementchecker as chained InBoundsRuleChecker and NoCollisionRuleChecker
     * @param w
     * @param h
     */
    public BattleShipBoard(int w, int h) {
        this(w, h, new InBoundsRuleChecker<>(new NoCollisionRuleChecker<>(null)));
    }

    /**
     * Constructs a BattleShipBoard with the specified width
     * and height
     * 
     * @param w       is the width of the newly constructed board.
     * @param h       is the height of the newly constructed board.
     * @param checker is the placement rule checker
     * @throws IllegalArgumentException if the width or height are less than or
     *                                  equal to zero.
     */
    public BattleShipBoard(int w, int h, PlacementRuleChecker<T> checker) {
        if (w <= 0) {
            throw new IllegalArgumentException("BattleShipBoard's width must be positive but is " + w);
        }
        if (h <= 0) {
            throw new IllegalArgumentException("BattleShipBoard's height must be positive but is " + h);
        }
        this.width = w;
        this.height = h;
        this.myShips = new ArrayList<>();
        this.placementChecker = checker;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }
    /**
     * Try adding a ship to the ship list
     * Using the checker to determine there's no overlapping or outofboundry
     * 
     * @param toAdd the ship to be added
     */
    public boolean tryAddShip(Ship<T> toAdd) {
        if(placementChecker.checkPlacement(toAdd, this)){
            myShips.add(toAdd);
            return true;
        }
        return false;
    }

    /**
     * Figure out what is at where.
     * 
     * @param where the place to be figured
     */
    public T whatIsAt(Coordinate where) {
        for (Ship<T> s : myShips) {
            if (s.occupiesCoordinates(where)) {
                return s.getDisplayInfoAt(where);
            }
        }
        return null;
    }
}