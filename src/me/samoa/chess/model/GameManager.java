package me.samoa.chess.model;

import java.util.ArrayList;

public class GameManager {
  private Board board;
  public static GameManager instance;
  private Player[] players = {new Player("redPlayer", Team.RED), new Player("bluePlayer", Team.BLUE)};
  private Player currentPlayer;
  private Player winner;
  private GameState gameState;

  public static GameManager getInstance() {
    if (instance == null) {
      instance = new GameManager();
    }
    return instance;
  }

  public enum GameState {
    PLAY, END
  };

  public GameManager() { 
    this.gameState = GameState.PLAY;
    board = new Board();
    int boardHeight = board.getBoardHeight();

    for(Player i: players){
      int row = (i.getPlayerName().equals("bluePlayer")) ? 0 : boardHeight-1;

      i.addPieces(new SunPiece(i, row, 3));
      board.setSlotOccupiedPiece(i.getPieces(), row, 3);

      i.addPieces(new ChevronPiece(i, row, 2));
      board.setSlotOccupiedPiece(i.getPieces(), row, 2);
      i.addPieces(new ChevronPiece(i, row, 4));
      board.setSlotOccupiedPiece(i.getPieces(), row, 4);

      i.addPieces(new TrianglePiece(i, row, 1));
      board.setSlotOccupiedPiece(i.getPieces(), row, 1);
      i.addPieces(new TrianglePiece(i, row, 5));
      board.setSlotOccupiedPiece(i.getPieces(), row, 5);

      i.addPieces(new PlusPiece(i, row, 0));
      board.setSlotOccupiedPiece(i.getPieces(), row, 0);
      i.addPieces(new PlusPiece(i, row, 6));
      board.setSlotOccupiedPiece(i.getPieces(), row, 6);

      row = (i.getPlayerName().equals("bluePlayer")) ? (row += 1) : (row -= 1);
      for(int j = 0; j < 7; j++){
        i.addPieces(new ArrowPiece(i, row, j++));
        board.setSlotOccupiedPiece(i.getPieces(), row, j);
      }
    } 
  }

  public void movement(Piece selectedPiece, Slot targetDest) {
    if(selectedPiece.getPlayer().getTeam() == getCurrentPlayer().getTeam()){
      if(!targetDest.isOccupied()){
        selectedPiece.onMove(targetDest);
      }
      else if(targetDest.getOccupiedPiece().getPlayer().getTeam() == selectedPiece.getPlayer().getOpponentTeam()){
        Piece previousPiece = targetDest.getOccupiedPiece();
        selectedPiece.onMove(targetDest);
        board.removeSlotOccupation(selectedPiece.getPositionR(), selectedPiece.getPositionC());
        previousPiece.setEaten();
      }
    }
    currentPlayer.addTurn();
    if(checkSwitch()) {
      for(Player p: players){
        // random piece for temporary usage
        Piece temp = new ArrowPiece(new Player("", p.getTeam()), -1, -1);
        ArrayList<Piece> tempPlus = p.getPieces(Type.Plus);
        ArrayList<Piece> tempTriangle = p.getPieces(Type.Triangle);
        for(int i = 0; i < 2; i++){
          temp.setPositionR(tempPlus.get(i).getPositionR());
          temp.setPositionC(tempPlus.get(i).getPositionC());
          tempPlus.get(i).setPositionR(tempTriangle.get(i).getPositionR());
          tempPlus.get(i).setPositionC(tempTriangle.get(i).getPositionC());
          tempTriangle.get(i).setPositionR(temp.getPositionR());
          tempTriangle.get(i).setPositionC(temp.getPositionC());
        }
      }
    }
  }

  public boolean checkGameOver() {
    Piece temp0 = players[0].getPieces(Type.Sun).get(0);
    Piece temp1 = players[0].getPieces(Type.Sun).get(0);
    if(temp0.isEaten()){
      this.winner = players[1];
      this.gameState = GameState.END;
      return true;
    }
    else if(temp1.isEaten()){
      this.winner = players[0];
      this.gameState = GameState.END;
      return true;
    }
    return false;
  }

  public Board getBoard() {
    return this.board;
  }

  public boolean checkSwitch() {
    if (players[0].getTurn() % 2 == 0 && players[1].getTurn() % 2 == 0){
      return true;
    }
    return false;
  }

  public Player getCurrentPlayer() {
    return currentPlayer;
  }

  public void nextTurn() {
    if (this.gameState == GameState.PLAY) {
      Player player = getCurrentPlayer();
      player.addTurn();
    }
  }

  public Player getWinner() {
    return this.winner;
  }

  public GameState getGameState() {
    return this.gameState;
  }

}