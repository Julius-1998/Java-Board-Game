package edu.duke.sz232.battleship;

import java.util.function.Function;

/**
 * This class handles textual display of
 * a Board (i.e., converting it to a string to show
 * to the user).
 * It supports two ways to display the Board:
 * one for the player's own board, and one for the
 * enemy's board.
 */
public class BoardTextView {
    /**
     * The Board to display
     */
    private final Board<Character> toDisplay;

    /**
     * Constructs a BoardView, given the board it will display.
     * 
     * @param toDisplay is the Board to display
     */
    public BoardTextView(Board<Character> toDisplay) {
        this.toDisplay = toDisplay;
    }

    /**
     * This makes the header line, e.g. 0|1|2|3|4\n
     * 
     * @return the String that is the header line for the given board
     */
    String makeHeader() {
        StringBuilder ans = new StringBuilder("  "); // README shows two spaces at
        String sep = ""; // start with nothing to separate, then switch to | to separate
        for (int i = 0; i < toDisplay.getWidth(); i++) {
            ans.append(sep);
            ans.append(i);
            sep = "|";
        }
        ans.append("\n");
        return ans.toString();
    }

    /**
     * This makes the display of board
     * 
     * @return The string of board
     */
    public String displayAnyBoard(Function<Coordinate, Character> getSquareFn) {
        StringBuilder ans = new StringBuilder("");
        ans.append(makeHeader());
        for (int row = 0; row < toDisplay.getHeight(); row++) {
            ans.append((char) ('A' + row));
            ans.append(" ");
            ans.append(getSquareFn.apply(new Coordinate(row, 0)) == null ? ' '
                    : getSquareFn.apply(new Coordinate(row, 0)));
            for (int col = 1; col < toDisplay.getWidth(); col++) {
                ans.append("|");
                if (getSquareFn.apply(new Coordinate(row, col)) != null) {
                    ans.append(getSquareFn.apply(new Coordinate(row, col)));
                } else {
                    ans.append(' ');
                }
            }
            ans.append(" ");
            ans.append((char) ('A' + row));
            ans.append('\n');
        }
        ans.append(makeHeader());
        return ans.toString();
    }

    public String displayMyOwnBoard() {
        return displayAnyBoard((c) -> toDisplay.whatIsAtForSelf(c));
    }

    public String displayEnemyBoard() {
        return displayAnyBoard((c) -> toDisplay.whatIsAtForEnemy(c));

    }

    public String displayMyBoardWithEnemyNextToIt(BoardTextView enemyView, String myHeader, String enemyHeader) {
        StringBuilder sb = new StringBuilder();
        String[] myLines = displayMyOwnBoard().split("\n");
        String[] enemyLines = enemyView.displayEnemyBoard().split("\n");
        int space = 16;
        String commonHeader = generateHeaders(myHeader, enemyHeader, space);
        sb.append(commonHeader);
        for (int i = 0; i < myLines.length; i++) {
            sb.append(myLines[i]);
            for (int j = 0; j < space; j++) {
                sb.append(" ");
            }
            if (i == 0 || i == myLines.length - 1) {
                sb.append("  ");
            }
            sb.append(enemyLines[i]);
            sb.append("\n");
        }
        return sb.toString();
    }

    public String generateHeaders(String myHeader, String enemyHeader, int space) {
        StringBuilder sb = new StringBuilder();
        sb.append(myHeader);
        for (int i = 0; i < toDisplay.getWidth()*2+space + 3 -myHeader.length();i++) {
            sb.append(" ");
        }
        sb.append(enemyHeader);
        sb.append("\n");
        return sb.toString();

    }

}