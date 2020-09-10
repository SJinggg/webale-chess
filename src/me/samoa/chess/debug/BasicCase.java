package me.samoa.chess.debug;

public class BasicCase implements Case {

  @Override
  public void test() {
    new GameStartAction().run();

    //testSelectMoveRedChevronPiece
    new MoveAction(7, 2, 5, 3).run();

    //testSelectMoveBlueArrowPiece
    new MoveAction(1, 4, 3, 4).run();

    //testSelectMoveRedSunPiece
    new MoveAction(7, 3, 7, 2).run();

    //testSelectMoveBlueTrianglePiece
    new MoveAction(0, 5, 5, 0).run();

    //testSelectRedArrowCapture
    new MoveAction(6, 0, 5, 0).run();

  }
  
}
