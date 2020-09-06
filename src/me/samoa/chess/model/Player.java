package me.samoa.chess.model;

import java.util.ArrayList;

public class Player {

  private String playerName;
  private int turn;
  private Team team;
  private ArrayList<Piece> pieces;
  
  public Player(String playerName, Team team) {
    this.playerName = playerName;
    this.team = team;
    pieces = new ArrayList<>();
  }

  public boolean teamIdentify(Team team) {
    return this.team == team;
  }

  public String getPlayerName() {
    return playerName;
  }

  public int getTurn() {
    return turn;
  }
  
  public void addTurn() {
    this.turn += 1;
  }

  public void addPieces(Piece p) {
    this.pieces.add(p);
  }

  public void getPieces(){
    this.pieces.get(pieces.getSize()-1);
  }

  public Team getTeam() {
    return team;
  }

  public Team getOpponentTeam() {
    return this.team == Team.BLUE ? Team.RED : Team.BLUE; 
  }

  public int getId() {
    return (this.team == Team.RED) ? 0 : 1;
  }
}
