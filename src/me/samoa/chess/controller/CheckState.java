package me.samoa.chess.controller;

import java.util.List;

import me.samoa.chess.model.GameManager;
import me.samoa.chess.model.GameManager.GameState;

/**
 * Checking state if the game has finished
 * 
 * @author Casey Teh Qi Shi
 */
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
  public List<MovementInfo> onSelect(int row, int col) {
    throw new IllegalStateException("Locked state");
  }

  @Override
  public List<PositionInfo> onMove(int row, int col) {
    throw new IllegalStateException("Locked state");
  }

  /**
   * To check current state of the game
   * usage: API.getInstance().getState().onCheck()
   * 
   * @return Game status information
   */
  @Override
  public GameStatusInfo onCheck() {
    if (GameManager.getInstance().getGameState() == GameState.PLAY) {
      api.setState(new TurnState(api));
    } else {
      api.setState(new ClearState(api));
    }
    return new GameStatusInfo();
  }

  /**
   * Check type of state
   * 
   * @return State type
   */
  @Override
  public Type getStateType() {
    return Type.Check;
  }

  
}