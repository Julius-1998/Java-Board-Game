package edu.duke.sz232.battleship;

public abstract class PlacementRuleChecker<T> {
    private final PlacementRuleChecker<T> next;

    /**
     * To be checked in the subclasses
     * 
     * @param theShip  The ship to be checked
     * @param theBoard The board to be checked
     * @return
     */
    protected abstract String checkMyRule(Ship<T> theShip, Board<T> theBoard);

    public PlacementRuleChecker(PlacementRuleChecker<T> next) {
        this.next = next;
    }

    public String checkPlacement(Ship<T> theShip, Board<T> theBoard) {
        // if we fail our own rule: stop the placement is not legal
        String err = checkMyRule(theShip, theBoard);
        if (err != null) {
            return err;
        }
        // other wise, ask the rest of the chain.
        if (next != null) {
            return next.checkPlacement(theShip, theBoard);
        }
        // if there are no more rules, then the placement is legal
        return null;
    }

}