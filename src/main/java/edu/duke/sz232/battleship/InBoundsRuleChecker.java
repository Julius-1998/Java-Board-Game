package edu.duke.sz232.battleship;

public class InBoundsRuleChecker<T> extends PlacementRuleChecker<T> {
    public InBoundsRuleChecker(PlacementRuleChecker<T> next) {
        super(next);
        //TODO Auto-generated constructor stub
    }

    @Override
    protected boolean checkMyRule(Ship<T> theShip, Board<T> theBoard) {
       Iterable<Coordinate> coordinateSet = theShip.getCoordinates();
       for (Coordinate coordinate : coordinateSet) {
           int c = coordinate.getColumn();
           int r = coordinate.getRow();
           if ( c > theBoard.getWidth()-1) {
               return false;
           }
           if (r > theBoard.getHeight()) {
               return false;
           }
       }
       return true;
    }
}
