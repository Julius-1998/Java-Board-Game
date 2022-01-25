package edu.duke.sz232.battleship;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class BattleShipBoardTest {

  @Test
  public void test_width_and_height() {
    Board<Character> b1 = new BattleShipBoard<Character>(20, 10);
    assertEquals(10, b1.getHeight());
    assertEquals(20, b1.getWidth());
  }

  @Test
  public void test_invalid_dimensions() {
    assertThrows(IllegalArgumentException.class, () -> new BattleShipBoard<Character>(10, 0));
    assertThrows(IllegalArgumentException.class, () -> new BattleShipBoard<Character>(0, 20));
    assertThrows(IllegalArgumentException.class, () -> new BattleShipBoard<Character>(10, -5));
    assertThrows(IllegalArgumentException.class, () -> new BattleShipBoard<Character>(-8, 20));
  }

  @Test
  public void test_add_ship(){
    BattleShipBoard<Character> b1 = new BattleShipBoard<>(20, 10);
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
    BattleShipBoard<Character> b1 = new BattleShipBoard<>(20, 10);
    Character[][] board = new Character[10][20];
    assertEquals("That placement is invalid: the ship goes off the bottom of the board.",b1.tryAddShip(new RectangleShip<Character>("Testship",new Coordinate(3,5),10,10,'s','x')));
    

  }

  
  private <T> void checkWhatIsAtBoard(BattleShipBoard<T> b, T[][] expected){
    for(int i = 0;i < b.getWidth();i++){
      for(int j = 0;j < b.getHeight();j++){
        assertEquals(expected[j][i], b.whatIsAt(new Coordinate(j,i)));
      }
    }
  }

}

