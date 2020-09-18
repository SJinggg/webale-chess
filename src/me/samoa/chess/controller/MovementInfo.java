package me.samoa.chess.controller;

/**
 * Provide movement information performed
 * 
 * @author Casey Teh Qi Shi
 */
public class MovementInfo {
  
  private int row;
  private int col;
  private boolean hasOpponent;

  /**
   * Constructor of MovementInfo class
   * 
   * @param x Row of slot
   * @param y Column of slot
   * @param hasOpponent Check whether slot has opponent
   */
  public MovementInfo(int x, int y, boolean hasOpponent) {
    this.row = x;
    this.col = y;
    this.hasOpponent = hasOpponent;
  }

  /**
   * Get row of slot
   * 
   * @return Slot's row
   */
  public int getRow() {
    return this.row;
  }

  /**
   * Get column of slot
   * 
   * @return Slot's column
   */
  public int getCol() {
    return this.col;
  }

  /**
   * Check if slot has opponent
   * 
   * @return <code>true</code> if slot has opponent ; else <code>false</code>
   */
  public boolean hasOpponent() {
    return this.hasOpponent;
  }

}