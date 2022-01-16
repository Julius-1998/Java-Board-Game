package edu.duke.sz232.battleship;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.LinkedList;

import javax.swing.plaf.SliderUI;

import org.junit.jupiter.api.Test;

public class BoardTextViewTest {

    @Test
    public void test_display_empty_2by2() {
        Board<Character> b1 = new BattleShipBoard<Character>(2, 2);
        BoardTextView view = new BoardTextView(b1);
        String expectedHeader = "  0|1\n";
        assertEquals(expectedHeader, view.makeHeader());
        String expected = expectedHeader +
                "A  |  A\n" +
                "B  |  B\n" +
                expectedHeader;
        assertEquals(expected, view.displayMyOwnBoard());
    }

    @Test
    public void test_display_empty_3by2() {
        String expectedHeader = "  0|1|2\n";
        String expectedBody = "A  | |  A\n" +
                "B  | |  B\n";
        emptyBoardHelper(3, 2, expectedHeader, expectedBody);
    }

    @Test
    public void test_display_empty_3by5() {
        String expectedHeader = "  0|1|2\n";
        String expectedBody = "A  | |  A\n" +
                "B  | |  B\n" +
                "C  | |  C\n" +
                "D  | |  D\n" +
                "E  | |  E\n";
        emptyBoardHelper(3, 5, expectedHeader, expectedBody);
    }

    @Test
    public void test_dispaly_single_basic_ship_3by5(){
        LinkedList<Ship<Character>> shipList = new LinkedList<>();
        BasicShip b1 =  new BasicShip(new Coordinate(0, 1));
        shipList.add(b1);
        String expectedBody = 
                "A  |s|  A\n" +
                "B  | |  B\n" +
                "C  | |  C\n" +
                "D  | |  D\n" +
                "E  | |  E\n";
        notEmptyBoardHelper(3, 5, expectedBody,shipList);
    }

    @Test
    public void test_dispaly_multiple_basic_ship_3by5(){
        LinkedList<Ship<Character>> shipList = new LinkedList<>();
        BasicShip b1 =  new BasicShip(new Coordinate(0, 1));
        BasicShip b2 =  new BasicShip(new Coordinate(0, 0));
        BasicShip b3 =  new BasicShip(new Coordinate(4, 2));
        shipList.add(b1);
        shipList.add(b2);
        shipList.add(b3);
        String expectedBody = 
                "A s|s|  A\n" +
                "B  | |  B\n" +
                "C  | |  C\n" +
                "D  | |  D\n" +
                "E  | |s E\n";
        notEmptyBoardHelper(3, 5, expectedBody,shipList);
    }
    /**
     * Helper function for generate board test
     * @param w width of the board
     * @param h height of the board
     * @param expectedHeader the top and bottom line of the board
     * @param expectedBody  the content of the board
     */
    private void emptyBoardHelper(int w, int h, String expectedHeader, String expectedBody) {
        Board<Character> b1 = new BattleShipBoard<Character>(w, h);
        BoardTextView view = new BoardTextView(b1);
        assertEquals(expectedHeader, view.makeHeader());
        String expected = expectedHeader + expectedBody + expectedHeader;
        assertEquals(expected, view.displayMyOwnBoard());
    }
    
    private void notEmptyBoardHelper(int w, int h, String expectedBody,LinkedList<Ship<Character>> list){
        Board<Character> b1 = new BattleShipBoard<Character>(w, h);
        for (Ship<Character> ship : list) {
            b1.tryAddShip(ship); 
        }
        BoardTextView view = new BoardTextView(b1);
        String expectedHeader = view.makeHeader();
        String expected = expectedHeader + expectedBody + expectedHeader;
        assertEquals(expected, view.displayMyOwnBoard());
    }
}
