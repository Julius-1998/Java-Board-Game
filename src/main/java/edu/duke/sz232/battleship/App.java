package edu.duke.sz232.battleship;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.Reader;

/**
 * App
 */
public class App {
    private final Board<Character> theBoard;
    private final BoardTextView view;
    private final BufferedReader inputReader;
    private final PrintStream out;


    public static void main(String[] args) throws IOException{
        Board<Character> theBoard = new BattleShipBoard<>(10, 20);
        App app = new App(theBoard, new InputStreamReader(System.in), System.out);
        app.doOnePlacement();
        
    }
    public App(Board<Character> theBoard, Reader inputSource, PrintStream out) {
        this.theBoard = theBoard;
        this.view = new BoardTextView(theBoard);
        this.inputReader = new BufferedReader(inputSource);
        this.out =out;
    }

    
    /** 
     * Read one certain replacement with prefix prompt
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
     * @throws IOException
     */
    public void doOnePlacement() throws IOException{
        Placement placement = readPlacement("Where would you like to put your ship?");
        RectangleShip<Character> s = new RectangleShip<Character>(placement.getWhere(), 's', '*');
        theBoard.tryAddShip(s);
        out.println(view.displayMyOwnBoard());
    }

    
    /** 
     * @return BoardTextView
     */
    public BoardTextView getBoardTextView(){
        return view;
    }
    
}