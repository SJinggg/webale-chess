package me.samoa.chess.model;

/**
 * The Chevron moves in an L shape: 2 steps in one direction, then 1 step perpendicular to it. 
 * It CAN skip over other pieces.
 */

public class ChevronPiece extends Piece{

  public ChevronPiece(Player player, int r, int c) {
    super(player, r, c);
    type = Type.Chevron;
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
    int distanceRow = super.distanceCounter(slot.getRow(), super.getPositionR());
    int distanceCol = super.distanceCounter(slot.getCol(), super.getPositionC());
    if((distanceRow == 2 && distanceCol == 1) || (distanceRow == 1 && distanceCol == 2)) {
      return true;
    }
    return false;
  }
}