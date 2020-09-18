package me.samoa.chess.model;

import java.util.ArrayList;
import java.util.List;

/**
 * The Chevron moves in an L shape: 2 steps in one direction, then 1 step
 * perpendicular to it. It CAN skip over other pieces.
 * 
 * @author Casey Teh Qi Shi
 * @author Wong Man Yi
 * @author Koh Shi Jing
 * @author Nadia Ahmad Pirdaus
 */

public class ChevronPiece extends Piece {

  /**
   * Chevron piece constructor
   * 
   * @param player the owner of this piece
   * @param r the row coordination of this piece on the board
   * @param c the column coordination of this piece on the board
   */
  public ChevronPiece(Player player, int r, int c) {
    super(player, r, c);
    type = Type.Chevron;
  }

  /**
   * On turn movement of webale piece
   * 
   * @param turn the number of turn
   */
  @Override
  public void onTurn(int turn) { }

  /**
   * On move movement of webale piece
   * 
   * @param slot the slot of the piece intends to move to
   */
  @Override
  public void onMove(Slot slot) { }

  /**
   * Get all placeable slot for the potential movement of this piece
   * 
   * @return the list of slot that is allowed to move
   */
  @Override
  public List<Slot> getAllPlaceableSlot() {
    ArrayList<Slot> placeableSlots = new ArrayList<>();

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