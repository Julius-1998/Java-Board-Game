package edu.duke.sz232.battleship;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class BattleShipBoardTest {

  @Test
  public void test_width_and_height() {
    Board<Character> b1 = new BattleShipBoard<Character>(20, 10,'X');
    assertEquals(10, b1.getHeight());
    assertEquals(20, b1.getWidth());
  }

  @Test
  public void test_invalid_dimensions() {
    assertThrows(IllegalArgumentException.class, () -> new BattleShipBoard<Character>(10, 0,'X'));
    assertThrows(IllegalArgumentException.class, () -> new BattleShipBoard<Character>(0, 20,'X'));
    assertThrows(IllegalArgumentException.class, () -> new BattleShipBoard<Character>(10, -5,'X'));
    assertThrows(IllegalArgumentException.class, () -> new BattleShipBoard<Character>(-8, 20,'X'));
  }

  @Test
  public void test_add_ship(){
    BattleShipBoard<Character> b1 = new BattleShipBoard<>(20, 10,'X');
    Character[][] board = new Character[10][20];
    checkWhatIsAtBoard(b1, board);
    b1.tryAddShip(new RectangleShip<Character>(new Coordinate(3,5),'s','*'));
    board[3][5] = 's';
    checkWhatIsAtBoard(b1, board);
    b1.tryAddShip(new RectangleShip<Character>(new Coordinate(9,19),'s','*'));
    board[9][19] = 's';
    checkWhatIsAtBoard(b1, board);

  }

  @Test
  public void test_invalid_add_ship(){
    BattleShipBoard<Character> b1 = new BattleShipBoard<>(20, 10,'X');
    Character[][] board = new Character[10][20];
    assertEquals("That placement is invalid: the ship goes off the bottom of the board.",b1.tryAddShip(new RectangleShip<Character>("Testship",new Coordinate(3,5),10,10,'s','x')));
    

  }

  @Test
  public void test_fire_at(){
    BattleShipBoard<Character> b1 = new BattleShipBoard<>(20, 10,'X');
    V1ShipFactory shipFactory = new V1ShipFactory();
    Ship<Character> s1 = shipFactory.makeBattleship(new Placement("A4V"));
    b1.tryAddShip(s1);
    assertEquals('b', b1.whatIsAtForSelf(new Coordinate(0,4)));
    assertEquals(null, b1.whatIsAtForEnemy(new Coordinate(0,4)));
    assertSame(s1, b1.fireAt(new Coordinate(0,4)));
    assertEquals('x', b1.whatIsAtForSelf(new Coordinate(0,4)));
    assertEquals('b', b1.whatIsAtForEnemy(new Coordinate(0,4)));
    assertSame(s1, b1.fireAt(new Coordinate(1,4)));
    assertEquals(false, s1.isSunk());
    assertSame(s1, b1.fireAt(new Coordinate(2,4)));
    assertEquals(true, s1.isSunk());

  }
  @Test
  public void test_fire_miss(){
    BattleShipBoard<Character> b1 = new BattleShipBoard<>(20, 10,'X');
    V1ShipFactory shipFactory = new V1ShipFactory();
    Ship<Character> s1 = shipFactory.makeBattleship(new Placement("A4V"));
    b1.tryAddShip(s1);
    assertSame(null, b1.fireAt(new Coordinate(0,0)));

  }

  @Test
  public void test_has_all_sunk(){
    BattleShipBoard<Character> b1 = new BattleShipBoard<>(20, 10,'X');
    V1ShipFactory shipFactory = new V1ShipFactory();
    Ship<Character> s1 = shipFactory.makeSubmarine(new Placement("A4V"));
    b1.tryAddShip(s1);
    b1.fireAt(new Coordinate("A4"));
    assertEquals(b1.hasAllSunk(), false);
    b1.fireAt(new Coordinate("B4"));
    assertEquals(b1.hasAllSunk(), true);
  }
  
  @Test
  public void test_out_of_boundry(){
    BattleShipBoard<Character> b1 = new BattleShipBoard<>(20, 9,'X');
    V1ShipFactory shipFactory = new V1ShipFactory();
    Ship<Character> s1 = shipFactory.makeSubmarine(new Placement("A4V"));
    b1.tryAddShip(s1);
    b1.fireAt(new Coordinate("A4"));
    assertEquals(b1.hasAllSunk(), false);
    assertThrows(IllegalArgumentException.class, ()->b1.fireAt(new Coordinate("U4")));
    assertThrows(IllegalArgumentException.class, ()->b1.fireAt(new Coordinate("A9")));    
  }
  
  private <T> void checkWhatIsAtBoard(BattleShipBoard<T> b, T[][] expected){
    for(int i = 0;i < b.getWidth();i++){
      for(int j = 0;j < b.getHeight();j++){
        assertEquals(expected[j][i], b.whatIsAtForSelf(new Coordinate(j,i)));
      }
    }
  }

}

