package me.samoa.chess.model;

public enum Team {
  RED, BLUE;

  @Override
  public String toString(){
    return this == RED ? "RED" : "BLUE";
  }
}