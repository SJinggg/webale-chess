package me.samoa.chess.controller;

import java.util.List;

/**
 * State to start a game
 * 
 * @author Casey Teh Qi Shi
 */
public class ReadyState extends State {

  public ReadyState(API api) {
    super(api);
  }

  @Override
  public List<PositionInfo> onReset() {
    throw new IllegalStateException("Locked state");
  }

  /**
   * return game status information
   * usage: API.getInstance().getState().onStart()
   * 
   * @return GameStatusInfo
   */
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

  /**
   * Check type of state
   * 
   * @return State type
   */
  @Override
  public Type getStateType() {
    return Type.Ready;
  }
  
}