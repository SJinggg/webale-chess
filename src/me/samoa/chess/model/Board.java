package me.samoa.chess.model;

/**
 * This represents the chess Board
 * 
 * @author Casey Teh Qi Shi
 * @author Wong Man Yi
 * @author Koh Shi Jing
 * @author Nadia Ahmad Pirdaus
 */
public class Board {

  private Slot[][] slots;
  private final int BOARDHEIGHT = 8, BOARDWIDTH = 7;

  /**
   * Constructor for Board
   */
  public Board() {
    slots = new Slot[BOARDHEIGHT][BOARDWIDTH];

    for(int i = 0; i < BOARDHEIGHT; i++) {
      for(int j = 0; j < BOARDWIDTH; j++) {
        slots[i][j] = new Slot(i, j);
      }
    }
  }

  /**
   * This function returns the board height
   * 
   * @return the board's height
   */
  public int getBoardHeight() {
    return BOARDHEIGHT; 
  }

  /**
   * This function returns the board width
   * 
   * @return the board's width
   */
  public int getBoardWidth() {
    return BOARDWIDTH; 
  }

  /**
   * Get the corresponding board slot
   * 
   * @param row the row of the board slot
   * @param col the column of the board slot 
   * @return the slot
   */
  public Slot getSlot(int row, int col) {
    return slots[row][col];
  }

  /**
   * Check if the slot is occupied
   * 
   * @param row the row of the board slot
   * @param col the column of the board slot 
   * @return <code>true</code>if slot is occupied; <code>false</code> otherwise. 
   */
  public boolean getSlotOccupied(int row, int col) {
    return slots[row][col].isOccupied();
  }

  /**
   * Set the webale piece that has occupied the board slot
   * 
   * @param p the webale piece that occupied the slot
   * @param row the row coordination of slot in the board
   * @param col the column coordination of slot in the board 
   */
  public void setSlotOccupiedPiece(Piece p, int row, int col) {
    slots[row][col].setOccupiedPiece(p);
  }

  /**
   * Remove the slot that has occupied the board slot previously
   * 
   * @param row the row coordination of slot where has been occupied previously
   * @param col the column coordination of slot where has been occupied previously
   */
  public void removeSlotOccupation(int row, int col) {
    slots[row][col].setOccupiedPiece(null);
  }

  /**
   * Clear all pieces on the board
   * 
   */
  public void clearBoard(){
    for(int i = 0; i < BOARDHEIGHT; i++) {
      for(int j = 0; j < BOARDWIDTH; j++) {
        removeSlotOccupation(i,j);
      }
    }
  }
}