package me.samoa.chess.model;

public class TrianglePiece extends Piece{

  public TrianglePiece(Player player, int r, int c) {
    super(player, r, c);
    type = Type.Triangle;
  }

  @Override
  public void onMove(Slot slot){

    if (isPlaceable(slot)) {
      super.setPositionR(slot.getRow());
      super.setPositionC(slot.getCol());
      return;
    }
  }

  @Override
  public boolean isPlaceable(Slot selectedPosition) {
    int multiplier = (super.getPositionR() > selectedPosition.getRow()) ? 1 : -1;
    int dist = super.distanceCounter(super.getPositionR(), selectedPosition.getRow());

    if(dist == super.distanceCounter(super.getPositionC(), selectedPosition.getCol())) {
      for(int i = 0; i < dist; i++){
        if(super.getBoard().getSlotOccupied(selectedPosition.getRow() + (i * multiplier), selectedPosition.getCol() + (i * multiplier)))
          return false;
      }
      System.out.println("Move diagonally");
      return true;
    }
    return false;
  }

}