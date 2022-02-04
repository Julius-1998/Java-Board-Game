package edu.duke.sz232.battleship;

import java.io.BufferedReader;
import java.io.EOFException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.function.Function;

public class TextPlayer {
    private final Board<Character> theBoard;
    private final BoardTextView view;
    private final BufferedReader inputReader;
    private final PrintStream out;
    private final AbstractShipFactory<Character> shipFactory;
    String name;

    final ArrayList<String> shipsToPlace;
    final HashMap<String, Function<Placement, Ship<Character>>> shipCreationFns;

    public TextPlayer(String name, Board<Character> theBoard, BufferedReader inputSource, PrintStream out,
            V1ShipFactory factory) {
        this.theBoard = theBoard;
        this.view = new BoardTextView(theBoard);
        this.inputReader = inputSource;
        this.out = out;
        this.name = name;
        this.shipFactory = factory;
        shipsToPlace = new ArrayList<>();
        shipCreationFns = new HashMap<>();
        setupShipCreationMap();
        setupShipCreationList();
    }

    protected void setupShipCreationMap() {
        shipCreationFns.put("Submarine", (p) -> shipFactory.makeSubmarine(p));
        shipCreationFns.put("Destroyer", (p) -> shipFactory.makeDestroyer(p));
        shipCreationFns.put("Battleship", (p) -> shipFactory.makeBattleship(p));
        shipCreationFns.put("Carrier", (p) -> shipFactory.makeCarrier(p));

    }

    protected void setupShipCreationList() {
        shipsToPlace.addAll(Collections.nCopies(2, "Submarine"));
        shipsToPlace.addAll(Collections.nCopies(3, "Destroyer"));
        shipsToPlace.addAll(Collections.nCopies(3, "Battleship"));
        shipsToPlace.addAll(Collections.nCopies(2, "Carrier"));

    }

    /**
     * Read one certain replacement with prefix prompt
     * 
     * @param prompt
     * @return Placement
     * @throws IOException
     */
    public Placement readPlacement(String prompt) throws IOException {
        out.println(prompt);
        String s = inputReader.readLine();
        if (s == null) {
            throw new EOFException();
        }
        return new Placement(s);
    }

    /**
     * Read one coordinate from input
     * 
     * @param prompt
     * @return Coordinate from input
     * @throws IOException
     */
    public Coordinate readCoordinate(String prompt) throws IOException {
        out.println(prompt);
        String s = inputReader.readLine();
        if (s == null) {
            throw new EOFException();
        }
        return new Coordinate(s);
    }

    /**
     * Make one replacement
     * 
     * @param shipName is the ship's name
     * @param createFn is the the builder
     * @throws IOException
     */
    public void doOnePlacement(String shipName, Function<Placement, Ship<Character>> createFn) throws IOException {
        while (true) {
            try {
                Placement p = readPlacement("Player " + name + " where do you want to place a " + shipName + "?");
                Ship<Character> s = createFn.apply(p);
                String err = theBoard.tryAddShip(s);
                if (err != null) {
                    out.println(err);
                } else {
                    out.print(view.displayMyOwnBoard());
                    break;
                }
            } catch (IllegalArgumentException e) {
                out.println("Invalid placement String");
            }
        }
    }

    /**
     * get the view of player
     * 
     * @return the view of the board
     */
    public BoardTextView getBoardTextView() {
        return view;
    }

    /**
     * Do one place ment for player
     * 
     * @throws IOException
     */
    public void doPlacementPhase() throws IOException {
        out.print(view.displayMyOwnBoard());
        out.print(
                "--------------------------------------------------------------------------------\n" +
                        "Player " + name + ": you are going to place the following ships (which are all\n" +
                        "rectangular). For each ship, type the coordinate of the upper left\n" +
                        "side of the ship, followed by either H (for horizontal) or V (for\n" +
                        "vertical).  For example M4H would place a ship horizontally starting\n" +
                        "at M4 and going to the right.  You have\n" +
                        "\n" +
                        "2 \"Submarines\" ships that are 1x2 \n" +
                        "3 \"Destroyers\" that are 1x3\n" +
                        "3 \"Battleships\" that are 1x4\n" +
                        "2 \"Carriers\" that are 1x6\n" +
                        "--------------------------------------------------------------------------------\n");
        for (String shipName : shipsToPlace) {
            Function<Placement, Ship<Character>> f = shipCreationFns.get(shipName);
            doOnePlacement(shipName, f);
        }

    }

    /**
     * A player loses when all ships on the board is sunk
     * 
     * @return
     */
    public boolean hasLost() {
        return theBoard.hasAllSunk();
    }

    public void playOneTurn(BoardTextView enemyView, Board<Character> enemyBoard) throws IOException {
        String myHeader = "Your ocean";
        String enemyHeader = "Enemy's ocean";
        out.print(view.displayMyBoardWithEnemyNextToIt(enemyView, myHeader, enemyHeader));
        out.print(promptAttack(enemyBoard));
    }

    public String promptAttack(Board<Character> enemyBoard) throws IOException {
        String prompt = "Player " + name + ",Please enter your coordinate to attack:";
        while (true) {
            try {
                Coordinate c = readCoordinate(prompt);
                Ship<Character> s = enemyBoard.fireAt(c);
                if (s == null) {
                    return "You missed!\n";
                }
                return "You hit a " + s.getName() + "!\n";
            } catch (IllegalArgumentException e) {
                out.println("Invalid input coordinate!");
            }
        }
    }

    public boolean doAttackingPhase(BoardTextView enemyView, Board<Character> enemyBoard, TextPlayer enemyPlayer)
            throws IOException {
        playOneTurn(enemyView, enemyBoard);
        if (enemyPlayer.hasLost()) {
            out.println("Player " + name + " has won!");
            return false;
        }
        return true;
    }

}
