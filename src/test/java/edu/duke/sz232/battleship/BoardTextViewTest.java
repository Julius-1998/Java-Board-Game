package edu.duke.sz232.battleship;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
}
