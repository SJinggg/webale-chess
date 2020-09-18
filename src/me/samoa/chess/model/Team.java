package me.samoa.chess.model;

/**
 * Indicates the player's corresponding team
 * 
 * @author Casey Teh Qi Shi
 * @author Wong Man Yi
 * @author Koh Shi Jing
 * @author Nadia Ahmad Pirdaus
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