package edu.duke.sz232.battleship;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Function;

class ComputerPlayer implements Player {
    private final Board<Character> theBoard;
    private final BoardTextView view;
    private final BufferedReader inputReader;
    private final PrintStream out;
    private final AbstractShipFactory<Character> shipFactory;
    String name;

    final ArrayList<String> shipsToPlace;
    final HashMap<String, Function<Placement, Ship<Character>>> shipCreationFns;
    int sonarUsage;
    int moveUsage;
    List<Placement> placements;
    List<Coordinate> coordinates;

    public ComputerPlayer(String name, Board<Character> theBoard, BufferedReader inputSource, PrintStream out,
            AbstractShipFactory<Character> factory) {
        this.theBoard = theBoard;
        this.view = new BoardTextView(theBoard);
        this.inputReader = inputSource;
        this.out = out;
        this.name = name;
        this.shipFactory = factory;
        this.sonarUsage = 1;
        this.moveUsage = 2;
        shipsToPlace = new ArrayList<>();
        shipCreationFns = new HashMap<>();
        setupShipCreationMap();
        setupShipCreationList();
        this.placements = setPlacements();
        this.coordinates = setCoordinates();
    }

    public List<Placement> setPlacements() {
        List<Placement> placements = new LinkedList<>();
        placements.add(new Placement("a0v"));
        placements.add(new Placement("a1v"));
        placements.add(new Placement("a2v"));
        placements.add(new Placement("a3v"));
        placements.add(new Placement("a4v"));
        placements.add(new Placement("a5r"));
        placements.add(new Placement("a7r"));
        placements.add(new Placement("d0r"));
        placements.add(new Placement("f0r"));
        placements.add(new Placement("h0r"));
        return placements;
    }

    public List<Coordinate> setCoordinates() {
        List<Coordinate> coordinates = new LinkedList<>();
        for (int i = 0; i < theBoard.getHeight(); i++) {
            for (int j = 0; j < theBoard.getWidth(); j++) {
                Coordinate c = new Coordinate(j, i);
                coordinates.add(c);
            }
        }
        return coordinates;
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

    @Override
    public void doPlacementPhase() throws IOException {
        out.print(view.displayMyOwnBoard());
        out.print(
                "--------------------------------------------------------------------------------\n" +
                        "Computer player " + name + ": you are going to place the following ships (which are all\n" +
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

    public void doOnePlacement(String shipName, Function<Placement, Ship<Character>> createFn) throws IOException {
        while (true) {
            try {
                out.println("Player " + name + " where do you want to place a " + shipName + "?");
                Placement p = placements.remove(0);
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

    @Override
    public boolean doAttackingPhase(BoardTextView enemyView, Board<Character> enemyBoard, Player enemyPlayer)
            throws IOException {
        out.print("Computer is attacking\n");
        playOneTurn(enemyView, enemyBoard);
        if (enemyPlayer.hasLost()) {
            out.println("Player " + name + " has won!");
            return false;
        }
        return true;
    }

    public void playOneTurn(BoardTextView enemyView, Board<Character> enemyBoard) throws IOException {
        String myHeader = "Computer's ocean";
        String enemyHeader = "Enemy's ocean";
        out.print(view.displayMyBoardWithEnemyNextToIt(enemyView, myHeader, enemyHeader));
        out.print(promptAttack(enemyBoard));
    }

    public String promptAttack(Board<Character> enemyBoard) {
        Coordinate c = coordinates.remove(0);
        Ship<Character> s = enemyBoard.fireAt(c);
        if (s == null) {
            return "Computer missed!\n";
        }
        return "Computer hit a " + s.getName() + "!\n";
    }

    @Override
    public BoardTextView getBoardTextView() {
        return view;
    }

    @Override
    public boolean hasLost() {
        return theBoard.hasAllSunk();
    }

}