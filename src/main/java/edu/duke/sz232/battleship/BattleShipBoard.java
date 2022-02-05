package edu.duke.sz232.battleship;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

/**
 * Representing the board
 */
public class BattleShipBoard<T> implements Board<T> {
    private final int width;
    private final int height;
    private final ArrayList<Ship<T>> myShips;
    private final PlacementRuleChecker<T> placementChecker;
    private final HashSet<Coordinate> enemyMisses;
    private final T missInfo;

    /**
     * chain to the other constructor
     * initiate placementchecker as chained InBoundsRuleChecker and
     * NoCollisionRuleChecker
     * 
     * @param w
     * @param h
     */
    public BattleShipBoard(int w, int h, T missInfo) {
        this(w, h, new InBoundsRuleChecker<>(new NoCollisionRuleChecker<>(null)), missInfo);
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
    public BattleShipBoard(int w, int h, PlacementRuleChecker<T> checker, T missInfo) {
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
        this.enemyMisses = new HashSet<>();
        this.missInfo = missInfo;
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
    public String tryAddShip(Ship<T> toAdd) {
        String err = placementChecker.checkPlacement(toAdd, this);
        if (err == null) {
            myShips.add(toAdd);
            return null;
        }
        return err;
    }

    /**
     * Figure out what is at where.
     * 
     * @param where the place to be figured
     */
    public T whatIsAtForSelf(Coordinate where) {
        return whatIsAt(where, true);
    }

    /**
     * Figure out what is at
     * 
     * @param where
     * @return
     */
    public T whatIsAtForEnemy(Coordinate where) {
        if (enemyMisses.contains(where)) {
            return missInfo;
        }
        return whatIsAt(where, false);
    }

    /**
     * Figure out what is at where.
     * 
     * @param where the place to be figured
     */
    protected T whatIsAt(Coordinate where, boolean isSelf) {
        for (Ship<T> s : myShips) {
            if (s.occupiesCoordinates(where)) {
                return s.getDisplayInfoAt(where, isSelf);
            }
        }
        return null;
    }

    /**
     * Got fire at a certain coordinate
     * 
     * @param c The coordinate to be fired at
     * @return the ship got fired at or null if it's a miss
     */
    public Ship<T> fireAt(Coordinate c) {
        // determine if c is within range of the board
        if (!isValid(c)) {
            throw new IllegalArgumentException("The coordinate is not on the board!");
        }
        for (Ship<T> ship : myShips) {
            Iterable<Coordinate> coordinates = ship.getCoordinates();
            for (Coordinate coordinate : coordinates) {
                if (coordinate.equals(c)) {
                    ship.recordHitAt(c);
                    return ship;
                }
            }
        }
        enemyMisses.add(c);
        return null;
    }

    /**
     * Determine if all ships owned by the board is sunk
     * 
     * @return true if it's all sunk
     */
    public boolean hasAllSunk() {
        for (Ship<T> ship : myShips) {
            if (!ship.isSunk()) {
                return false;
            }
        }
        return true;
    }

    /**
     * Determine if the coordinate is within range of the board
     */
    public boolean isValid(Coordinate where) {
        if (where.getRow() >= width || where.getColumn() >= height || where.getRow() < 0 || where.getColumn() < 0) {
            return false;
        }
        return true;
    }

    /**
     * get the ship at specific place, if there's no ship, return err string.
     * 
     * @param where         the old space of the ship
     * @param newCoordinate
     * @return
     */
    public Ship<T> removeShip(Coordinate where) throws IllegalArgumentException {
        if (!isValid(where)) {
            throw new IllegalArgumentException("The coordinate is not on the board!");
        }
        Ship<T> ship = getShipAtCoordinate(where);
        if (ship == null) {
            throw new IllegalArgumentException("The ship is not valid");
        }
        myShips.remove(ship);
        return ship;
    }

    /**
     * Check if the place has ship;
     * 
     * @param where the place being get
     * @return null if the place is not a ship
     */
    public Ship<T> getShipAtCoordinate(Coordinate where) {
        for (Ship<T> ship : myShips) {
            if (ship.occupiesCoordinates(where)) {
                return ship;
            }
        }
        return null;
    }

    /**
     * Do the sonar scan of the given coordinate
     * @param where the place being scanned
     * @return the map of ship's collection of name and count
     */
    public HashMap<String, Integer> sonar(Coordinate where) throws IllegalArgumentException {
        if (!isValid(where)) {
            throw new IllegalArgumentException("The coordinate is not on the board!");
        }
        HashMap<String, Integer> map = new HashMap<>();
        for (int i = -3; i < 4; i++) {
            for (int j = -3; j < 4; j++) {
                Coordinate detectPoint = new Coordinate(where.getRow() + i, where.getColumn() + j);
                int bias = Math.abs(i) + Math.abs(j);
                if (isValid(detectPoint) && bias <= 3) {
                    Ship<T> ship = getShipAtCoordinate(detectPoint);
                    if (ship != null) {
                        Integer currentCount = map.getOrDefault(ship.getName(),0);
                        map.put(ship.getName(), currentCount + 1);
                    }
                }
            }
        }
        return map;
    }
}