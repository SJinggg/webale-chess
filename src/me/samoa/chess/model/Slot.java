package me.samoa.chess.model;

/**
 * Represent each slot on the board
 * 
 * @author Casey Teh Qi Shi
 * @author Wong Man Yi
 * @author Koh Shi Jing
 * @author Nadia Ahmad Pirdaus
 */
public class Slot {
  
  private int row, col;
  private Piece occupiedPiece;

  /**
   * Constructor of Slot class
   * 
   * @param row Row of slot
   * @param col Column of slot
   */
  public Slot(int row, int col) {
    this.row = row;
    this.col = col;
  }

  /**
   * Get row of slot
   * 
   * @return row of slot
   */
  public int getRow() {
    return row;
  }

  /**
   * Get column of slot
   * 
   * @return column of slot
   */
  public int getCol() {
    return col;
  }

  /**
   * Check if current slot equals to another slot
   * 
   * @param otherSlot the slot to be compared with
   * @return <code>true</code> if both slots are the same ; else <code>false</code>
   */
  public boolean equals(Slot otherSlot) {
    return (this.row == otherSlot.row) && (this.col == otherSlot.col);
  }

  /**
   * Set row of slot
   * 
   * @param row Row of slot
   */
  public void setRow(int row) {
    this.row = row;
  }

  /**
   * Set column of slot
   * 
   * @param col Column of slot
   */
  public void setCol(int col) {
    this.col = col;
  }

  /**
   * Check if the slot is occupied
   * 
   * @return <code>true</code> if slot is occupied ; else <code>false</code>
   */
  public boolean isOccupied() {
    return this.occupiedPiece != null;
  }

  /**
   * Set occupant of slot
   * 
   * @param occupiedPiece the piece to occupy slot
   */
  public void setOccupiedPiece(Piece occupiedPiece) {
    this.occupiedPiece = occupiedPiece;
    if (occupiedPiece != null) {
      occupiedPiece.setPositionC(this.col);
      occupiedPiece.setPositionR(this.row); 
    }
  }

  /**
   * Get occupant of slot
   * 
   * @return occupant of slot
   */
  public Piece getOccupiedPiece() {
    return occupiedPiece;
  }
}