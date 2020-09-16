package me.samoa.chess.model;

import java.io.*;
import java.util.List;

public class GameManager {
  public enum GameState {
    PLAY, END
  };

  public static GameManager instance;

  public static GameManager getInstance() {
    if (instance == null) {
      instance = new GameManager();
    }
    return instance;
  }

  public static void newInstance() {
    instance = new GameManager();
  }

  private Board board;
  private Player[] players = {new Player("redPlayer", Team.RED), new Player("bluePlayer", Team.BLUE)};
  private Player currentPlayer;
  private Player winner;
  private GameState gameState;

  public GameManager() { 
    this.gameState = GameState.PLAY;
    board = new Board();
    int boardHeight = board.getBoardHeight();
    currentPlayer = getPlayer(Team.RED);

    for(Player i: players){
      int row = (i.getPlayerName().equals("bluePlayer")) ? 0 : boardHeight-1;

      board.setSlotOccupiedPiece(i.addPieces(new SunPiece(i, row, 3)), row, 3);

      board.setSlotOccupiedPiece(i.addPieces(new ChevronPiece(i, row, 2)), row, 2);
      board.setSlotOccupiedPiece(i.addPieces(new ChevronPiece(i, row, 4)), row, 4);

      board.setSlotOccupiedPiece(i.addPieces(new TriPlusPiece(i, row, 1, false)), row, 1);
      board.setSlotOccupiedPiece(i.addPieces(new TriPlusPiece(i, row, 5, false)), row, 5);

      board.setSlotOccupiedPiece(i.addPieces(new TriPlusPiece(i, row, 0, true)), row, 0);
      board.setSlotOccupiedPiece(i.addPieces(new TriPlusPiece(i, row, 6, true)), row, 6);

      row = (i.getPlayerName().equals("bluePlayer")) ? (row += 1) : (row -= 1);
      for(int j = 0; j < 7; j+=2) {
        board.setSlotOccupiedPiece(i.addPieces(new ArrowPiece(i, row, j)), row, j);
      }
    } 
  }

  public boolean performMove(Piece selectedPiece, Slot targetDest) {
    if (gameState != GameState.PLAY) {
      throw new IllegalStateException("Attempt to perform move in invalid Game State");
    } 
    if(selectedPiece.getPlayer().getTeam() == getCurrentPlayer().getTeam()){
      if (selectedPiece.tryMove(targetDest)) {
        currentPlayer.nextTurn();
        SunPiece sunPiece = getPlayer(currentPlayer.getOpponentTeam()).getSunPiece();
        if (sunPiece.isEaten()) {
          gameState = GameState.END;
          winner = currentPlayer;
        } else {
          currentPlayer = getPlayer(getCurrentPlayer().getOpponentTeam());
        }
        return true;
      }
      return false;
    }
    return false;
  }

  public void saveGame(String file) {
    GameManager gameManager = GameManager.getInstance();
    try{
      PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(new File(file))));
      // for(int i = 0; i < 8; i++){
      //   for(int j = 0; j < 7; j++){
      //     Piece piece = gameManager.getBoard().getSlot(i, j).getOccupiedPiece();
      //     if(piece != null){
      //       StringBuilder sB = new StringBuilder("").append(piece.getPlayer().getTeam()).append(":").append(piece.getPositionR()).append(":").append(piece.getPositionC()).append(":").append(piece.getType()).append(":").append(piece.isEaten());
      //       pw.println(sB.toString());
      //     }
      //   }
      // }
      List<Piece> piecesBlue = gameManager.getPlayer(Team.BLUE).getPieces();
      for(Piece p: piecesBlue){
        StringBuilder sB = new StringBuilder().append(p.getPlayer().getTeam()).append(":").append(p.getPositionR()).append(":").append(p.getPositionC()).append(":").append(p.getType()).append(":").append(p.isEaten());
        pw.println(sB.toString());
      }
      List<Piece> piecesRed = gameManager.getPlayer(Team.RED).getPieces();
      for(Piece p: piecesRed){
        StringBuilder sB = new StringBuilder().append(p.getPlayer().getTeam()).append(":").append(p.getPositionR()).append(":").append(p.getPositionC()).append(":").append(p.getType()).append(":").append(p.isEaten());
        pw.println(sB.toString());
      }
      pw.println("CurrentPlayer:" + gameManager.getCurrentPlayer().getTeam());
      pw.flush();
      pw.close();
    } catch (IOException e){
      e.printStackTrace();
    }
  }

  public void loadGame(String file){
    GameManager gameManager = GameManager.getInstance();
    gameManager.getBoard().clearBoard();
    Board board = gameManager.getBoard();
    BufferedReader input;
    try{
      input = new BufferedReader(new FileReader(new File(file)));
      
      String readLine;
      while ((readLine = input.readLine()) != null) {
        System.out.println(readLine);
        String[] tokens = readLine.split(":");
        if(tokens[0].equals("CurrentPlayer")){
          if(tokens[1].equals("RED")){
            gameManager.setCurrentPlayer(Team.RED);
          }
          else{
            gameManager.setCurrentPlayer(Team.BLUE);
          }
        } else {
          Player player = tokens[0].equals("RED") ? getPlayer(Team.RED): getPlayer(Team.BLUE);
          int r = Integer.parseInt(tokens[1]);
          int c = Integer.parseInt(tokens[2]);
          Type type = Type.getType(tokens[3]);
          Boolean isEaten = tokens[4].equals("false") ? false: true;
          List<Piece> pieces = player.getPieces();
          for(Piece p: pieces){
            if(p.getType() == type){
              p.setPositionR(r);
              p.setPositionC(c);
              if(isEaten)
                p.setEaten();
              player.pushPieceToEnd(p);
              break;
            }
          }
        }
      }
      input.close();
    } catch(RuntimeException e){
      e.printStackTrace();
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
    List<Piece> piecesBlue = gameManager.getPlayer(Team.BLUE).getPieces();
    System.out.println(piecesBlue);
    for(Piece p:piecesBlue){
      System.out.println(p);
      if(!p.isEaten())
        board.setSlotOccupiedPiece(p, p.getPositionR(), p.getPositionC());
    }
    List<Piece> piecesRed = gameManager.getPlayer(Team.RED).getPieces();
    for(Piece p:piecesRed){
      if(!p.isEaten())
        board.setSlotOccupiedPiece(p, p.getPositionR(), p.getPositionC());
    }
  }

  public Board getBoard() {
    return this.board;
  }

  public Player getPlayer(Team team) {
    for (Player player : players) {
      if (player.getTeam() == team) {
        return player;
      }
    }
    return null;
  }

  public Player getCurrentPlayer() {
    return currentPlayer;
  }

  public void setCurrentPlayer(Team team) {
    this.currentPlayer = getPlayer(team);
  }

  public Player getWinner() {
    return this.winner;
  }

  public GameState getGameState() {
    return this.gameState;
  }

}