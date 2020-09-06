package me.samoa.chess.model;

/**
 * The Sun moves in a straight line in any direction at one slot at a time
 * Cannot skip over the other pieces.
 * Once eaten the player of the Sun Piece looses
 */

public class SunPiece extends Piece {

  public SunPiece(Player player, Slot position, Team team) {
    super(player, position, team);
    Type chessType = Type.Sun;
  }

  @Override
  public void onMove(Slot position){
    Slot currentPosition = super.getSlot();
    // if (position got occupant) {
    //   break;
    // }
    if (isPlaceable(position)) {
      currentPosition.setX(position.getX());
      currentPosition.setY(position.getY());
      return;
    }
  }

  @Override
  public boolean isPlaceable(Slot selectedPosition) {
    Slot currentPosition = super.getSlot();

    if ( ((Math.abs(selectedPosition.getX() - currentPosition.getX()) == 1) || (selectedPosition.getX() - currentPosition.getX() == 0)) &&
         ((Math.abs(selectedPosition.getY() - currentPosition.getY()) == 1) || (selectedPosition.getY() - currentPosition.getY() == 0)) ) {
        return true;
    }
    return false;
  }
}