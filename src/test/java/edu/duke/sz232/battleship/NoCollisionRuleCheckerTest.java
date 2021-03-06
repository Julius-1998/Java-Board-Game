package edu.duke.sz232.battleship;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class NoCollisionRuleCheckerTest {
    
    @Test
    public void testNoCollisionRuleChecker(){
        NoCollisionRuleChecker<Character> noCollisionRuleChecker = new NoCollisionRuleChecker<>(null);
        BattleShipBoard<Character> battleShipBoard = new BattleShipBoard<>(10, 10,noCollisionRuleChecker,'X');
        V1ShipFactory v1ShipFactory = new V1ShipFactory();
        Ship<Character> s1 =  v1ShipFactory.makeDestroyer(new Placement("A4H"));
        battleShipBoard.tryAddShip(s1);
        Ship<Character> s2 =  v1ShipFactory.makeBattleship(new Placement("A7V"));
        assertEquals("That placement is invalide: the ship overlaps another ship",  noCollisionRuleChecker.checkPlacement(s2, battleShipBoard));
        Ship<Character> s3 =  v1ShipFactory.makeBattleship(new Placement("B7V"));
        assertEquals(null,  noCollisionRuleChecker.checkPlacement(s3, battleShipBoard));
    }

    @Test
    public void testMultipleRuleChecker(){
        NoCollisionRuleChecker<Character> noCollisionRuleChecker = new NoCollisionRuleChecker<>(
            new InBoundsRuleChecker<>(null)
        );
        BattleShipBoard<Character> battleShipBoard = new BattleShipBoard<>(10, 10,noCollisionRuleChecker,'X');
        V1ShipFactory v1ShipFactory = new V1ShipFactory();
        Ship<Character> s1 =  v1ShipFactory.makeDestroyer(new Placement("A4H"));
        battleShipBoard.tryAddShip(s1);
        Ship<Character> s2 =  v1ShipFactory.makeBattleship(new Placement("A7V"));
        assertEquals("That placement is invalide: the ship overlaps another ship",  noCollisionRuleChecker.checkPlacement(s2, battleShipBoard));
        Ship<Character> s3 =  v1ShipFactory.makeBattleship(new Placement("B7V"));
        assertEquals(null,  noCollisionRuleChecker.checkPlacement(s3, battleShipBoard));
        Ship<Character> s4 =  v1ShipFactory.makeDestroyer(new Placement("A5H"));
        assertEquals("That placement is invalide: the ship overlaps another ship",  noCollisionRuleChecker.checkPlacement(s4, battleShipBoard));
    }
}
