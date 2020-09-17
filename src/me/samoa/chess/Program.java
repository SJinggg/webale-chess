package me.samoa.chess;

import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import me.samoa.chess.view.GameWindow;
import me.samoa.chess.view.Window;

/**
 * @author Casey Teh Qi Shi
 */
public class Program {

  public static Program getInstance() {
    if (instance == null) {
      instance = new Program();
    }
    return instance;
  }

  private static Program instance;

  private Window mainWindow;

  private Program() {
    mainWindow = new GameWindow();
  }

  public void run() {
    mainWindow.open();
  }

}