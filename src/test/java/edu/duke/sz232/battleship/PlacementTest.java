package edu.duke.sz232.battleship;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class PlacementTest {
    @Test
    void test_to_string() {
        Placement p1 = new Placement(new Coordinate("A1"), 'V');
        assertEquals('V', p1.getOrientation());
        assertEquals(new Coordinate("A1"), p1.getWhere());
        Placement p2 = new Placement(new Coordinate("B2"), 'v');
        assertEquals('V', p2.getOrientation());
        assertEquals(new Coordinate("B2"), p2.getWhere());
        Placement p3 = new Placement(new Coordinate("A1"), 'h');
        assertEquals('H', p3.getOrientation());
        assertEquals(new Coordinate("A1"), p1.getWhere());
        Placement p4 = new Placement(new Coordinate("B2"), 'H');
        assertEquals('H', p4.getOrientation());
        assertEquals(new Coordinate("B2"), p2.getWhere());
    }

    @Test
    void invalid_to_string(){
        assertThrows(IllegalArgumentException.class, ()->new Placement(new Coordinate("B2"), 'a'));

    }
    @Test
    void test_string_constructor_valid_cases() {
        Placement p1 = new Placement("A1v");
        assertEquals(p1.getWhere(), new Coordinate(0, 1));
        assertEquals(p1.getOrientation(), 'V');
        Placement p2 = new Placement("C5h");
        assertEquals(p2.getWhere(), new Coordinate(2, 5));
        assertEquals(p2.getOrientation(), 'H');
        Placement p3 = new Placement("D4h");
        assertEquals(p3.getWhere(), new Coordinate(3, 4));
        assertEquals(p3.getOrientation(), 'H');
    }

    @Test
    void test_string_constructor_error_cases() {
        assertThrows(IllegalArgumentException.class, () -> new Placement("A11V"));
        assertThrows(IllegalArgumentException.class, () -> new Placement("AAV"));
        assertThrows(IllegalArgumentException.class, () -> new Placement("A0Z"));
    }

    @Test
    void test_hashCode() {
        Coordinate c1 = new Coordinate("C5");
        Coordinate c2 = new Coordinate("C5");
        Coordinate c3 = new Coordinate("D6");
        Placement p1 = new Placement(c1, 'v');
        Placement p2 = new Placement(c2, 'V');
        Placement p3 = new Placement(c1, 'v');
        Placement p4 = new Placement(c3, 'v');
        Placement p5 = new Placement(c1, 'h');
        assertEquals(p1.hashCode(), p2.hashCode());
        assertEquals(p1.hashCode(), p3.hashCode());
        assertNotEquals(p1.hashCode(), p4.hashCode());
        assertNotEquals(p1.hashCode(), p5.hashCode());
    }

    @Test
    void test_equals(){
        Coordinate c1 = new Coordinate("C5");
        Coordinate c2 = new Coordinate("C5");
        Coordinate c3 = new Coordinate("D6");
        Placement p1 = new Placement(c1, 'v');
        Placement p2 = new Placement(c2, 'V');
        Placement p3 = new Placement(c1, 'v');
        Placement p4 = new Placement(c3, 'v');
        Placement p5 = new Placement(c1, 'h');
        assertEquals(p1, p2);
        assertEquals(p1, p3);
        assertNotEquals(p1, p4);
        assertNotEquals(p1, p5);
        assertNotEquals(p1, c1);
    }
}
