package edu.duke.sz232.battleship;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

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

    @Test
    void testCheckCoordinateInThisShip(){
        RectangleShip<Integer> rectangleShip = new RectangleShip<>(new Coordinate(3,4),1 ,5 , 1, 0);
        Coordinate c1 = new Coordinate(4,4);
        assertDoesNotThrow(()->rectangleShip.checkCoordinateInThisShip(c1));
        Coordinate c2 = new Coordinate(2,4);
        assertThrows(IllegalArgumentException.class,()->rectangleShip.checkCoordinateInThisShip(c2));
    }

    @Test
    void testIsSunk(){
        RectangleShip<Integer> rectangleShip = new RectangleShip<>(new Coordinate(3,4),1 ,3 , 1, 0);
        rectangleShip.recordHitAt(new Coordinate(3,4));
        rectangleShip.recordHitAt(new Coordinate(4,4));
        assertEquals(rectangleShip.isSunk(), false);
        rectangleShip.recordHitAt(new Coordinate(5,4));
        assertEquals(rectangleShip.isSunk(), true);
    }
    @Test
    void testRecordHitAt(){
        RectangleShip<Integer> rectangleShip = new RectangleShip<>(new Coordinate(3,4),1 ,5 , 1, 0);
        Coordinate c1 = new Coordinate(4,4);
        rectangleShip.recordHitAt(c1);
        assertEquals(rectangleShip.getDisplayInfoAt(c1), 0);
        Coordinate c2 = new Coordinate(3,4);
        assertEquals(rectangleShip.getDisplayInfoAt(c2), 1);
    }
}
