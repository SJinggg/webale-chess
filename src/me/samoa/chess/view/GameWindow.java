package me.samoa.chess.view;

public class GameWindow implements Window {
  
  public static GameWindow getInstance() {
    if (instance == null) {
      instance = new GameWindow();
    }
    return instance;
  }

  private static GameWindow instance;

  @Override
  public void open() {

  }

  @Override
  public void close() {

  }

  @Override
  public void setup() {

  }

}
