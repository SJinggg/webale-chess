package me.samoa.chess.controller;

import java.util.List;

public abstract class State {

  public enum Type {
    Clear, Ready, Turn, Moving, Check
  }
  
  protected API api;

  public State(API api) {
    this.api = api;
  }

  public abstract List<PositionInfo> onReset();

  public abstract GameStatusInfo onStart();

  public abstract List<MovementInfo> onSelect(int x, int y);

  public abstract List<PositionInfo> onMove(int x, int y);

  public abstract GameStatusInfo onCheck();

  public abstract Type getStateType();

}

