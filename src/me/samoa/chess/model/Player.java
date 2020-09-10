package me.samoa.chess.model;

import java.util.ArrayList;
import java.util.List;

public class Player {

  private String playerName;
  private int turn;
  private Team team;
  private ArrayList<Piece> pieces;
  
  public Player(String playerName, Team team) {
    this.playerName = playerName;
    this.team = team;
    pieces = new ArrayList<>();
    this.turn = 0;
  }

  public boolean teamIdentify(Team team) {
    return this.team == team;
  }

  public String getPlayerName() {
    return playerName;
  }

  public void nextTurn() {
    this.turn++;
    for (Piece piece : this.pieces) {
      if (piece.isEaten()) continue;
      piece.onTurn(this.turn);
    }
  }

  public Piece addPieces(Piece p) {
    this.pieces.add(p);
    return p;
  }

  public List<Piece> getPieces(){
    return this.pieces;
  }

  public SunPiece getSunPiece(){
    for(Piece p: pieces){
      if(p.getType() == Type.Sun){
        return (SunPiece)p;
      }
    }
    return null;
  }

  public Team getTeam() {
    return team;
  }

  public Team getOpponentTeam() {
    return this.team == Team.BLUE ? Team.RED : Team.BLUE; 
  }
}
