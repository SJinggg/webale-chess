package me.samoa.chess.controller;

public class API {
  
  private static API instance = null;
  private State state;
  
  /**
   * Singleton design pattern
   * Get instance of the APIs
   */
  public static API getInstance() {
    if (instance == null) {
      instance = new API();
    }
    return instance;
  }

  /**
   * Consutructor of API class
   */
  private API() {
    state = new ClearState(this);
  }

  /**
   * Set state of Webale game
   * 
   * @param state the state to be set
   */
  public void setState(State state) {
    this.state = state;
  }

  /**
   * Get state of Webale game
   * 
   * @return the State
   */
  public State getState() {
    return this.state;
  }

}
