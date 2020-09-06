package me.samoa.chess.model;

/**
 * The Chevron moves in an L shape: 2 steps in one direction, then 1 step perpendicular to it. 
 * It moves: 
 *  - 1 step (right/left) and 2 steps (up/down) [2 row 1 col]
 *  - 2 steps (right/left) and 1 step (up/down) [2 col 1 row]
 * It CAN skip over other pieces.
 */

public class ChevronPiece extends Piece{

  public ChevronPiece(Player player, int r, int c) {
    super(player, r, c);
  }

  @Override
  public void onMove(Slot slot) {
    if(isPlaceable(slot)){
      super.setPositionR(slot.getRow());
      super.setPositionC(slot.getCol());
    }
  }

  @Override
  public boolean isPlaceable(Slot slot) {
    //current position // ??
    if((slot.getRow() == super.getPositionR() - 2 || slot.getRow() == super.getPositionR() + 2) && 
        (slot.getCol() == super.getPositionC() + 1 || slot.getCol() == super.getPositionC() - 1))x`
      return true;

    return false;
  }
}