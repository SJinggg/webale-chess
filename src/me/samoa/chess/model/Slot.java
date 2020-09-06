package me.samoa.chess.model;

public class Slot {
  private int x, y;
  private boolean occupied = false;

  public Slot(int x, int y) {
    this.x = x;
    this.y = y;
  }  

  public int getX() {
    return x;
  }

  public int getY() {
    return y;
  }

  public void setX(int x) {
    this.x = x;
  }

  public void setY(int y) {
    this.y = y;
  }

  public void setOccupied(boolean occupied) {
    this.occupied = occupied;
  }
  
  public boolean isOccupied() {
    return occupied;
  }
  // pass in what? piece or slot? shd be piece, cuz need check player also, if it is enemy or not
  // dahla we don't really have a way to check- where did occupy gooo
  // why u dont continue aduh jiejie dont gooooooo

  //aduh idk
  

}