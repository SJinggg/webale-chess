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

  /**
   * Constructor of PositionInfo class
   * 
   * @param piece Webale Piece Object
   */
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

  /**
   * Get row of piece
   * 
   * @return Piece's row
   */
  public int getRow() {
    return this.row;
  }

  /**
   * Get column of piece
   * 
   * @return Piece's column
   */ 
  public int getCol() {
    return this.col;
  }

  /**
   * Get type of piece
   * 
   * @return Piece's type
   */ 
  public Type getType() {
    return this.type;
  }

  /**
   * Get team of piece
   * 
   * @return Piece's team
   */ 
  public Team getTeam() {
    return this.team;
  }

  /**
   * Check if piece's direction is north
   * 
   * @return <code>true</code> if direction of piece is north ; else <code>false</code>
   */
  public boolean isNorth() {
    return this.isNorth;
  }

}