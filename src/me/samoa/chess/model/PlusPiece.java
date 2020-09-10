package me.samoa.chess.model;

import java.util.ArrayList;
import java.util.List;

/**
 * The Plus piece moves in a straight line in ANY direction. It CANNOT skip over
 * other pieces.
 * @deprecated
 * Merged into a single class
 */
@Deprecated
public class PlusPiece extends Piece{
  boolean vertical = false;

  public PlusPiece(Player player, int r, int c) {
    super(player, r, c);
    type = Type.Plus;
  }

  @Override
  public void onTurn(int turn) {}
  
  @Override
  public void onMove(Slot slot) {}


  @Override
  public List<Slot> getAllPlaceableSlot() {
    // vertical down, vertical up, horizontal right, horizontal left
    int[][] increments = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };
    ArrayList<Slot> placeableSlots = new ArrayList<>();

    for (int[] increment : increments) {
      int row = this.getPositionR() + increment[0];
      int col = this.getPositionC() + increment[1];

      while (!(row >= getBoard().getBoardHeight() || col >= getBoard().getBoardWidth() || row < 0 || col < 0)) {
        Slot slot = getBoard().getSlot(row, col);

        if (!slot.isOccupied()) {
          placeableSlots.add(slot);
          row += increment[0];
          col += increment[1];
          continue;
        }
        if (slot.getOccupiedPiece().getPlayer().getOpponentTeam() == getPlayer().getTeam()) {
          placeableSlots.add(slot);
          break;
        }
        break;
      }
    }
    return placeableSlots;
  }
}