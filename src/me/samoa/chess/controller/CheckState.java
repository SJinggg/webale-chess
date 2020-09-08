package me.samoa.chess.controller;

import java.util.List;

public class CheckState extends State {

  public CheckState(API api) {
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

  @Override
  public List<PositionInfo> onMove(int x, int y) {
    throw new IllegalStateException("Locked state");
  }

  /** Move to TurnState if still playing else move to ClearState */
  @Override
  public GameStatusInfo onCheck() {
    throw new IllegalStateException("Locked state");
  }

  @Override
  public Type getStateType() {
    return Type.Check;
  }

  
}