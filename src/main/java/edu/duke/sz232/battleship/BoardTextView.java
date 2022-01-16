package edu.duke.sz232.battleship;

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
     * @return The empty board string
     */
    public String displayMyOwnBoard() {
        StringBuilder ans = new StringBuilder("");
        ans.append(makeHeader());
        for (int i = 0; i < toDisplay.getHeight(); i++) {
            ans.append((char) ('A' + i));
            ans.append(" ");
            for (int j = 0; j < toDisplay.getWidth() - 1; j++) {
                ans.append(" |");
            }
            ans.append("  ");
            ans.append((char) ('A' + i));
            ans.append('\n');
        }
        ans.append(makeHeader());
        return ans.toString(); // this is a placeholder for the moment
    }
}