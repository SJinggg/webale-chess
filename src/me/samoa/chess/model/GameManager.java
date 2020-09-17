package me.samoa.chess.model;

import java.io.*;
import java.util.List;

/**
 * Manage all the movements and logical operations of the game
 * 
 * @author Casey Teh Qi Shi
 * @author Wong Man Yi
 * @author Koh Shi Jing
 * @author Nadia Ahmad Pirdaus
 */
public class GameManager {
  public enum GameState {
    PLAY, END
  };

  public static GameManager instance;

  
  /**
   * Singleton Design Pattern
   * Get the instance of the GameManager or create a new one if not exist
   */
  public static GameManager getInstance() {
    if (instance == null) {
      instance = new GameManager();
    }
    return instance;
  }

  /**
   * Create a new instance of GameManger
   */
  public static void newInstance() {
    instance = new GameManager();
  }

  private Board board;
  private Player[] players = {new Player("redPlayer", Team.RED), new Player("bluePlayer", Team.BLUE)};
  private Player currentPlayer;
  private Player winner;
  private GameState gameState;

  /**
   * Constructor of GameManager
   */
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

  /**
   * Validates the movement performed
   * 
   * @param selectedPiece the piece that has been selected to move
   * @param targetDest the targeted location to move the piece
   * @return <code>true</code> if the movement performed is valid; <code>false</code> otherwise.
   */
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

  /**
   * Perform the game saving function
   * 
   * @param file the file name to save the state of game
   */
  public void saveGame(String file) {
    GameManager gameManager = GameManager.getInstance();
    File directory = new File("src/save");
    if(!directory.exists()) {
      directory.mkdir();
    }
    try{
      PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(new File(file))));
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

  /**
   * Perform the load game function
   * 
   * @param file the filename where the game state should be loaded from
   */
  public void loadGame(String file){
    GameManager gameManager = GameManager.getInstance();
    gameManager.getBoard().clearBoard();
    Board board = gameManager.getBoard();
    BufferedReader input;
    try{
      input = new BufferedReader(new FileReader(new File(file)));
      
      String readLine;
      while ((readLine = input.readLine()) != null) {
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
    for(Piece p:piecesBlue){
      if(!p.isEaten())
        board.setSlotOccupiedPiece(p, p.getPositionR(), p.getPositionC());
    }
    List<Piece> piecesRed = gameManager.getPlayer(Team.RED).getPieces();
    for(Piece p:piecesRed){
      if(!p.isEaten())
        board.setSlotOccupiedPiece(p, p.getPositionR(), p.getPositionC());
    }
  }

  /**
   * Get the webale board
   * 
   * @return the board 
   */
  public Board getBoard() {
    return this.board;
  }

  /**
   * Get the player who belongs to the team
   * 
   * @param team The team where the player belongs to
   * @return the player or null
   */
  public Player getPlayer(Team team) {
    for (Player player : players) {
      if (player.getTeam() == team) {
        return player;
      }
    }
    return null;
  }

  /**
   * Get current player of this round
   * 
   * @return player of this round
   */
  public Player getCurrentPlayer() {
    return currentPlayer;
  }

  /**
   * Set the current player of this round
   * 
   * @param team the team the player belongs to
   */
  public void setCurrentPlayer(Team team) {
    this.currentPlayer = getPlayer(team);
  }

  /**
   * Get the winner if exist
   * 
   * @return the winner or null
   */
  public Player getWinner() {
    return this.winner;
  }

  /**
   * Get current game state
   * 
   * @return current game state
   */
  public GameState getGameState() {
    return this.gameState;
  }

}