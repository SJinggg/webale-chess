package me.samoa.chess.view;

/**
 * Game Window when the program is open
 * 
 * @author Casey Teh Qi Shi
 */
public class GameWindow implements Window {
  
  /**
   * Singleton design pattern
   * Get the instance of the GameWindow or create one if not exist
   */
  public static GameWindow getInstance() {
    if (instance == null) {
      instance = new GameWindow();
    }
    return instance;
  }

  private static GameWindow instance;

  /**
   * On open of the game window
   */
  @Override
  public void open() {
    new GameGUI();
  }

  /**
  * On close of the game window
  */
  @Override
  public void close() { }

  /**
   * Game window setup
   */
  @Override
  public void setup() { }

}
