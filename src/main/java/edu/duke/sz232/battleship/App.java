package edu.duke.sz232.battleship;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.Reader;

/**
 * App where the program starts
 */
public class App {
   
    static TextPlayer player1;
    static TextPlayer player2;
    public static void main(String[] args) throws IOException {
        Board<Character> b1 = new BattleShipBoard<Character>(10, 20,'X');
        Board<Character> b2 = new BattleShipBoard<Character>(10, 20,'X');
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        V1ShipFactory factory = new V1ShipFactory();
        player1 = new TextPlayer("A", b1, input, System.out, factory);
        player2 = new TextPlayer("B", b2, input, System.out, factory);
        doPlacementPhase();
        //While neither of the players has won, continue
        while(true){
            if(!player1.doAttackingPhase(player2.getBoardTextView(), b2, player2)){return;};
            if(!player2.doAttackingPhase(player1.getBoardTextView(), b1, player1)){return;};
        }
    }
    //When doing placementPhase, do then one by one
    public static void doPlacementPhase()throws IOException{
        player1.doPlacementPhase();
        player2.doPlacementPhase();
    }


   
   

}