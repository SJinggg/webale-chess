package me.samoa.chess.model;

/**
 * The Arrow piece can only move 1/2 steps forward at a time.
 * If it reaches the end of the board, it turns around
 * and heads back to the other side.
 * It CANNOT skip over other pieces.
 */

public class ArrowPiece extends Piece {
  private boolean reachEnd = false; //when will this become true?

  public ArrowPiece(Player player, Slot position, Team team) {
    super(player, position, team);
    Type chessType = Type.Arrow;
  }

  @Override
  public void onMove(Slot slot) {
    /** 
     * Player select which slot to move to
     * System check if it is placeable according to its movement rule
     * put the piece on the slot position if the move valid
     */
    if(isPlaceable(slot)){
      super.getSlot().setX(slot.getX());
    } 
  }

  @Override
  public boolean isPlaceable(Slot slot) {
    //current position
    Slot position = super.getSlot();
    //original position selected
    if ((slot.getX() == position.getX()) || (slot.getX() == position.getX())) {
      //display message? current position selected
      return false;
    }
    //moving forward, check if the position is inbetween two steps
    else if ((slot.getX() <= position.getX() - 2) && !reachEnd) {
      return true;
    }
    //Head back after reached edge, check if the position is not more than two steps far from original position
    else if ((slot.getX() <= position.getX() + 2) && reachEnd) {
      return true;
    }
    return false;
  }
}