package me.samoa.chess.model;

/**
 * The Sun piece can move one step in any direction.
 * It CANNOT skip over other pieces.
 * The player who eats the Sun piece of the opposing team first wins.
 */

public class SunPiece extends Piece {

  public SunPiece(Player player, int r, int c) {
    super(player, r, c);
    type = Type.Sun;
  }

  @Override
  public void onMove(Slot position){
    if (isPlaceable(position)) {
      super.setPositionR(position.getRow());
      super.setPositionC(position.getCol());
    }
  }

  @Override
  public boolean isPlaceable(Slot selectedPosition) {
    int distanceRow = super.distanceCounter(selectedPosition.getRow(), super.getPositionR());
    int distanceCol = super.distanceCounter(selectedPosition.getCol(), super.getPositionC());
    if ( (distanceRow == 1 || distanceRow == 0) && (distanceCol == 1 || distanceCol == 0) ) {
        if (distanceRow == 1 && distanceCol == 1) {
          if (super.getBoard().getSlotOccupied(selectedPosition.getRow() + 1, selectedPosition.getCol() + 1))
            return false;
        }
        else if (distanceRow == 1){
          if (super.getBoard().getSlotOccupied(selectedPosition.getRow() + 1, selectedPosition.getCol()))
            return false;
        }
        else if (distanceCol == 1) {
          if (super.getBoard().getSlotOccupied(selectedPosition.getRow(), selectedPosition.getCol() + 1))
            return false;
        }
        return true;
    }
    return false;
  }

}