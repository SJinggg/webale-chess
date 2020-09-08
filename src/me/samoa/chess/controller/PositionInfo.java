package me.samoa.chess.controller;

import me.samoa.chess.model.ArrowPiece;
import me.samoa.chess.model.Piece;
import me.samoa.chess.model.Team;
import me.samoa.chess.model.Type;


public class PositionInfo {
  
  private int row;
  private int col;
  private Type type;
  private Team team;
  private boolean isNorth;

  public PositionInfo(Piece piece) {
    this.row = piece.getPositionR();
    this.col = piece.getPositionC();
    this.type = piece.getType();
    this.team = piece.getPlayer().getTeam();
    if (piece instanceof ArrowPiece) {
      final ArrowPiece arrowPiece = (ArrowPiece)piece;
      this.isNorth = (this.team == Team.RED) ? !(arrowPiece.isReachEnd()) : (arrowPiece.isReachEnd());
    } else {
      this.isNorth = (this.team == Team.RED);
    }
  }

  public int getRow() {
    return this.row;
  }

  public int getCol() {
    return this.col;
  }

  public Type getType() {
    return this.type;
  }

  public Team getTeam() {
    return this.team;
  }

  public boolean isNorth() {
    return this.isNorth;
  }

}