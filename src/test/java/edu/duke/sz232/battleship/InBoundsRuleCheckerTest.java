package edu.duke.sz232.battleship;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class InBoundsRuleCheckerTest {
    @Test
    public void testInboundsRule(){
        InBoundsRuleChecker<Character> inBoundsRuleChecker = new InBoundsRuleChecker<>(null); 
        BattleShipBoard<Character> battleShipBoard = new BattleShipBoard<>(6,6 ,inBoundsRuleChecker,'X');
        V1ShipFactory v1ShipFactory = new V1ShipFactory();
        Ship<Character> s1 =  v1ShipFactory.makeDestroyer(new Placement("A5H"));
        assertEquals(inBoundsRuleChecker.checkPlacement(s1,battleShipBoard),"That placement is invalid: the ship goes off the right of the board.");
        Ship<Character> s2 =  v1ShipFactory.makeBattleship(new Placement("A0H"));
        assertEquals(inBoundsRuleChecker.checkPlacement(s2,battleShipBoard),null);
        Ship<Character> s3=  v1ShipFactory.makeDestroyer(new Placement("F5V"));
        assertEquals(inBoundsRuleChecker.checkPlacement(s3,battleShipBoard),"That placement is invalid: the ship goes off the bottom of the board.");


    }
}
