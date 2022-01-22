package edu.duke.sz232.battleship;

import static org.junit.jupiter.api.Assertions.assertEquals;


import org.junit.jupiter.api.Test;

public class SimpleShipDisplayInfoTest {
    @Test
    <T> void test_simple_ship_display_info() {

        SimpleShipDisplayInfo<Character> s= new SimpleShipDisplayInfo<Character>('s','a');
        assertEquals(s.getInfo(new Coordinate(1,1),true),'a');
        assertEquals(s.getInfo(new Coordinate(1,1),false),'s');
    }
}
