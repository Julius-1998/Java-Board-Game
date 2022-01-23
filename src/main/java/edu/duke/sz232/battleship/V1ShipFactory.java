package edu.duke.sz232.battleship;

public class V1ShipFactory implements AbstractShipFactory<Character> {
    /**
     * Factory method for submarine
     * 
     * @param where UpperLeft corner of the ship
     */
    @Override
    public Ship<Character> makeSubmarine(Placement where) {
        return createShip(where, 1, 2, 's', "Submarine");
    }

    /**
     * Factory method for Battleship
     * 
     * @param where UpperLeft corner of the ship
     */
    @Override
    public Ship<Character> makeBattleship(Placement where) {
        return createShip(where, 1, 3, 'b', "Battleship");

    }

    /**
     * Factory method for Carrier
     * 
     * @param where UpperLeft corner of the ship
     */
    @Override
    public Ship<Character> makeCarrier(Placement where) {
        return createShip(where, 1, 4, 'c', "Carrier");

    }

    /**
     * Factory method for Destroyer
     * 
     * @param where UpperLeft corner of the ship
     */
    @Override
    public Ship<Character> makeDestroyer(Placement where) {
        return createShip(where, 1, 6, 'd', "Destroyer");

    }

    /**
     * Create ship method for the factory
     * 
     * @param where  UpperLeft coordinate of the ship
     * @param w      ship's width
     * @param h      ship's height
     * @param letter ship's symbol
     * @param name   ship's name
     * @return
     */
    protected Ship<Character> createShip(Placement where, int w, int h, char letter, String name) {
        char c = where.getOrientation();
        if (c == 'h' || c == 'H') {
            int temp = h;
            h = w;
            w = temp;
        }
        return new RectangleShip<Character>(name, where.getWhere(), w, h, letter, 'x');

    }

}
