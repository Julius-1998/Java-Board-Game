package edu.duke.sz232.battleship;

import java.io.IOException;

/**
 * The interface for player and computer player
 */
public interface Player {
    /**
     * Do the placements
     * 
     * @throws IOException
     */
    public void doPlacementPhase() throws IOException;

    /**
     * Do one attack phase
     * 
     * @param view        the enemy's view
     * @param b           the enemy's board
     * @param otherPlayer the enemy
     * @return if the game has ended
     * @throws IOException
     */
    public boolean doAttackingPhase(BoardTextView view, Board<Character> b, Player otherPlayer) throws IOException;

    /**
     * Get the board's view
     * 
     * @return
     */
    public BoardTextView getBoardTextView();

    /**
     * 
     * @return if the player has lost
     */
    public boolean hasLost();
}