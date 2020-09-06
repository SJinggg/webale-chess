package me.samoa.chess.model;

/**
 * The Chevron moves in an L shape: 2 steps in one direction, then 1 step perpendicular to it. 
 * It CAN skip over other pieces.
 */

public class ChevronPiece extends Piece{

  public ChevronPiece(Player player, Slot position, Team team) {
    super(player, position, team);
    Type chessType = Type.Chevron;
  }

  @Override
  public void onMove(Slot slot) {
    if(isPlaceable(slot)){
      super.getSlot().setX(slot.getX());
      super.getSlot().setY(slot.getY());
    }
  }

  @Override
  public boolean isPlaceable(Slot slot) {
    //current position
    Slot position = super.getSlot();

    if((slot.getX() == position.getX() - 2 || slot.getX() == position.getX() + 2) && slot.getY() == position.getY() + 1)
      return true;

    return false;
  }
}