package me.samoa.chess.model;

/**
 * The Plus moves in a straight line in ANY direction.
 * It CANNOT skip over other pieces.
 */

public class PlusPiece extends Piece{
  boolean vertical = false;

  public PlusPiece(Player player, Slot position, Team team) {
    super(player, position, team);
    Type chessType = Type.Plus;
  }

  @Override
  public void onMove(Slot slot) {
    if(isPlaceable(slot)){
      if(vertical) {
        Slot prevSlot = super.getSlot();
        super.getSlot().setX(slot.getX());
        prevSlot.setOccupied(false);
      }
      else {
        Slot prevSlot = super.getSlot();
        super.getSlot().setY(slot.getY());
        prevSlot.setOccupied(false);
      }
    }
  }

  @Override 
  public boolean isPlaceable(Slot selectedPosition) { 
    Slot currentPosition = super.getSlot();

    if(currentPosition.getX() == selectedPosition.getX() && currentPosition.getY() != selectedPosition.getY()) {
      int distance = Math.abs(currentPosition.getY()-selectedPosition.getY());
      for(int i=0; i<distance; i++) {
        
      }
      System.out.println("move vertically");
      vertical = true;
      return true;
    }
    else if(currentPosition.getX() != selectedPosition.getX() && currentPosition.getY() == selectedPosition.getY()) {
      System.out.println("move horizontally");
      vertical = false;
      return true;
    }
    return false;
  }
}