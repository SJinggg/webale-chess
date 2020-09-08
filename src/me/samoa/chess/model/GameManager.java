package me.samoa.chess.model;

public class GameManager {
  private Board board;
  public static GameManager instance;
  private Player[] players = {new Player("redPlayer", Team.RED), new Player("bluePlayer", Team.BLUE)};
  private Team turn = Team.RED;
  private static final GameState GameState = null;
  private static final me.samoa.chess.model.GameManager.GameState PLAY = null;
  private Player currentPlayer;
<<<<<<< HEAD
=======
  private Player winner;
>>>>>>> f25093e10b8ff0fc68b8a123bc7f61ccb885e04a

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
<<<<<<< HEAD
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
=======
>>>>>>> f25093e10b8ff0fc68b8a123bc7f61ccb885e04a
  }

  public void movement(Piece selectedPiece, Slot targetDest) {
      if(selectedPiece.getPlayer().getTeam() == getCurrentPlayer().getTeam()){
        if(!targetDest.isOccupied()){
          selectedPiece.onMove(targetDest);
        }
        else if(targetDest.getOccupiedPiece().getPlayer().getTeam() == selectedPiece.getPlayer().getOpponentTeam()){
          Piece previousPiece = targetDest.getOccupiedPiece();
          board.removeSlotOccupation(selectedPiece.getPositionR(), selectedPiece.getPositionC());
          selectedPiece.onMove(targetDest);
          previousPiece.setEaten();
        }
      } 
  }

  public boolean checkGameOver() { 
    if(players[0].getPieces(Type.Sun).isEaten()){
      this.winner = players[1];
      return true;
    }
    else if(players[1].getPieces(Type.Sun).isEaten()){
      this.winner = players[0];
      return true;
    }
    return false;
  } // here dun have { just now 0.0 // actually i dk those singleton blablabla eh xD but those UI stuff
    // together more easier?
    // try compile again i see xia 
// saw? // how geh 0.0 gg die /kay
// but hor it only error when erm how do i show u ... erm u click on me and see  i show u
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