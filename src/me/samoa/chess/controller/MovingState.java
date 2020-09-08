package me.samoa.chess.controller;

import java.util.ArrayList;
import java.util.List;

public class MovingState extends State {


  public MovingState(API api) {
    super(api);
  }

  @Override
  public List<PositionInfo> onReset() {
    throw new IllegalStateException("Locked state");
  }

  @Override
  public GameStatusInfo onStart() {
    throw new IllegalStateException("Locked state");
  }

  @Override
  public List<MovementInfo> onSelect(int x, int y) {
    throw new IllegalStateException("Locked state");
  }

  /** return null if move failed and set state to TurnState */
  @Override
  public List<PositionInfo> onMove(int x, int y) {
    throw new IllegalStateException("Locked state");
  }

  @Override
  public GameStatusInfo onCheck() {
    throw new IllegalStateException("Locked state");
  }

  @Override
  public Type getStateType() {
    return Type.Moving;
  }

  
}