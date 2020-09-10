package me.samoa.chess.controller;

import java.util.List;

public class ReadyState extends State {

  public ReadyState(API api) {
    super(api);
  }

  @Override
  public List<PositionInfo> onReset() {
    throw new IllegalStateException("Locked state");
  }

  // return game status information -> current player, game status, winner
  @Override
  public GameStatusInfo onStart() {
    api.setState(new TurnState(api));
    return new GameStatusInfo();
  }

  @Override
  public List<MovementInfo> onSelect(int row, int col)  {
    throw new IllegalStateException("Locked state");
  }

  @Override
  public List<PositionInfo> onMove(int row, int col)  {
    throw new IllegalStateException("Locked state");
  }

  @Override
  public GameStatusInfo onCheck() {
    throw new IllegalStateException("Locked state");
  }

  @Override
  public Type getStateType() {
    return Type.Ready;
  }
  
}