package edu.duke.sz232.battleship;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;

public class TextPlayer {
    private final Board<Character> theBoard;
    private final BoardTextView view;
    private final BufferedReader inputReader;
    private final PrintStream out;
    private final AbstractShipFactory<Character> shipFactory;
    String name;

 

     public TextPlayer(String name, Board<Character> theBoard, BufferedReader inputSource, PrintStream out, V1ShipFactory factory) {
        this.theBoard = theBoard;
        this.view = new BoardTextView(theBoard);
        this.inputReader = inputSource;
        this.out = out;
        this.name = name;
        this.shipFactory = factory;
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
        return new Placement(s);
    }

    /**
     * Make one replacement
     * 
     * @throws IOException
     */
    public void doOnePlacement() throws IOException {
        Placement placement = readPlacement("Player " + name + " Where do you want to place a destroyer?");
        Ship<Character> s = shipFactory.makeDestroyer(placement);
        theBoard.tryAddShip(s);
        out.println(view.displayMyOwnBoard());
    }

    /**
     * get the view of player
     * @return the view of the board
     */
    public BoardTextView getBoardTextView(){
        return view;
    }

    public void doPlacementPhase() throws IOException{
        view.displayMyOwnBoard();
        out.print(
        "--------------------------------------------------------------------------------\n"+
        "Player A: you are going to place the following ships (which are all\n"+
        "rectangular). For each ship, type the coordinate of the upper left\n"+
        "side of the ship, followed by either H (for horizontal) or V (for\n"+
        "vertical).  For example M4H would place a ship horizontally starting\n"+
        "at M4 and going to the right.  You have\n"+
        "\n"+
        "2 \"Submarines\" ships that are 1x2 \n"+
        "3 \"Destroyers\" that are 1x3\n"+
        "3 \"Battleships\" that are 1x4\n"+
        "2 \"Carriers\" that are 1x6\n"+
        "--------------------------------------------------------------------------------\n");
        doOnePlacement();
    }

}
