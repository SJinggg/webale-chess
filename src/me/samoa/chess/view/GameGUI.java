package me.samoa.chess.view;

import me.samoa.chess.model.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import javax.swing.border.LineBorder;
import java.util.*;
import java.io.*;

public class GameGUI extends JFrame{
  private GameManager gameManager;
  private JMenuBar menu;
  private JButton startButton;
  private JButton saveButton;
  private JButton endButton;
  private JButton[][] buttons = new JButton[8][7];
  private HashMap<String, BufferedImage> chessImage = new HashMap<>();

  public GameGUI() {
    super("Webale Chess");

    setSize(500, 700);

    menu = new JMenuBar();
    startButton = new JButton("Start");
    endButton = new JButton("End");
    saveButton = new JButton("Save");
    
    menu.add(startButton);
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
      }
    }

    setChessImg();

    super.add(mpanel);

    setVisible(true);
    setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
    // Displays a pop-up window to confirm whether or not the player wants to exit the game
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e){
				JFrame frame = (JFrame)e.getSource();
		 
        if (JOptionPane.showConfirmDialog(mpanel, "Are you sure you want to exit the game? Don't forget to save!",
					  "Exit Game?", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)
		        setDefaultCloseOperation(EXIT_ON_CLOSE);
			}
		});
	}

  public void setChessImg () {
    try{
      chessImage.put("Sun Red", ImageIO.read(new File("resources/Sun-red.png")));
      chessImage.put("Sun Blue", ImageIO.read(new File("resources/Sun-blue.png")));
      chessImage.put("Arrow Red", ImageIO.read(new File("resources/Arrow-red.png")));
      chessImage.put("Arrow Blue", ImageIO.read(new File("resources/Arrow-blue.png")));
      chessImage.put("Chevron Red", ImageIO.read(new File("resources/Chevron-red.png")));
      chessImage.put("Chevron Blue", ImageIO.read(new File("resources/Chevron-blue.png")));
      chessImage.put("Plus Red", ImageIO.read(new File("resources/Plus-red.png")));
      chessImage.put("Plus Blue", ImageIO.read(new File("resources/Plus-blue.png")));
      chessImage.put("Triangle Red", ImageIO.read(new File("resources/Triangle-red.png")));
      chessImage.put("Triangle Blue", ImageIO.read(new File("resources/Triangle-blue.png")));
    } catch(IOException err) {
    }
    
  }
}
