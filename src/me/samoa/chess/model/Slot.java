package me.samoa.chess.model;

public class Slot{
  private int row, col;
  Piece occupiedPiece;

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

  public void setRow(int row) {
    this.row = row;
  }

  public void setCol(int col) {
    this.col = col;
  }

  public boolean isOccupied() {
    return this.occupiedPiece == null;
  }

  public void setOccupiedPiece(Piece occupiedPiece) {
    this.occupiedPiece = occupiedPiece;
  }

  public void removeOccupiedPiece(){
    this.occupiedPiece = null;
  }
  
  public Piece getOccupiedPiece() {
    return occupiedPiece;
  }
}