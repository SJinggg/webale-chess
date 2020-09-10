package me.samoa.chess.debug;

public class IllegalMoveCase implements Case {

  @Override
  public void test() {
    new GameStartAction().run();

    //move red arrow piece to illegal position
    new MoveAction(6, 6, 6, 5).run();

    //select empty space
    new MoveAction(6, 5, 6, 6).run();

    //red player select blue arrow
    new MoveAction(1, 6, 2, 6).run();

  }
  
}