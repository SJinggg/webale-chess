package me.samoa.chess.model;

import java.util.List;

/**
 * The Piece with different types and movement details
 */
public abstract class Piece {

  private Player owner;
  private int positionR, positionC;
  private boolean isEaten = false;
  protected Type type;

  /**
   * Webale Piece constructor
   * 
   * @param player the player who owns this piece
   * @param r the row coordination of this piece
   * @param c the column coordination of this piece
   */
  public Piece (Player player, int r, int c) { 
    this.owner = player;
    this.positionR = r;
    this.positionC = c;
  }

  /**
   * Get owner of the webale piece
   * 
   * @return the owner(player) of this piece
   */
  public Player getPlayer() {
    return this.owner;
  }

  /**
   * Get row of the webale piece
   * 
   * @return the row of the webale piece
   */
  public int getPositionR(){
    return positionR;
  }

  /**
   * Get column of the webale piece
   * 
   * @return the column of the webale piece
   */
  public int getPositionC(){
    return positionC;
  }

  /**
   * Get board of the webale piece
   * 
   * @return the game board
   */
  public Board getBoard() {
    return GameManager.getInstance().getBoard();
  }

  /**
   * Set row of the webale piece
   * 
   * @param r row coordination to be set.
   */
  public synchronized void setPositionR(int r){
    this.positionR = r;
  }

  /**
   * Set column of the webale piece
   * 
   * @param c column coordination to be set
   */
  public synchronized void setPositionC(int c){
    this.positionC = c;
  }

  /**
   * Check if webale piece is eaten 
   * 
   * @return <code>true</code> if the piece is eaten; <code>false</code> otherwise.
   * 
   */
  public boolean isEaten() {
    return this.isEaten;
  }

  /**
   * Set webale piece to be eaten
   */
  public void setEaten() {
    this.isEaten = true;
    this.positionR = -1;
    this.positionC = -1;
  }

  /**
   * Get type of webale piece
   * 
   * @return type of the piece
   */
  public Type getType(){
    return type;
  }

  /**
   * Move webale piece to selected slot
   * 
   * @param slot the selected slot
   * @return the movement validation
   */
  public boolean tryMove(Slot slot){
    for (Slot placeableSlot : getAllPlaceableSlot()) {
      if (placeableSlot.equals(slot)) {
        if (placeableSlot.isOccupied()) {
          placeableSlot.getOccupiedPiece().setEaten();
        }
        getBoard().removeSlotOccupation(this.getPositionR(), this.getPositionC());
        placeableSlot.setOccupiedPiece(this);
        this.onMove(slot);
        return true;
      }
    }
    return false;
  }

  /**
   * On turn movement of webale piece
   * 
   * @param turn the number of turn
   */
  public abstract void onTurn(int turn);
  
  /**
   * On move movement of webale piece
   * 
   * @param slot the slot of the piece intends to move to
   */
  public abstract void onMove(Slot slot);

  /**
   * Get all placeable slots of the webale piece
   * 
   * @return the list of placeable slot
   */
  public abstract List<Slot> getAllPlaceableSlot();

}