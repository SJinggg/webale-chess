package me.samoa.chess.debug;

public class CaptureSunCase implements Case {

  @Override
  public void test() {
    new GameStartAction().run();

    //testSelectMoveRedArrowPiece
    new MoveAction(6, 2, 5, 2).run();

    //testSelectMoveBlueArrowPiece
    new MoveAction(1, 6, 2, 6).run();

    //testSelectMoveRedTrianglePiece
    new MoveAction(7, 1, 5, 3).run();

    //testSelectMoveBlueArrowPiece
    new MoveAction(2, 6, 3, 6).run();

    //testSelectRedPlusCapture
    new MoveAction(5, 3, 0, 3).run();

  }
  
}
