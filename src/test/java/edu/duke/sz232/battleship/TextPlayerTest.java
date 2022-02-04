package edu.duke.sz232.battleship;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.PrintStream;
import java.io.StringReader;
import java.util.LinkedList;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

public class TextPlayerTest {

    @Test
    void test_read_placement() throws IOException {
        StringReader sr = new StringReader("B2V\nC8H\na4v\n");
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(bytes, true);
        Board<Character> b = new BattleShipBoard<Character>(10, 20, 'X');
        TextPlayer player = new TextPlayer("A", b, new BufferedReader(sr), ps, new V1ShipFactory());
        String prompt = "Please enter a location for a ship:";
        Placement[] expected = new Placement[3];
        expected[0] = new Placement(new Coordinate(1, 2), 'V');
        expected[1] = new Placement(new Coordinate(2, 8), 'H');
        expected[2] = new Placement(new Coordinate(0, 4), 'V');
        for (int i = 0; i < expected.length; i++) {
            Placement p = player.readPlacement(prompt);
            assertEquals(p, expected[i]); // did we get the right Placement back
            assertEquals(prompt + "\n", bytes.toString()); // should have printed prompt and newline
            bytes.reset(); // clear out bytes for next time around
        }
    }

    @Test
    public void move_ship_test() {
        LinkedList<Ship<Character>> shipList = new LinkedList<>();
        V2ShipFactory v2ShipFactory = new V2ShipFactory();
        Ship<Character> s1 = v2ShipFactory.createIrregularShip(new Placement("A0l"), 'c', "Carrier");
        Ship<Character> s2 = v2ShipFactory.createRectangleShip(new Placement("c0v"), 1,2,'s', "Submarines");

        shipList.add(s1);
        shipList.add(s2);
        String expectedBody = 
                "A  | |c|c|c A\n" +
                "B c|c|c|c|  B\n" +
                "C s| | | |  C\n" +
                "D s| | | |  D\n" +
                "E  | | | |  E\n";
        Board<Character> b1 = new BattleShipBoard<Character>(5, 5, 'X');
        for (Ship<Character> ship : shipList) {
            b1.tryAddShip(ship);
        }
        BoardTextView view = new BoardTextView(b1);
        String expectedHeader = view.makeHeader();
        String expected = expectedHeader + expectedBody + expectedHeader;
        assertEquals(expected, view.displayMyOwnBoard());

        StringReader sr = new StringReader("A0\n");
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(bytes, true);
        TextPlayer player = new TextPlayer("A", b1, new BufferedReader(sr), ps, new V2ShipFactory());
        try {
            player.doSonarScan(b1);
        } catch (IOException e) {
        
        }
        
    }

    @Test
    void test_do_one_placement() throws IOException {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        TextPlayer player = createTextPlayer(10, 20, "B2V\nC8H\na4v\n", bytes);
        String prompt = "Player " + player.name + " where do you want to place a Destroyer?\n";
        V1ShipFactory shipFactory = new V1ShipFactory();
        player.doOnePlacement("Destroyer", (p) -> shipFactory.makeDestroyer(p));
        assertEquals(prompt + player.getBoardTextView().displayMyOwnBoard(), bytes.toString());

    }

    @Test
    void test_read_EOF() throws IOException {
        StringReader sr = new StringReader("");
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(bytes, true);
        Board<Character> b = new BattleShipBoard<Character>(10, 20, 'X');
        TextPlayer player = new TextPlayer("A", b, new BufferedReader(sr), ps, new V1ShipFactory());
        String prompt = "Please enter a location for a ship:";
        Placement[] expected = new Placement[3];
        expected[0] = new Placement(new Coordinate(1, 2), 'V');
        expected[1] = new Placement(new Coordinate(2, 8), 'H');
        expected[2] = new Placement(new Coordinate(0, 4), 'V');
        for (int i = 0; i < expected.length; i++) {
            assertThrows(EOFException.class, () -> player.readPlacement(prompt));
        }
    }

    private TextPlayer createTextPlayer(int w, int h, String inputData, ByteArrayOutputStream bytes) {
        BufferedReader input = new BufferedReader(new StringReader(inputData));
        PrintStream output = new PrintStream(bytes, true);
        Board<Character> board = new BattleShipBoard<Character>(w, h, 'X');
        V1ShipFactory shipFactory = new V1ShipFactory();
        return new TextPlayer("A", board, input, output, shipFactory);
    }
}
