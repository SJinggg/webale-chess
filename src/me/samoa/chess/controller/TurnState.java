package me.samoa.chess.controller;

import java.util.ArrayList;
import java.util.List;

import me.samoa.chess.model.GameManager;
import me.samoa.chess.model.Piece;
import me.samoa.chess.model.Slot;


public class TurnState extends State {

  public TurnState(API api) {
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

  // select piece player wants to move
  @Override
  public List<MovementInfo> onSelect(int row, int col) {
    GameManager gameManager = GameManager.getInstance();
    Slot slot = gameManager.getBoard().getSlot(row, col);

    if (!slot.isOccupied()) return null;
    Piece occupiedPiece = slot.getOccupiedPiece();
    if (occupiedPiece.getPlayer().teamIdentify(gameManager.getCurrentPlayer().getTeam())) {
      List<MovementInfo> information = new ArrayList<>();
      for (final Slot placeableSlot : occupiedPiece.getAllPlaceableSlot()) {
        boolean hasOpponent = placeableSlot.isOccupied() && placeableSlot.getOccupiedPiece().getPlayer().teamIdentify(occupiedPiece.getPlayer().getOpponentTeam());
        information.add(new MovementInfo(placeableSlot.getRow(), placeableSlot.getCol(), hasOpponent));
      }
      api.setState(new MovingState(api, occupiedPiece));
      return information;
    }
    return null;
  }

  @Override
  public List<PositionInfo> onMove(int row, int col) {
    throw new IllegalStateException("Locked state");
  }

  @Override
  public GameStatusInfo onCheck() {
    throw new IllegalStateException("Locked state");
  }

  @Override
  public Type getStateType() {
    return Type.Turn;
  }
  
}