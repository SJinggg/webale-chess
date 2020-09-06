package me.samoa.chess.model;

/**
 * The Chevron moves in an L shape: 2 steps in one direction, then 1 step perpendicular to it. 
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
    //current position
    if((slot.getRow() == super.getPositionR() - 2 || slot.getRow() == super.getPositionR() + 2) && 
        slot.getCol() == super.getPositionC() + 1)
      return true;

    return false;
  }
}