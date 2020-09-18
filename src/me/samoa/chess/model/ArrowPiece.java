package me.samoa.chess.model;

import java.util.ArrayList;
import java.util.List;

/**
 * The Arrow piece can only move 1 or 2 steps forward at a time. If it reaches
 * the end of the board, it turns around and heads back to the other side. It
 * CANNOT skip over other pieces.
 * 
 * @author Casey Teh Qi Shi
 * @author Wong Man Yi
 * @author Koh Shi Jing
 * @author Nadia Ahmad Pirdaus
 */

public class ArrowPiece extends Piece {
  
  private boolean reachEnd = false;

  /**
   * Arrow piece constructor
   * 
   * @param player the owner of this piece
   * @param r the row coordination of this piece on the board
   * @param c the column coordination of this piece on the board
   */
  public ArrowPiece(Player player, int r, int c) {
    super(player, r, c);
    type = Type.Arrow;
  }

  /**
   * Check if Arrow piece has reached end of board
   * 
   * @return <code>true</code> if the arrow piece has reached the end of board; <code>false</code> otherwise.
   */
  public boolean isReachEnd() {
    return reachEnd;
  }

  /**
   * On turn movement of webale piece
   * 
   * @param turn the number of turn
   */
  @Override
  public void onTurn(int turn) { }

  /**
   * Movement control for the selected slot
   * 
   * @param slot the slot that the piece is moving to
   */
  @Override
  public void onMove(Slot slot) {
    if (this.getPositionR() == getBoard().getBoardHeight() - 1 || this.getPositionR() == 0) {
      this.reachEnd = !this.reachEnd;
    }
  }

  /**
   * Get all placeable slot for this piece on the webale board
   * 
   * @return list of the slot that is valid for potential movement
   */
  @Override
  public List<Slot> getAllPlaceableSlot() {
    ArrayList<Slot> placeableSlots = new ArrayList<>();
    int sign = (getPlayer().teamIdentify(Team.RED)) ? reachEnd ? 1 : -1 : reachEnd ? -1 : 1;

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