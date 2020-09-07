package me.samoa.chess.model;

/**
 * The Arrow piece can only move 1/2 steps forward at a time.
 * If it reaches the end of the board, it turns around
 * and heads back to the other side.
 * It CANNOT skip over other pieces.
 */

public class ArrowPiece extends Piece {
  private boolean reachEnd = false; //when will this become true? when it reach the opposite side

  public ArrowPiece(Player player, int r, int c) {
    super(player, r, c);
  }

  @Override
  public void onMove(Slot slot) {
    /** 
     * Player select which slot to move to
     * System check if it is placeable according to its movement rule
     * put the piece on the slot position if the move valid
     * if there is an opponent piece at the destination slot, it captures/eats it
     */
    if(isPlaceable(slot)){
      if(super.getBoard().getSlotOccupied(slot.getPositionR(), slot.getPositionC())) {
        slot.getOccupiedPiece().setEaten();
      }
      super.setPositionR(slot.getRow());
    }
  }

  @Override
  public boolean isPlaceable(Slot slot) {
    /**
    * Functions: (1)check if any pieces along the path (max 2 steps), (2)check if destination slots is occupied
    * TODO: 
    * (1)- if red piece not yet reach end or blue piece reached end --> check '2' steps forwards
    * (1)- if blue piece not yet reach end or red piece reached end --> check '2' steps backwards
    * (2)- if destination occupied --> check if it is opponent or ally
    */
    else if((super.getPlayer().teamIdentify(Team.RED) && !reachEnd) || (super.getPlayer().teamIdentify(Team.BLUE) && reachEnd)) {
      if(slot.getRow() <= super.getPositionR() - 2) {
        int dist = super.distanceCounter(super.getPositionR(), slot.getRow());
        for(int i = 1; i <= dist; i++) {
          if (super.getBoard().getSlotOccupied(slot.getRow() + i, slot.getCol())) {
            // if destination slot has a piece and is an ally piece
            if (i == dist) {
              if (super.getBoard().getSlotOccupiedPiece(slot.getRow() + i, slot.getCol()).teamIdentify(super.getPlayer().getTeam())) {
                return false;
              }
            }
            else return false;
          }
        }
        return true;
      }
    }
    else if((super.getPlayer().teamIdentify(Team.BLUE) && !reachEnd) || (super.getPlayer().teamIdentify(Team.RED) && reachEnd)) {
      if((slot.getRow() <= super.getPositionR() + 2) {
        int dist = super.distanceCounter(slot.getRow(), super.getPositionR());
        for(int i = 1; i < dist; i++){
          if (super.getBoard().getSlotOccupied(slot.getRow() - i, slot.getCol())) {
            // if destination slot has a piece and is an ally piece
            if (i == dist) {
              if (super.getBoard().getSlotOccupiedPiece(slot.getRow() + i, slot.getCol()).teamIdentify(super.getPlayer().getTeam())) {
                return false;
              }
            }
            else return false;
          }
        }
        return true;
      }
    }
    return false;
    //******************************************
    // i think the if condition is abit wrong
    // ori code
    //moving forward, check if the position is in between two steps              
    //                                                          vv-- here if the player is red and also reachEnd then it should move backwards
    // else if ((slot.getRow() <= super.getPositionR() - 2) && (super.getPlayer().teamIdentify(Team.RED) || reachEnd)) {
    //   int dist = super.distanceCounter(super.getPositionR(), slot.getRow());
    //   for(int i = 1; i < dist; i++){
    //     if (super.getBoard().getSlotOccupied(slot.getRow() + i, slot.getCol()))
    //       return false;
    //   }
    //   return true;
    // }
    //Head back after reached edge, check if the position is not more than two steps far from original position
    //                                                          vv-- same here when player is blue and reached the end it should move forwards
    // else if ((slot.getRow() <= super.getPositionR() + 2) && (super.getPlayer().teamIdentify(Team.BLUE) || reachEnd)) {
    //   int dist = super.distanceCounter(slot.getRow(), super.getPositionR());
    //   for(int i = 1; i < dist; i++){
    //     if (super.getBoard().getSlotOccupied(slot.getRow() - i, slot.getCol()))
    //       return false;
    //   }
    //   return true;
    // }
    // return false;
  }
}