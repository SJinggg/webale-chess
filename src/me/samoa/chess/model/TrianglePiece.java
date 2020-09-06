package me.samoa.chess.model;

public class TrianglePiece extends Piece{

  public TrianglePiece(Player player, Slot position, Team team) {
    super(player, position, team);
    Type chessType = Type.Triangle;
  }

  @Override
  public void onMove(Slot slot){
    //assuming ur isPlaceable is ok already (i didnt check)
    Slot currentPosition = super.getSlot();

    if (isPlaceable(slot)) {
      currentPosition.setX(slot.getX());
      currentPosition.setY(slot.getY());
      return;
    }
  }
  // basically pieces done? except those checking for occupied 
  // the occupied should set to that position, not the slot becuz ithink there are alot slot create or what 0.0
  // put a boolean for occupy then if .... call setOccupy, then one more function for what huh
  // i oso x tau, but that function for checking all the position that has been occupied i really dk eh Q.Q 
  @Override
  public boolean isPlaceable(Slot selectedposition) {
    Slot currentposition = super.getSlot();
    
    if(Math.abs(currentposition.getX() - selectedposition.getX()) 
        == Math.abs(currentposition.getY() - selectedposition.getY()))
    {
      System.out.println("Move diagonally");
      return true;
    }
    return false;
  }

}