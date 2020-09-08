package me.samoa.chess.controller;

import java.util.ArrayList;
import java.util.List;

public class ClearState extends State {

  public ClearState(API api) {
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

  @Override
  public GameStatusInfo onCheck() {
    throw new IllegalStateException("Locked state");
  }

  @Override
  public Type getStateType() {
    return Type.Clear;
  }
  
}