package me.samoa.chess.model;

public class GameManager {

  private static final GameState GameState = null;
  public static GameManager instance;

  public static GameManager getInstance() {
    if (instance == null) {
      instance = new GameManager();
    }
    return instance;
  }

  public enum GameState {
    PLAY, END
  }

  private Board board;
  private Player[] player = {new Player("redPlayer", Team.RED), new Player("bluePlayer", Team.BLUE)};
  private Team turn = Team.RED;

  private GameManager() {
    Team redteam = Team.RED;
    Team blueteam = Team.BLUE;
    int boardHeight = board.getBoardHeight();
    
    for(Player i: player){
      int row = (i.getPlayerName().equals("redPlayer")) ? 0 : boardHeight-1;
      i.addPieces(new SunPiece(i, new Slot(row, 3), i.getTeam()));

      i.addPieces(new ChevronPiece(i, new Slot(row, 2), i.getTeam()));
      i.addPieces(new ChevronPiece(i, new Slot(row, 4), i.getTeam()));

      i.addPieces(new TrianglePiece(i, new Slot(row, 1), i.getTeam()));
      i.addPieces(new TrianglePiece(i, new Slot(row, 5), i.getTeam()));

      i.addPieces(new PlusPiece(i, new Slot(row, 0), i.getTeam()));
      i.addPieces(new PlusPiece(i, new Slot(row, 6), i.getTeam()));

      row = (i.getPlayerName().equals("redPlayer")) ? (row += 1) : (row -= 1);
      for(int j = 0; j < 7; j++)
        i.addPieces(new ArrowPiece(i, new Slot(row, j++), i.getTeam()));
    }
    // redPlayer.addPieces(new SunPiece(redPlayer, new Slot(0, 3), redteam));;
    // bluePlayer.addPieces(new SunPiece(bluePlayer, new Slot(7, 3), blueteam));
    
    // redPlayer.addPieces(new ChevronPiece(redPlayer, new Slot(0, 2), redteam));
    // redPlayer.addPieces(new ChevronPiece(redPlayer, new Slot(0, 4), redteam));
    // bluePlayer.addPieces(new ChevronPiece(bluePlayer, new Slot(7, 2), blueteam));
    // bluePlayer.addPieces(new ChevronPiece(bluePlayer, new Slot(7, 4), blueteam));

    // redPlayer.addPieces(new ChevronPiece(redPlayer, new Slot(0, 2), redteam));
    // redPlayer.addPieces(new ChevronPiece(redPlayer, new Slot(0, 4), redteam));
    
  }

  public Board getBoard() {
    return this.board;
  }
  
  public String getTurn() {
    return turn.toString();
  }

  // public void nextTurn() {
  //   if (GameManager.GameState == GameState.PLAY) {
  //     Player player = getCurrentPlayer();
  //     player.addTurn();
  //   }
  // }
}
