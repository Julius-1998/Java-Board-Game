package edu.duke.sz232.battleship;

/**
 * A rule checker to check if the ship is not overlapping
 */
public class NoCollisionRuleChecker<T> extends PlacementRuleChecker<T> {
    public NoCollisionRuleChecker(PlacementRuleChecker<T> next) {
        super(next);
    }

    /**
     * The method is checking the ship's validity
     * 
     * @param theShip  the ship to be checked
     * @param theBoard the board to be checked with
     */
    @Override
    protected String checkMyRule(Ship<T> theShip, Board<T> theBoard) {
        Iterable<Coordinate> set = theShip.getCoordinates();
        for (Coordinate coordinate : set) {
            if (theBoard.whatIsAt(coordinate) != null) {
                return "That placement is invalide: the ship overlaps another ship";
            }
        }
        return null;

    }
}
