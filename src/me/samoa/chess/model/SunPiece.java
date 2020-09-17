package me.samoa.chess.model;

import java.util.ArrayList;
import java.util.List;

/**
 * The Sun piece can move one step in any direction. It CANNOT skip over other
 * pieces. The player who eats the Sun piece of the opposing team first wins.
 */

public class SunPiece extends Piece {

  /**
   * Constructor of Sun Piece
   * 
   * @param player Player of Sun Piece
   * @param r Row of Sun Piece
   * @param c Column of Sun Piece
   */
  public SunPiece(Player player, int r, int c) {
    super(player, r, c);
    type = Type.Sun;
  }

  /**
   * On turn movement of webale piece
   * 
   * @param turn the number of turn
   */
  @Override
  public void onTurn(int turn) {}

  /**
   * On move movement of webale piece
   * 
   * @param slot the slot of the piece intends to move to
   */
  @Override
  public void onMove(Slot slot) {}

  /**
   * Get all placeable slot for this piece on the webale board
   * 
   * @return list of the slot that is valid for potential movement
   */
  @Override
  public List<Slot> getAllPlaceableSlot() {
    ArrayList<Slot> placeableSlots = new ArrayList<>();

    for (int i = -1; i < 2; i++) {
      for (int j = -1; j < 2; j++) {
        if (i == 0 && j == 0) continue;
        int row = this.getPositionR() + i;
        int col = this.getPositionC() + j;

        if ((row >= getBoard().getBoardHeight() || col >= getBoard().getBoardWidth() || row < 0 || col < 0)) continue;
        Slot slot = getBoard().getSlot(row, col);
        if (!slot.isOccupied()) {
          placeableSlots.add(slot);
          continue;
        }
        if (slot.getOccupiedPiece().getPlayer().getOpponentTeam() == getPlayer().getTeam()) {
          placeableSlots.add(slot);
        }
      }
    }
    return placeableSlots;
  }
}