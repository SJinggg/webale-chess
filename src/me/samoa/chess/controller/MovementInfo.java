package me.samoa.chess.controller;

public class MovementInfo {
  
  private int row;
  private int col;
  private boolean hasOpponent;

  public MovementInfo(int x, int y, boolean hasOpponent) {
    this.row = x;
    this.col = y;
    this.hasOpponent = hasOpponent;
  }

  public int getRow() {
    return this.row;
  }

  public int getCol() {
    return this.col;
  }

  public boolean hasOpponent() {
    return this.hasOpponent;
  }

}