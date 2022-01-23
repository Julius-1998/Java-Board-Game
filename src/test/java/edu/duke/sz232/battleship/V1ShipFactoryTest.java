package edu.duke.sz232.battleship;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class V1ShipFactoryTest {

    @Test
    void testMakeBattleship() {
        V1ShipFactory factory = new V1ShipFactory();
        Placement h3_4 = new Placement(new Coordinate(3, 4), 'H');
        Ship<Character> dst = factory.makeBattleship(h3_4);
        checkShip(dst, "Battleship", 'b', new Coordinate(3, 4), new Coordinate(3, 5), new Coordinate(3, 6));

    }

    @Test
    void testMakeCarrier() {
        V1ShipFactory factory = new V1ShipFactory();
        Placement v3_4 = new Placement(new Coordinate(3, 4), 'V');
        Ship<Character> dst = factory.makeCarrier(v3_4);
        checkShip(dst, "Carrier", 'c', new Coordinate(3, 4), new Coordinate(4, 4), new Coordinate(5, 4),
                new Coordinate(6, 4));

    }

    @Test
    void testMakeDestroyer() {
        V1ShipFactory factory = new V1ShipFactory();
        Placement v1_2 = new Placement(new Coordinate(1, 2), 'v');
        Ship<Character> dst = factory.makeDestroyer(v1_2);
        checkShip(dst, "Destroyer", 'd', new Coordinate(1, 2), new Coordinate(2, 2), new Coordinate(3, 2),
                new Coordinate(4, 2), new Coordinate(5, 2), new Coordinate(6, 2));
    }

    @Test
    void testMakeSubmarine() {
        V1ShipFactory factory = new V1ShipFactory();
        Placement v1_2 = new Placement(new Coordinate(1, 2), 'v');
        Ship<Character> dst = factory.makeSubmarine(v1_2);
        checkShip(dst, "Submarine", 's', new Coordinate(1, 2), new Coordinate(2, 2));

    }

    private void checkShip(Ship<Character> testShip, String expectedName,
            char expectedLetter, Coordinate... expectedLocs) {
        for (Coordinate coordinate : expectedLocs) {
            assertEquals(expectedLetter, testShip.getDisplayInfoAt(coordinate));
        }
        assertEquals(expectedName, testShip.getName());
    }

  
}
