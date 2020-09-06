package me.samoa.chess.model;

public class GameManager {
  private Board board;
  public static GameManager instance;
  private Player[] players = {new Player("redPlayer", Team.RED), new Player("bluePlayer", Team.BLUE)};
  private Team turn = Team.RED;
  private static final GameState GameState = null;
  private static final me.samoa.chess.model.GameManager.GameState PLAY = null;
  private Player currentPlayer;

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
    board = new Board();
    int boardHeight = board.getBoardHeight();

    for(Player i: players){
      int row = (i.getPlayerName().equals("bluePlayer")) ? 0 : boardHeight-1;

      i.addPieces(new SunPiece(i, row, 3));
      board.setSlotOccupiedPiece(i.getPieces());

      i.addPieces(new ChevronPiece(i, row, 2));
      board.setSlotOccupiedPiece(i.getPieces());
      i.addPieces(new ChevronPiece(i, row, 4));
      board.setSlotOccupiedPiece(i.getPieces());

      i.addPieces(new TrianglePiece(i, row, 1));
      board.setSlotOccupiedPiece(i.getPieces());
      i.addPieces(new TrianglePiece(i, row, 5));
      board.setSlotOccupiedPiece(i.getPieces());

      i.addPieces(new PlusPiece(i, row, 0));
      board.setSlotOccupiedPiece(i.getPieces());
      i.addPieces(new PlusPiece(i, row, 6));
      board.setSlotOccupiedPiece(i.getPieces());

      row = (i.getPlayerName().equals("bluePlayer")) ? (row += 1) : (row -= 1);
      for(int j = 0; j < 7; j++){
        i.addPieces(new ArrowPiece(i, row, j++));
        board.setSlotOccupiedPiece(i.getPieces());
      }
    } 
  }

  public void movement(Piece selectedPiece, Slot targetDest) {
      if(selectedPiece.getPlayer().getTeam() == getCurrentPlayer().getTeam()){
        if(!targetDest.isOccupied()){
          selectedPiece.onMove(targetDest);
        }
        else if(targetDest.getOccupiedPiece().getPlayer().getTeam() == selectedPiece.getPlayer().getOpponentTeam()){
          selectedPiece.onMove(targetDest);
        }
      } 
  }

  public Board getBoard() {
    return this.board;
  }

  public Player getCurrentPlayer() {
    return currentPlayer;
  }

  public String getTurn() {
    return turn.toString();
  }

  public void nextTurn() {
    if (GameManager.GameState == PLAY) {
      Player player = getCurrentPlayer();
      player.addTurn();
    }
  }

}