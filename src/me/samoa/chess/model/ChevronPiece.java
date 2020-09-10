package me.samoa.chess.model;

import java.util.ArrayList;
import java.util.List;

/**
 * The Chevron moves in an L shape: 2 steps in one direction, then 1 step
 * perpendicular to it. It CAN skip over other pieces.
 */

public class ChevronPiece extends Piece{

  public ChevronPiece(Player player, int r, int c) {
    super(player, r, c);
    type = Type.Chevron;
  }

  @Override
  public void onTurn(int turn) {}

  @Override
  public void onMove(Slot slot) {}

  @Override
  public List<Slot> getAllPlaceableSlot() {
    ArrayList<Slot> placeableSlots = new ArrayList<>();

    // for reference
    // placeableSlots.add(getBoard().getSlot(this.getPositionR() - 2, this.getPositionC() + 1));
    // placeableSlots.add(getBoard().getSlot(this.getPositionR() - 2, this.getPositionC() - 1));
    // placeableSlots.add(getBoard().getSlot(this.getPositionR() - 1, this.getPositionC() + 2));
    // placeableSlots.add(getBoard().getSlot(this.getPositionR() - 1, this.getPositionC() - 2));
    // placeableSlots.add(getBoard().getSlot(this.getPositionR() + 1, this.getPositionC() + 2));
    // placeableSlots.add(getBoard().getSlot(this.getPositionR() + 1, this.getPositionC() - 2));
    // placeableSlots.add(getBoard().getSlot(this.getPositionR() + 2, this.getPositionC() + 1));
    // placeableSlots.add(getBoard().getSlot(this.getPositionR() + 2, this.getPositionC() - 1));

    for (int i = -2; i < 3; i++) {
      if (i == 0) continue;
      int c = (Math.abs(i) > 1 ? 1 : 2);
      int row = this.getPositionR() + i;
      int col = this.getPositionC() + c;

      if (!(row >= getBoard().getBoardHeight() || col >= getBoard().getBoardWidth() || row < 0 || col < 0)) {
        Slot slot = getBoard().getSlot(row, col);
        if (!slot.isOccupied()) {
          placeableSlots.add(slot);
        } else if (slot.getOccupiedPiece().getPlayer().getOpponentTeam() == getPlayer().getTeam()) {
          placeableSlots.add(slot);
        }
      }
      col = this.getPositionC() - c;
      if (!(row >= getBoard().getBoardHeight() || col >= getBoard().getBoardWidth() || row < 0 || col < 0)) {
        Slot slot = getBoard().getSlot(row, col);
        if (!slot.isOccupied()) {
          placeableSlots.add(slot);
        } else if (slot.getOccupiedPiece().getPlayer().getOpponentTeam() == getPlayer().getTeam()) {
          placeableSlots.add(slot);
        }
      }
    }

    return placeableSlots;
  }
}