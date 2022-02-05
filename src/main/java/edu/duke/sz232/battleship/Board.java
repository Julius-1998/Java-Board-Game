package edu.duke.sz232.battleship;

import java.util.HashMap;

/**
 * This interface describes what a board should do
 */
public interface Board<T> {
  public int getWidth();

  public int getHeight();

  /**
   * Get the represent of coordinate for self
   * @param where
   * @return
   */
  public T whatIsAtForSelf(Coordinate where);

  /**
   * Get the represent of coordinate for enemy
   * This function is used to get the display of enemy's view
   * @param where
   * @return
   */
  public T whatIsAtForEnemy(Coordinate where);

  /**
   * Try add ship
   * @param toAdd the ship to be added
   * @return null if exception happens, otherwise return the successful add message
   */
  public String tryAddShip(Ship<T> toAdd);

  /**
   * fire at place
   * @param where the place to fire
   * @return the ship being fired at
   */
  public Ship<T> fireAt(Coordinate where);

  /**
   * if the ships have all sunk
   * @return
   */
  public boolean hasAllSunk();

  /**
   * return if a coordinate is within the range of board
   * @param where
   * @return
   */
  public boolean isValid(Coordinate where);

  /**
   * remove the ship at coordinate
   * @param where 
   * @return the ship being removed
   */
  public Ship<T> removeShip(Coordinate where);

  /**
   * Do sonar scan at certain space
   * @param where
   * @return the hashmap of <name,count> of ship pieces
   */
  public HashMap<String,Integer> sonar(Coordinate where);
}
