package edu.duke.sz232.battleship;

public class V2ShipFactory implements AbstractShipFactory<Character> {

    @Override
    public Ship<Character> makeSubmarine(Placement where) {
        return createRectangleShip(where, 1, 2, 's', "Submarine");
    }

    @Override
    public Ship<Character> makeBattleship(Placement where) {
        return createIrregularShip(where, 'b', "Battleship");
    }

    @Override
    public Ship<Character> makeCarrier(Placement where) {
        return createIrregularShip(where, 'c', "Carrier");
    }

    @Override
    public Ship<Character> makeDestroyer(Placement where) {
        return createRectangleShip(where, 1, 3, 'd', "Destroyer");
    }
    
    /**
     * The create function used to create IrregularShip
     * @param where the place of ship
     * @param letter the represent letter of ship
     * @param name the name of the ship
     * @return
     */
    protected Ship<Character> createIrregularShip(Placement where, char letter, String name) {
        char c = where.getOrientation();
        if (c != 'U' && c != 'R' && c != 'D' && c != 'L'){
            throw new IllegalArgumentException();
        } 
        return new IrregularShip<Character>(name, where.getWhere(),c, letter, 'x');

    }

    /**
     * The function used to generate irregular ship
     * @param where the place of ship
     * @param w the width of ship
     * @param h the height of ship
     * @param letter the representing letter of ship
     * @param name the name of ship
     * @return
     */
    protected Ship<Character> createRectangleShip(Placement where, int w, int h, char letter, String name) {
        char c = where.getOrientation();
        if (c != 'H' && c != 'V') {
            throw new IllegalArgumentException();
        } 
        if (c == 'H') {
            int temp = h;
            h = w;
            w = temp;
        }
        return new RectangleShip<Character>(name, where.getWhere(), w, h, letter, 'x');

    }

}
