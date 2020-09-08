package me.samoa.chess.view;

import me.samoa.chess.model.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GameGUI extends JFrame{
  private GameManager gameManager;
  private JMenuBar menu;
  private JButton startButton;
  private JButton saveButton;
  private JButton endButton;

  public GameGUI() {
    super("Webale Chess");

    gameManager.getInstance();

    setSize(500, 700);

    menu = new JMenuBar();
    startButton = new JButton("Start");
    endButton = new JButton("End");
    saveButton = new JButton("Save");
    
    menu.add(startButton);
    menu.add(endButton);
    menu.add(saveButton);

    super.setJMenuBar(menu);

    setVisible(true);
    setDefaultCloseOperation(EXIT_ON_CLOSE);

  }
}
