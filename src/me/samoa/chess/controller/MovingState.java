package me.samoa.chess.controller;

import java.util.ArrayList;
import java.util.List;

import me.samoa.chess.model.GameManager;
import me.samoa.chess.model.Piece;
import me.samoa.chess.model.Player;
import me.samoa.chess.model.Slot;
import me.samoa.chess.model.Team;

public class MovingState extends State {

  private Piece selectedPiece;

  public MovingState(API api, Piece selectedPiece) {
    super(api);
    this.selectedPiece = selectedPiece;
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

  /**
   * To move the piece and set pieces to new positions
   * usage: API.getInstance().getState().onMove()
   * 
   * @param row Row of selected slot
   * @param col Column of selected slot
   * @return List of Position Information
   */
  @Override
  public List<PositionInfo> onMove(int row, int col) {
    Slot selectedSlot = GameManager.getInstance().getBoard().getSlot(row, col);

    if (GameManager.getInstance().performMove(selectedPiece, selectedSlot)) {
      api.setState(new CheckState(api));
      List<PositionInfo> information = new ArrayList<>();
      Player redPlayer = GameManager.getInstance().getPlayer(Team.RED);
      Player bluePlayer = GameManager.getInstance().getPlayer(Team.BLUE);
      for (Piece piece : redPlayer.getPieces()) {
        information.add(new PositionInfo(piece));
      }
      for (Piece piece : bluePlayer.getPieces()) {
        information.add(new PositionInfo(piece));
      }
      return information;
    }
    api.setState(new TurnState(api));
    return null;
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
    return Type.Moving;
  }

  
}