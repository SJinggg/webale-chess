package me.samoa.chess.model;

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

<<<<<<< HEAD
=======
  public boolean isEaten() {
    return this.isEaten;
  }

>>>>>>> f25093e10b8ff0fc68b8a123bc7f61ccb885e04a
  public synchronized void setPositionR(int r){
    this.positionR = r;
  }

  public synchronized void setPositionC(int c){
    this.positionC = c;
  }

<<<<<<< HEAD
  public int distanceCounter(int num1, int num2){
    return Math.abs(num1 - num2);

=======
  public synchronized void setEaten() {
    this.isEaten = true;
    this.positionR = -1;
    this.positionC = -1;
  }

  public int distanceCounter(int num1, int num2){
    return Math.abs(num1 - num2);
  }

  public Type getType(){
    return type;
>>>>>>> f25093e10b8ff0fc68b8a123bc7f61ccb885e04a
  }
  
  public abstract void onMove(Slot slot);

  public abstract boolean isPlaceable(Slot slot);

}