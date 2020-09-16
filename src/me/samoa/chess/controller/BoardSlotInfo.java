package me.samoa.chess.controller;

import me.samoa.chess.model.Piece;
import me.samoa.chess.model.Team;
import me.samoa.chess.model.ArrowPiece;
import me.samoa.chess.model.GameManager;

public class BoardSlotInfo {
  boolean occupied;
  Piece occupiedPiece;
  String pieceName;

  public BoardSlotInfo(int row, int col) {
    this.occupiedPiece = GameManager.getInstance().getBoard().getSlot(row, col).getOccupiedPiece();
    this.occupied = this.occupiedPiece == null ? false: true;
    if(occupied){
      if(this.occupiedPiece.getType().toString() == "Arrow") {
        this.pieceName = (GameManager.getInstance().getCurrentPlayer().teamIdentify(Team.RED)) 
          ? ((ArrowPiece) this.occupiedPiece).isReachEnd()
            ? "Rotated Arrow " + this.occupiedPiece.getPlayer().getTeam().toString() 
            : "Arrow " + this.occupiedPiece.getPlayer().getTeam().toString()
          : ((ArrowPiece) this.occupiedPiece).isReachEnd()
            ? "Arrow " + this.occupiedPiece.getPlayer().getTeam().toString() 
            : "Rotated Arrow " + this.occupiedPiece.getPlayer().getTeam().toString();
        }
      else {
        this.pieceName = GameManager.getInstance().getCurrentPlayer().teamIdentify(Team.RED) 
          ? (this.occupiedPiece.getType().toString() + " " + this.occupiedPiece.getPlayer().getTeam().toString())
          : ("Rotated " + this.occupiedPiece.getType().toString() + " " + this.occupiedPiece.getPlayer().getTeam().toString());
      }
    }
  }

  public boolean isOccupied() {
    return this.occupied;
  }

  public Piece getOccupiedPiece() {
    return this.occupiedPiece;
  }

  public String getPieceName() {
    return this.pieceName; 
  }
}
