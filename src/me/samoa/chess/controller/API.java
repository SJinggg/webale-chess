package me.samoa.chess.controller;

public class API {
  
  private static API instance = null;
  
  public static API getInstance() {
    if (instance == null) {
      instance = new API();
    }
    return instance;
  }

  private State state;

  private API() {
    state = new ClearState(this);
  }

  public void setState(State state) {
    this.state = state;
  }

  public State getState() {
    return this.state;
  }

}
