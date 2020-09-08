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
  private JButton[][] buttons = new JButton[8][7];
  private Map<Type, Buffered> chessImage = new HashMap<>();

  public GameGUI() {
    super("Webale Chess");

    setSize(500, 700);

    menu = new JMenuBar();
    startButton = new JButton("Start");
    endButton = new JButton("End");
    saveButton = new JButton("Save");
    
    menu.add( startButton);
    menu.add(endButton);
    menu.add(saveButton);

    super.setJMenuBar(menu);

    JPanel mpanel = new JPanel(new GridLayout(8,7));

    for (int i = 0; i < 8; i++) {
      for (int j = 0; j < 7; j++) {
        buttons[i][j] = new JButton();
        buttons[i][j].setName("("+j+","+i+")");
        buttons[i][j].setText("("+j+","+i+")");
        buttons[i][j].setBackground(Color.WHITE);
        buttons[i][j].setBorder(new LineBorder(Color.BLUE));
        buttons[i][j].addMouseListener(new ButtonListener());
        mpanel.add(buttons[i][j]); 
      } // listener add in control e
    }

    super.add(mpanel);

    setVisible(true);
    setDefaultCloseOperation(EXIT_ON_CLOSE);
  }
}
