package me.samoa.chess.model;

public abstract class Piece {

  private Player owner;
  private Slot position;
  private Team team;
  
  public enum Type {
    Arrow, Chevron, Plus, Triangle, Sun
  }

  public Piece (Player player, Slot position, Team team) { 
    this.owner = player;
    this.position = position;
    this.team = team;
    position.setOccupied(true);
  }
  
  public Player getPlayer() {
    return this.owner;
  }

  public Slot getSlot() {
    return this.position;
  }
  
  public abstract void onMove(Slot slot);

  public void isReplaceable(Piece otherPiece) {}

  public abstract boolean isPlaceable(Slot slot);

}