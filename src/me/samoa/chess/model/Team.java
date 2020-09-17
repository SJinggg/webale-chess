package me.samoa.chess.model;

/**
 * Indicates the player's corresponding team
 */
public enum Team {
  RED, BLUE;

  /**
   * toString() function
   */
  @Override
  public String toString(){
    return this == RED ? "RED" : "BLUE";
  }
}