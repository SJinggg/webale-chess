package me.samoa.chess.model;

public class Slot{
  private int row, col;
  private Piece occupiedPiece;

  public Slot(int row, int col) {
    this.row = row;
    this.col = col;
  }

  public int getRow() {
    return row;
  }

  public int getCol() {
    return col;
  }

  public boolean equals(Slot otherSlot) {
    return (this.row == otherSlot.row) && (this.col == otherSlot.col);
  }

  public void setRow(int row) {
    this.row = row;
  }

  public void setCol(int col) {
    this.col = col;
  }

  public boolean isOccupied() {
    return this.occupiedPiece != null;
  }

  public void setOccupiedPiece(Piece occupiedPiece) {
    this.occupiedPiece = occupiedPiece;
    if (occupiedPiece != null) {
      occupiedPiece.setPositionC(this.col);
      occupiedPiece.setPositionR(this.row); 
    }
  }

  public Piece getOccupiedPiece() {
    return occupiedPiece;
  }
}