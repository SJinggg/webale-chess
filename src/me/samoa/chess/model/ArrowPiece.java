package me.samoa.chess.model;

/**
 * The Arrow piece can only move 1 or 2 steps forward at a time.
 * If it reaches the end of the board, it turns around
 * and heads back to the other side.
 * It CANNOT skip over other pieces.
 */

public class ArrowPiece extends Piece {
  private boolean reachEnd = false; //when will this become true?

  public ArrowPiece(Player player, int r, int c) {
    super(player, r, c);
    type = Type.Arrow;
  }

  public void checkReachEnd(){
    if (super.getPlayer().teamIdentify(Team.RED) && super.getPositionR() == 0 ){
      this.reachEnd = true;
    }
    else if (super.getPlayer().teamIdentify(Team.BLUE) && super.getPositionR() == 7 ){
      this.reachEnd = true;
    }
  }

  @Override
  public void onMove(Slot slot) {
    /** 
     * Player select which slot to move to
     * System check if it is placeable according to its movement rule
     * put the piece on the slot position if the move valid
     */
    if(isPlaceable(slot)){
      super.setPositionR(slot.getRow());
      this.checkReachEnd();
    } 
  }

  @Override
  public boolean isPlaceable(Slot slot) {
    //original position selected
    if ((super.getPositionR() == slot.getRow()) || (super.getPositionC() == slot.getCol())) {
      //display message? current position selected
      System.out.println("The original position has been selected");
      return false;
    }
    //moving forward, check if the position is in between two steps              
    else if ((slot.getRow() <= super.getPositionR() - 2) && (super.getPlayer().teamIdentify(Team.RED) || reachEnd)) {
      if((super.getPlayer().teamIdentify(Team.RED) && reachEnd))
        return false;
      int dist = super.distanceCounter(super.getPositionR(), slot.getRow());
      for(int i = 1; i < dist; i++){
        if (super.getBoard().getSlotOccupied(slot.getRow() + i, slot.getCol()))
          return false;
      }
      return true;
    }
    //Head back after reached edge, check if the position is not more than two steps far from original position
    else if ((slot.getRow() <= super.getPositionR() + 2) && (super.getPlayer().teamIdentify(Team.BLUE) || reachEnd)) {
      if((super.getPlayer().teamIdentify(Team.BLUE) && reachEnd))
        return false;
      int dist = super.distanceCounter(slot.getRow(), super.getPositionR());
      for(int i = 1; i < dist; i++){
        if (super.getBoard().getSlotOccupied(slot.getRow() - i, slot.getCol()))
          return false;
      }
      return true;
    }
    return false;
  }
}