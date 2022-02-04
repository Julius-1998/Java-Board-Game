package edu.duke.sz232.battleship;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.LinkedList;


import org.junit.jupiter.api.Test;

public class BoardTextViewTest {

    @Test
    public void test_display_empty_2by2() {
        Board<Character> b1 = new BattleShipBoard<Character>(2, 2,'X');
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

        RectangleShip<Character> s = new RectangleShip<Character>( new Coordinate(0, 1), 's', '*');
        shipList.add(s);
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
        RectangleShip<Character> b1 = new RectangleShip<Character>( new Coordinate(0, 1), 's', '*');
        RectangleShip<Character> b2 = new RectangleShip<Character>( new Coordinate(0, 0), 's', '*');
        RectangleShip<Character> b3 = new RectangleShip<Character>( new Coordinate(4, 2), 's', '*');
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
        Board<Character> b1 = new BattleShipBoard<Character>(w, h,'X');
        BoardTextView view = new BoardTextView(b1);
        assertEquals(expectedHeader, view.makeHeader());
        String expected = expectedHeader + expectedBody + expectedHeader;
        assertEquals(expected, view.displayMyOwnBoard());
    }
    
    private void notEmptyBoardHelper(int w, int h, String expectedBody,LinkedList<Ship<Character>> list){
        Board<Character> b1 = new BattleShipBoard<Character>(w, h,'X');
        for (Ship<Character> ship : list) {
            b1.tryAddShip(ship); 
        }
        BoardTextView view = new BoardTextView(b1);
        String expectedHeader = view.makeHeader();
        String expected = expectedHeader + expectedBody + expectedHeader;
        assertEquals(expected, view.displayMyOwnBoard());
    }

 
    @Test
    public void test_fire_enemy_display(){
        LinkedList<Ship<Character>> shipList = new LinkedList<>();
        RectangleShip<Character> b1 = new RectangleShip<Character>( new Coordinate(0, 1), 's', '*');
        RectangleShip<Character> b2 = new RectangleShip<Character>( new Coordinate(0, 0), 's', '*');
        RectangleShip<Character> b3 = new RectangleShip<Character>( new Coordinate(4, 2), 's', '*');
        shipList.add(b1);
        shipList.add(b2);
        shipList.add(b3);
       
        Board<Character> board = new BattleShipBoard<Character>(3, 5,'X');
        for (Ship<Character> ship : shipList) {
            board.tryAddShip(ship); 
        }
        board.fireAt(new Coordinate(0,1));

        board.fireAt(new Coordinate(1,1));

        BoardTextView view = new BoardTextView(board);
        String expectedHeader = view.makeHeader();
         String expectedBody = 
                "A  |s|  A\n" +
                "B  |X|  B\n" +
                "C  | |  C\n" +
                "D  | |  D\n" +
                "E  | |  E\n";
        String expected = expectedHeader + expectedBody + expectedHeader;
        assertEquals(expected, view.displayEnemyBoard());
        
    }

    @Test
    public void test_display_my_board_with_enemy_board(){
        Board<Character> myBoard = new BattleShipBoard<Character>(10, 20,'X');
        Board<Character> enemyBoard = new BattleShipBoard<Character>(10, 20,'X');
        BoardTextView myView = new BoardTextView(myBoard);
        BoardTextView enemyView = new BoardTextView(enemyBoard);
        System.out.print(myView.displayMyBoardWithEnemyNextToIt(enemyView, "myHeader", "enemyHeader"));
        
    }
}
