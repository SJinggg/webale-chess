package me.samoa.chess.controller;

import java.util.List;

/**
 * State Design Pattern
 * Store each state of the game
 * 
 * @author Casey Teh Qi Shi
 */
public abstract class State {

  public enum Type {
    Clear, Ready, Turn, Moving, Check
  }
  
  protected API api;

  /**
   * Constructor of State class
   * 
   * @param api the game API instance
   */
  public State(API api) {
    this.api = api;
  }

  /**
   * Reset state
   * 
   * @return List of Position Information
   */
  public abstract List<PositionInfo> onReset();

  /**
   * Start state
   * 
   * @return Game Status Information
   */
  public abstract GameStatusInfo onStart();

  /**
   * Select piece state
   * 
   * @param row Row of selected piece
   * @param col Column of selected piece
   * @return List of Position Information
   */
  public abstract List<MovementInfo> onSelect(int row, int col) ;

  /**
   * Move piece to selected slot state
   * 
   * @param row Row of selected slot
   * @param col Column of selected slot
   * @return List of Position Information
   */
  public abstract List<PositionInfo> onMove(int row, int col) ;

  /**
   * Check state
   * 
   * @return Game Status Information
   */
  public abstract GameStatusInfo onCheck();

  /**
   * Get current state type
   * 
   * @return Type of current state
   */
  public abstract Type getStateType();

}

