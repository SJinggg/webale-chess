package me.samoa.chess.model;

public class Board{
  private Slot[][] slots;
  private final int BOARDHEIGHT = 8, BOARDWIDTH = 7;

  public Board() {
    slots = new Slot[BOARDHEIGHT][BOARDWIDTH];

    for(int i = 0; i < BOARDHEIGHT; i++){
      for(int j = 0; j < BOARDWIDTH; j++){
        slots[i][j] = new Slot(i, j);
      }
    }
  }

  public int getBoardHeight(){
    return BOARDHEIGHT; 
  }

  public int getBoardWidth() {
    return BOARDWIDTH; 
  }

  public Slot getSlot(int row, int col) {
    return slots[row][col];
  }

  public boolean getSlotOccupied(int row, int col){
    return slots[row][col].isOccupied();
  }

  public void setSlotOccupiedPiece(Piece p, int row, int col){
    slots[row][col].setOccupiedPiece(p);
  }
}