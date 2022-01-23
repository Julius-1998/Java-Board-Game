package edu.duke.sz232.battleship;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;

import org.junit.jupiter.api.Test;

public class InBoundsRuleCheckerTest {
    @Test
    public void testInboundsRule(){
        InBoundsRuleChecker<Character> inBoundsRuleChecker = new InBoundsRuleChecker<>(null); 
        BattleShipBoard<Character> battleShipBoard = new BattleShipBoard<>(10, 10,inBoundsRuleChecker);
        V1ShipFactory v1ShipFactory = new V1ShipFactory();
        Ship<Character> s1 =  v1ShipFactory.makeDestroyer(new Placement("A5H"));
        assertEquals(inBoundsRuleChecker.checkPlacement(s1,battleShipBoard),false);
        Ship<Character> s2 =  v1ShipFactory.makeBattleship(new Placement("A4H"));
        assertEquals(inBoundsRuleChecker.checkPlacement(s2,battleShipBoard),true);
        Ship<Character> s3=  v1ShipFactory.makeDestroyer(new Placement("E5V"));
        assertEquals(inBoundsRuleChecker.checkPlacement(s3,battleShipBoard),false);


    }
}
