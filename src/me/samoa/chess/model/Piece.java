package me.samoa.chess.model;

public abstract class Piece {

  private Player owner;
  private int positionR, positionC;
  
  // rmb to take out if useless
  public enum Type {
    Arrow, Chevron, Plus, Triangle, Sun
  }

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

  public int distanceCounter(int num1, int num2){
    return Math.abs(num1 - num2);

  }
  
  public abstract void onMove(Slot slot);

  public abstract boolean isPlaceable(Slot slot);

}