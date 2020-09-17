package me.samoa.chess.controller;

import java.util.ArrayList;
import java.util.List;

import me.samoa.chess.model.GameManager;
import me.samoa.chess.model.Piece;
import me.samoa.chess.model.Player;
import me.samoa.chess.model.Team;

public class ClearState extends State {

  public ClearState(API api) {
    super(api);
  }

  /**
   * To create a different instance and reset a new board
   * usage: API.getInstance().getState().onReset()
   * 
   * @return List of Position Information
   */
  @Override
  public List<PositionInfo> onReset() {
    GameManager.newInstance();
    List<PositionInfo> information = new ArrayList<PositionInfo>();
    Player redPlayer = GameManager.getInstance().getPlayer(Team.RED);
    Player bluePlayer = GameManager.getInstance().getPlayer(Team.BLUE);
    for (Piece piece : redPlayer.getPieces()) {
      information.add(new PositionInfo(piece));
    }
    for (Piece piece : bluePlayer.getPieces()) {
      information.add(new PositionInfo(piece));
    }
    api.setState(new ReadyState(api));
    return information;
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
    return Type.Clear;
  }
  
}