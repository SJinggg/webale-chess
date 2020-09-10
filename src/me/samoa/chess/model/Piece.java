package me.samoa.chess.model;

import java.util.List;

public abstract class Piece {

  private Player owner;
  private int positionR, positionC;
  private boolean isEaten = false;
  protected Type type;

  public Piece (Player player, int r, int c) { 
    this.owner = player;
    this.positionR = r;
    this.positionC = c;
  }

  public Player getPlayer() {
    return this.owner;
  }

  public int getPositionR(){
    return positionR;
  }

  public int getPositionC(){
    return positionC;
  }

  public Board getBoard() {
    return GameManager.getInstance().getBoard();
  }

  public synchronized void setPositionR(int r){
    this.positionR = r;
  }

  public synchronized void setPositionC(int c){
    this.positionC = c;
  }

  public boolean isEaten() {
    return this.isEaten;
  }

  public void setEaten() {
    this.isEaten = true;
    this.positionR = -1;
    this.positionC = -1;
  }

  public Type getType(){
    return type;
  }

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

  public abstract void onTurn(int turn);
  
  public abstract void onMove(Slot slot);

  public abstract List<Slot> getAllPlaceableSlot();

}