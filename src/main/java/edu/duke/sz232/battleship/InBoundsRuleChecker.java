package edu.duke.sz232.battleship;

/**
 * A rule checker to check if the ship is within the range of board
 */
public class InBoundsRuleChecker<T> extends PlacementRuleChecker<T> {
    public InBoundsRuleChecker(PlacementRuleChecker<T> next) {
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
        Iterable<Coordinate> coordinateSet = theShip.getCoordinates();
        for (Coordinate coordinate : coordinateSet) {
            int c = coordinate.getColumn();
            int r = coordinate.getRow();
            if (c > theBoard.getWidth() - 1) {
                return "That placement is invalid: the ship goes off the right of the board.";
            }
            if (r > theBoard.getHeight() - 1) {
                return "That placement is invalid: the ship goes off the bottom of the board.";
            }
        }
        return null;
    }
}
