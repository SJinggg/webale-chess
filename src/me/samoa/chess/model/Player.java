package me.samoa.chess.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Player class
 * 
 * @author Casey Teh Qi Shi
 * @author Wong Man Yi
 * @author Koh Shi Jing
 * @author Nadia Ahmad Pirdaus
 */
public class Player {

  private String playerName;
  private int turn;
  private Team team;
  private ArrayList<Piece> pieces;
  
  /**
   * Constructor of Player class
   * 
   * @param playerName
   */
  public Player(String playerName, Team team) {
    this.playerName = playerName;
    this.team = team;
    pieces = new ArrayList<>();
    this.turn = 0;
  }

  /**
   * Identify the team for the player
   * 
   * @param team the team the be identify
   * @return <code>true</code> if the player belongs to this team; <code>false</code> otherwise. 
   */
  public boolean teamIdentify(Team team) {
    return this.team == team;
  }

  /**
   * Get the player's name
   * 
   * @return name of the player
   */
  public String getPlayerName() {
    return playerName;
  }

  /**
   * Set the game round to next turn
   * 
   */
  public void nextTurn() {
    this.turn++;
    for (Piece piece : this.pieces) {
      if (piece.isEaten()) continue;
      piece.onTurn(this.turn);
    }
  }

  /**
   * Add webale piece to this player
   * 
   * @param p the piece to be added
   */
  public Piece addPieces(Piece p) {
    this.pieces.add(p);
    return p;
  }

  /**
   * Get a list of piece of the player
   * 
   * @return a list of pieces
   */
  public void pushPieceToEnd(Piece p){
    this.pieces.remove(p);
    this.pieces.add(p);
  }

  /**
   * Get all the pieces belongs to this player
   * 
   * @return list of chess pieces
   */
  public List<Piece> getPieces(){
    return this.pieces;
  }

  /**
   * Get Player's Webale Sun Piece
   * 
   * @return player's Sun Piece
   */
  public SunPiece getSunPiece(){
    for(Piece p: pieces){
      if(p.getType() == Type.Sun){
        return (SunPiece)p;
      }
    }
    return null;
  }

  /**
   * Get player's team
   * 
   * @return Team of player
   */
  public Team getTeam() {
    return team;
  }

  /**
   * Get player's opponent team
   * 
   * @return Team of Opponent
   */
  public Team getOpponentTeam() {
    return this.team == Team.BLUE ? Team.RED : Team.BLUE; 
  }
}
