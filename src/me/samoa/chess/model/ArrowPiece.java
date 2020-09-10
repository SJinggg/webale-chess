package me.samoa.chess.model;

import java.util.ArrayList;
import java.util.List;

/**
 * The Arrow piece can only move 1 or 2 steps forward at a time. If it reaches
 * the end of the board, it turns around and heads back to the other side. It
 * CANNOT skip over other pieces.
 */

public class ArrowPiece extends Piece {
  private boolean reachEnd = false;

  public ArrowPiece(Player player, int r, int c) {
    super(player, r, c);
    type = Type.Arrow;
  }

  public boolean isReachEnd() {
    return reachEnd;
  }

  @Override
  public void onTurn(int turn) {
  }

  @Override
  public void onMove(Slot slot) {
    if (this.getPositionR() == getBoard().getBoardHeight() - 1 || this.getPositionR() == 0) {
      this.reachEnd = !this.reachEnd;
    }
  }

  @Override
  public List<Slot> getAllPlaceableSlot() {
    ArrayList<Slot> placeableSlots = new ArrayList<>();
    int sign = (getPlayer().teamIdentify(Team.RED)) ? reachEnd ? 1 : -1 : reachEnd ? -1 : 1;

    // one by one checks towards the movable direction
    for (int i = 1; i < 3; i++) {
      int row = this.getPositionR() + i*sign;
      int col = this.getPositionC();

      if ((row >= getBoard().getBoardHeight() || col >= getBoard().getBoardWidth() || row < 0 || col < 0)) break;
      Slot slot = getBoard().getSlot(row, col);

      if (!slot.isOccupied()) {
        placeableSlots.add(slot);
        continue;
      }
      if (slot.getOccupiedPiece().getPlayer().getOpponentTeam() == getPlayer().getTeam()) {
        placeableSlots.add(slot);
        break;
      }
      break;
    }
    return placeableSlots;
  }
}