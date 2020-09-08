package me.samoa.chess.controller;

import me.samoa.chess.model.GameManager;
import me.samoa.chess.model.Team;
import me.samoa.chess.model.GameManager.GameState;

public class GameStatusInfo {
  
  private Team currentTurn;
  private Team winner;
  private GameState status;

  public GameStatusInfo() {
    final GameManager gameManager = GameManager.getInstance();
    this.currentTurn = gameManager.getCurrentPlayer().getTeam();
    this.winner = (gameManager.getWinner() == null) ? null : gameManager.getWinner().getTeam();
    this.status = gameManager.getGameState();
  }

  public Team getCurrentTurn() {
    return this.currentTurn;
  }

  public Team getWinner() {
    return this.winner;
  }

  public GameState getStatus() {
    return this.status;
  }

}