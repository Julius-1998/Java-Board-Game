package edu.duke.sz232.battleship;

import java.util.HashMap;

public interface Board<T> {
  public int getWidth();

  public int getHeight();

  public T whatIsAtForSelf(Coordinate where);

  public T whatIsAtForEnemy(Coordinate where);

  public String tryAddShip(Ship<T> toAdd);

  public Ship<T> fireAt(Coordinate where);

  public boolean hasAllSunk();

  public boolean isValid(Coordinate where);

  public Ship<T> removeShip(Coordinate where);

  public HashMap<String,Integer> sonar(Coordinate where);
}
