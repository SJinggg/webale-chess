package me.samoa.chess.model;

/**
 * The Chevron moves in an L shape: 2 steps in one direction, then 1 step perpendicular to it. 
 * It CAN skip over other pieces.
 */

public class ChevronPiece extends Piece{

  public ChevronPiece(Player player, int r, int c) {
    super(player, r, c);
<<<<<<< HEAD
=======
    type = Type.Chevron;
>>>>>>> f25093e10b8ff0fc68b8a123bc7f61ccb885e04a
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
<<<<<<< HEAD
    //current position
    if((slot.getRow() == super.getPositionR() - 2 || slot.getRow() == super.getPositionR() + 2) && 
        slot.getCol() == super.getPositionC() + 1)
=======
    int distanceRow = super.distanceCounter(slot.getRow(), super.getPositionR());
    int distanceCol = super.distanceCounter(slot.getCol(), super.getPositionC());
    if((distanceRow == 2 && distanceCol == 1) || (distanceRow == 1 && distanceCol == 2)) {
>>>>>>> f25093e10b8ff0fc68b8a123bc7f61ccb885e04a
      return true;
    }
    return false;
  }
}