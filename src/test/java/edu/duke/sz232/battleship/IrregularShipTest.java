package edu.duke.sz232.battleship;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.LinkedList;

import org.junit.jupiter.api.Test;

public class IrregularShipTest {
    @Test
    public void place_battleship_test_right() {
        LinkedList<Ship<Character>> shipList = new LinkedList<>();
        V2ShipFactory v2ShipFactory = new V2ShipFactory();
        Ship<Character> b1 = v2ShipFactory.createIrregularShip(new Placement("A0r"), 'b', "Battleship");
        shipList.add(b1);
        String expectedBody = "A b| |  A\n" +
                "B b|b|  B\n" +
                "C b| |  C\n" +
                "D  | |  D\n" +
                "E  | |  E\n";
        notEmptyBoardHelper(3, 5, expectedBody, shipList);
    }

    @Test
    public void place_battleship_test_left() {
        LinkedList<Ship<Character>> shipList = new LinkedList<>();
        V2ShipFactory v2ShipFactory = new V2ShipFactory();
        Ship<Character> b1 = v2ShipFactory.createIrregularShip(new Placement("A0l"), 'b', "Battleship");
        shipList.add(b1);
        String expectedBody = "A  |b|  A\n" +
                "B b|b|  B\n" +
                "C  |b|  C\n" +
                "D  | |  D\n" +
                "E  | |  E\n";
        notEmptyBoardHelper(3, 5, expectedBody, shipList);
    }

    @Test
    public void place_battleship_test_up() {
        LinkedList<Ship<Character>> shipList = new LinkedList<>();
        V2ShipFactory v2ShipFactory = new V2ShipFactory();
        Ship<Character> b1 = v2ShipFactory.createIrregularShip(new Placement("A0u"), 'b', "Battleship");
        shipList.add(b1);
        String expectedBody = "A  |b|  A\n" +
                "B b|b|b B\n" +
                "C  | |  C\n" +
                "D  | |  D\n" +
                "E  | |  E\n";
        notEmptyBoardHelper(3, 5, expectedBody, shipList);
    }

    @Test
    public void place_battleship_test_down() {
        LinkedList<Ship<Character>> shipList = new LinkedList<>();
        V2ShipFactory v2ShipFactory = new V2ShipFactory();
        Ship<Character> b1 = v2ShipFactory.createIrregularShip(new Placement("A0d"), 'b', "Battleship");
        shipList.add(b1);
        String expectedBody = "A b|b|b A\n" +
                "B  |b|  B\n" +
                "C  | |  C\n" +
                "D  | |  D\n" +
                "E  | |  E\n";
        notEmptyBoardHelper(3, 5, expectedBody, shipList);
    }

    @Test
    public void place_carrier_test_down() {
        LinkedList<Ship<Character>> shipList = new LinkedList<>();
        V2ShipFactory v2ShipFactory = new V2ShipFactory();
        Ship<Character> b1 = v2ShipFactory.createIrregularShip(new Placement("A0d"), 'c', "Carrier");
        shipList.add(b1);
        String expectedBody = "A c| |  A\n" +
                "B c|c|  B\n" +
                "C c|c|  C\n" +
                "D  |c|  D\n" +
                "E  |c|  E\n";
        notEmptyBoardHelper(3, 5, expectedBody, shipList);
    }

    @Test
    public void place_carrier_test_up() {
        LinkedList<Ship<Character>> shipList = new LinkedList<>();
        V2ShipFactory v2ShipFactory = new V2ShipFactory();
        Ship<Character> b1 = v2ShipFactory.createIrregularShip(new Placement("A0u"), 'c', "Carrier");
        shipList.add(b1);
        String expectedBody = "A c| |  A\n" +
                "B c| |  B\n" +
                "C c|c|  C\n" +
                "D c|c|  D\n" +
                "E  |c|  E\n";
        notEmptyBoardHelper(3, 5, expectedBody, shipList);
    }

    @Test
    public void place_carrier_test_right() {
        LinkedList<Ship<Character>> shipList = new LinkedList<>();
        V2ShipFactory v2ShipFactory = new V2ShipFactory();
        Ship<Character> b1 = v2ShipFactory.createIrregularShip(new Placement("A0r"), 'c', "Carrier");
        shipList.add(b1);
        String expectedBody = "A  |c|c|c|c A\n" +
                "B c|c|c| |  B\n" +
                "C  | | | |  C\n" +
                "D  | | | |  D\n" +
                "E  | | | |  E\n";
        notEmptyBoardHelper(5, 5, expectedBody, shipList);
    }

    @Test
    public void place_carrier_test_left() {
        LinkedList<Ship<Character>> shipList = new LinkedList<>();
        V2ShipFactory v2ShipFactory = new V2ShipFactory();
        Ship<Character> b1 = v2ShipFactory.createIrregularShip(new Placement("A0l"), 'c', "Carrier");
        shipList.add(b1);
        String expectedBody = "A  | |c|c|c A\n" +
                "B c|c|c|c|  B\n" +
                "C  | | | |  C\n" +
                "D  | | | |  D\n" +
                "E  | | | |  E\n";
        notEmptyBoardHelper(5, 5, expectedBody, shipList);
    }

    

    private void notEmptyBoardHelper(int w, int h, String expectedBody, LinkedList<Ship<Character>> list) {
        Board<Character> b1 = new BattleShipBoard<Character>(w, h, 'X');
        for (Ship<Character> ship : list) {
            b1.tryAddShip(ship);
        }
        BoardTextView view = new BoardTextView(b1);
        String expectedHeader = view.makeHeader();
        String expected = expectedHeader + expectedBody + expectedHeader;
        assertEquals(expected, view.displayMyOwnBoard());
    }
}
