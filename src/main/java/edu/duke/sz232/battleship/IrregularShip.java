package edu.duke.sz232.battleship;

import java.util.HashSet;

public class IrregularShip<T> extends BasicShip<T> {
    
    final String name;
    @Override
    public String getName() {
        return this.name;
    }

    public IrregularShip(String name, Coordinate upperLeft, char orientation, 
    ShipDisplayInfo<T> myDisplayInfo, ShipDisplayInfo<T> enemyDisplayInfo) {      
        super(makeCoords(upperLeft, name, orientation), myDisplayInfo, enemyDisplayInfo);
        this.name = name;
    }

    public IrregularShip(String name,Coordinate upperLeft, char orientation,T data,T onHit){
        this(name,upperLeft,orientation,new SimpleShipDisplayInfo<T>(data,onHit),new SimpleShipDisplayInfo<>(null, data));
    }
    /**
     * Add all coordinates of ship
     * @param upperLeft the upperleft corner of the ship
     * @param name the ship's name
     * @param orientation the ship's orientation
     * @return
     */
    static HashSet<Coordinate> makeCoords(Coordinate upperLeft, String name, char orientation) {
        HashSet<Coordinate> coords = new HashSet<>();
        int r = upperLeft.getRow();
        int c = upperLeft.getColumn();
        
        if (name == "Battleship") {  
            if (orientation == 'U') {
                coords.add(new Coordinate(r, c+1));
                for (int i=0; i<3; i++) {
                    coords.add(new Coordinate(r+1, c+i));
                }
            }
            if (orientation == 'R') {
                coords.add(new Coordinate(r+1, c+1));
                for (int i=0; i<3; i++) {
                    coords.add(new Coordinate(r+i, c));
                }
            }
            if (orientation == 'D') {
                coords.add(new Coordinate(r+1, c+1));
                for (int i=0; i<3; i++) {
                    coords.add(new Coordinate(r, c+i));
                }
            }
            if (orientation == 'L') {
                coords.add(new Coordinate(r+1, c));
                for (int i=0; i<3; i++) {
                    coords.add(new Coordinate(r+i, c+1));
                }
            }
        }

        if (name == "Carrier") {
            if (orientation == 'U') {
                for (int i=0; i<4; i++) {
                    coords.add(new Coordinate(r+i, c));
                }
                for (int i=2; i<5; i++) {
                    coords.add(new Coordinate(r+i, c+1));
                }
            }

            if (orientation == 'R') {
                for (int i=0; i<3; i++) {
                    coords.add(new Coordinate(r+1, c+i));
                }
                for (int i=1; i<5; i++) {
                    coords.add(new Coordinate(r, c+i));
                }
            }

            if (orientation == 'D') {
                for (int i=0; i<3; i++) {
                    coords.add(new Coordinate(r+i, c));
                }
                for (int i=1; i<5; i++) {
                    coords.add(new Coordinate(r+i, c+1));
                }
            }

            if (orientation == 'L') {
                for (int i=0; i<4; i++) {
                    coords.add(new Coordinate(r+1, c+i));
                }
                for (int i=2; i<5; i++) {
                    coords.add(new Coordinate(r, c+i));
                }
            }        
        }

        if (name == "Submarine") {
            if (orientation == 'H') {
                coords.add(new Coordinate(r, c));
                coords.add(new Coordinate(r, c+1));
            }
            if (orientation == 'V') {
                coords.add(new Coordinate(r, c));
                coords.add(new Coordinate(r+1, c));
            }
        }

        if (name == "Destroyer") {
            if (orientation == 'H') {
                coords.add(new Coordinate(r, c));
                coords.add(new Coordinate(r, c+1));
                coords.add(new Coordinate(r, c+2));
            }
            if (orientation == 'V') {
                coords.add(new Coordinate(r, c));
                coords.add(new Coordinate(r+1, c));
                coords.add(new Coordinate(r+2, c));
            }
        }

        return coords;
    }

}
