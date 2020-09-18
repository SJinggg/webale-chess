package me.samoa.chess.controller;

import me.samoa.chess.model.GameManager;
import me.samoa.chess.model.Team;
import me.samoa.chess.model.GameManager.GameState;

/**
 * Provide game status information
 * 
 * @author Casey Teh Qi Shi
 */
public class GameStatusInfo {
  
  private Team currentTurn;
  private Team winner;
  private GameState status;

  /**
   * Constructor of GameStatusInfo class
   */
  public GameStatusInfo() {
    final GameManager gameManager = GameManager.getInstance();
    this.currentTurn = gameManager.getCurrentPlayer().getTeam();
    this.winner = (gameManager.getWinner() == null) ? null : gameManager.getWinner().getTeam();
    this.status = gameManager.getGameState();
  }

  /**
   * Get team of current turn
   * 
   * @return Team
   */
  public Team getCurrentTurn() {
    return this.currentTurn;
  }

  /**
   * Get winner of game
   * 
   * @return Team
   */
  public Team getWinner() {
    return this.winner;
  }

  /**
   * Get game status
   * 
   * @return GameState
   */
  public GameState getStatus() {
    return this.status;
  }

}