package edu.duke.sz232.battleship;

import java.io.IOException;

public interface Player {
    public void doPlacementPhase()throws IOException;
    public boolean doAttackingPhase(BoardTextView view,Board<Character>b,Player othePlayer) throws IOException;
    public BoardTextView getBoardTextView();
    public boolean hasLost();
}