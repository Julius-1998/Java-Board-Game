package edu.duke.sz232.battleship;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;

/**
 * App where the program starts
 */
public class App {

    static Player player1;
    static Player player2;

    public static void main(String[] args) throws IOException {
        Board<Character> b1 = new BattleShipBoard<Character>(10, 20, 'X');
        Board<Character> b2 = new BattleShipBoard<Character>(10, 20, 'X');
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        V2ShipFactory factory = new V2ShipFactory();
        player1 = initPlayer("A", b1, input, System.out, factory);
        player2 = initPlayer("B", b2, input, System.out, factory);
        doPlacementPhase();
        // While neither of the players has won, continue
        while (true) {
            if (!player1.doAttackingPhase(player2.getBoardTextView(), b2, player2)) {
                return;
            }

            if (!player2.doAttackingPhase(player1.getBoardTextView(), b1, player1)) {
                return;
            }

        }
    }

    /**
     * This function initialize a player as computer player or human player
     * @param name the player's name
     * @param theBoard the player's board
     * @param inputSource the inputSource of player
     * @param out  the output of player
     * @param factory ship factory
     * @return the constructed player
     * @throws IOException
     */
    public static Player initPlayer(String name, Board<Character> theBoard, BufferedReader inputSource,
            PrintStream out,
            AbstractShipFactory<Character> factory) throws IOException {
        while (true) {
            try {
                String prompt = "Player " + name + " is a:\n" +
                        "1. human player\n" +
                        "2. computer player\n" +
                        "Please enter 1 or 2 to select";
                out.println(prompt);
                String type = inputSource.readLine();
                if (type.equals("1")) {
                    out.println("Generating textPlayer");
                    return new TextPlayer(name, theBoard, inputSource, out, factory);
                } else if (type.equals("2")) {
                    out.println("Generating ComputerPlayer");
                    return new ComputerPlayer(name, theBoard, inputSource, out, factory);
                }
                if (!type.equals("1") || type.equals("2")) {
                    throw new IllegalArgumentException();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    // When doing placementPhase, do then one by one
    public static void doPlacementPhase() throws IOException {
        player1.doPlacementPhase();
        player2.doPlacementPhase();
    }

}