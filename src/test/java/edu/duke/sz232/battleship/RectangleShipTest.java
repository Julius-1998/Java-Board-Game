package edu.duke.sz232.battleship;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashSet;

import org.junit.jupiter.api.Test;

public class RectangleShipTest {
    @Test
    void testMakeCoords() {
        HashSet<Coordinate> s = RectangleShip.makeCoords(new Coordinate(3, 4) , 1, 5);        
        assertEquals(s.contains(new Coordinate(3,4)), true);
        assertEquals(s.contains(new Coordinate(4,4)), true);
        assertEquals(s.contains(new Coordinate(5,4)), true);
        assertEquals(s.contains(new Coordinate(6,4)), true);
        assertEquals(s.contains(new Coordinate(7,4)), true);
        
    }
}
