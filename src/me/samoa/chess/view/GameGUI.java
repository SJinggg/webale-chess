package me.samoa.chess.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import javax.swing.border.LineBorder;
import java.util.*;
import java.io.*;
import me.samoa.chess.controller.*;

/**
 * This is the main part where GUI functions
 * 
 * @author Wong Man Yi
 * @author Koh Shi Jing
 * @author Nadia Ahmad Pirdaus
 */
public class GameGUI extends JFrame {

  private static final long serialVersionUID = 1L;
  private static JButton[][] buttons = new JButton[8][7];
  private static HashMap<String, BufferedImage> chessImage = new HashMap<>();
  private JPanel informationPanel;
  private static JLabel infoLabel;

  /**
   * Constructor of GameGUI
   */
  public GameGUI() {
    super("Webale Chess");

    setSize(700, 900);

    super.setJMenuBar(new Menu());

    JPanel mpanel = new JPanel(new GridLayout(8, 7));

    informationPanel = new JPanel();

    infoLabel = new JLabel("Press Start to begin", null, SwingConstants.HORIZONTAL);

    informationPanel.add(infoLabel);

    for (int i = 0; i < 8; i++) {
      for (int j = 0; j < 7; j++) {
        buttons[i][j] = new JButton();
        buttons[i][j].setName("(" + i + "," + j + ")");
        buttons[i][j].setBackground(Color.WHITE);
        buttons[i][j].setBorder(new LineBorder(Color.BLUE));
        buttons[i][j].addActionListener(new ButtonListener());
        mpanel.add(buttons[i][j]);
      }
    }

    chessImg();

    super.add(informationPanel, BorderLayout.PAGE_START);
    super.add(mpanel);

    setVisible(true);
    setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
    // Displays a pop-up window to confirm whether or not the player wants to exit the game
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
		 
        if (JOptionPane.showConfirmDialog(mpanel, "Are you sure you want to exit the game? Don't forget to save!",
          "Exit Game?", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)
          setDefaultCloseOperation(EXIT_ON_CLOSE);
			}
    });
    addComponentListener(new ComponentAdapter() {
      public void componentResized(ComponentEvent componentEvent) {
        chessWImage();
      }
  });
  }
  
  /**
   * Binds image with the chess piece's name
   */
  public void chessImg() {
    try {
      chessImage.put("Sun RED", ImageIO.read(new File("src/resources/Sun-red.png")));
      chessImage.put("Sun BLUE", ImageIO.read(new File("src/resources/Sun-blue.png")));
      chessImage.put("Sun BLUE", ImageIO.read(new File("src/resources/Sun-blue.png")));
      chessImage.put("Arrow RED", ImageIO.read(new File("src/resources/Arrow-red.png")));
      chessImage.put("Arrow BLUE", ImageIO.read(new File("src/resources/Arrow-blue.png")));
      chessImage.put("Chevron RED", ImageIO.read(new File("src/resources/Chevron-red.png")));
      chessImage.put("Chevron BLUE", ImageIO.read(new File("src/resources/Chevron-blue.png")));
      chessImage.put("Plus RED", ImageIO.read(new File("src/resources/Plus-red.png")));
      chessImage.put("Plus BLUE", ImageIO.read(new File("src/resources/Plus-blue.png")));
      chessImage.put("Triangle RED", ImageIO.read(new File("src/resources/Triangle-red.png")));
      chessImage.put("Triangle BLUE", ImageIO.read(new File("src/resources/Triangle-blue.png")));
      chessImage.put("Rotated Sun RED", ImageIO.read(new File("src/resources/Rot-Sun-red.png")));
      chessImage.put("Rotated Sun BLUE", ImageIO.read(new File("src/resources/Rot-Sun-blue.png")));
      chessImage.put("Rotated Sun BLUE", ImageIO.read(new File("src/resources/Rot-Sun-blue.png")));
      chessImage.put("Rotated Arrow RED", ImageIO.read(new File("src/resources/Rot-Arrow-red.png")));
      chessImage.put("Rotated Arrow BLUE", ImageIO.read(new File("src/resources/Rot-Arrow-blue.png")));
      chessImage.put("Rotated Chevron RED", ImageIO.read(new File("src/resources/Rot-Chevron-red.png")));
      chessImage.put("Rotated Chevron BLUE", ImageIO.read(new File("src/resources/Rot-Chevron-blue.png")));
      chessImage.put("Rotated Plus RED", ImageIO.read(new File("src/resources/Rot-Plus-red.png")));
      chessImage.put("Rotated Plus BLUE", ImageIO.read(new File("src/resources/Rot-Plus-blue.png")));
      chessImage.put("Rotated Triangle RED", ImageIO.read(new File("src/resources/Rot-Triangle-red.png")));
      chessImage.put("Rotated Triangle BLUE", ImageIO.read(new File("src/resources/Rot-Triangle-blue.png")));
    } catch(IOException err) {
      err.printStackTrace();
    }
  }

  /**
   * Disable all buttons and clear all icons
   */
  public static void disableButtons() {
    for(int i = 0; i < 8; i++) {
      for(int j = 0; j < 7; j++) {
        buttons[i][j].setIcon(null);
        buttons[i][j].setEnabled(false);
      }
    }
  }
  
  /**
   * Enable all buttons
   */
  public static void enableButtons() {
    for(int i = 0; i < 8; i++) {
      for(int j = 0; j < 7; j++) {
        buttons[i][j].setEnabled(true);
      }
    }
  }

  /**
   * Function to rotate the board when switching players and
   * match the Board slot with each Image if the chess piece exists
   */
  public static void turnButtons() {
    JButton[][] rot = new JButton[8][7];

    int n = 0;
    for(int i = 7; i >= 0; i--) {
      int m = 0;
      for(int j = 6; j >= 0; j--) {
        rot[i][j] = buttons[n][m];
        rot[i][j].setName("("+i+","+j+")");
        m++;
      }
      n++;
    }
    buttons = rot;
    chessWImage();
  }

  /**
   * Clears pieces displayed on board and re-adds them accordingly and 
   * match the Board slot with each Image if the chess piece exists
   */
  public static void chessWImage() {
    for(int i = 0; i < 8; i++) {
      for(int j = 0; j < 7; j++) {
        buttons[i][j].setIcon(null);
        buttons[i][j].setBackground(Color.white);
      }
    }
    for(int i = 0; i < 8; i++) {
      for(int j = 0; j < 7; j++) {
        BoardSlotInfo slotInfo = new BoardSlotInfo(i, j);
        if(slotInfo.isOccupied()) {
          if(chessImage.containsKey(slotInfo.getPieceName())) {
            Image imgIcon = (Image)chessImage.get(slotInfo.getPieceName());
            Image scaledIcon = imgIcon.getScaledInstance(buttons[i][j].getWidth(), buttons[i][j].getHeight(), java.awt.Image.SCALE_SMOOTH);
            buttons[i][j].setIcon(new ImageIcon(scaledIcon));
          }
        } 
      }
    }
  }

  /**
   * Label for display message of game status, movement, etc.
   * 
   * @param msg the message to be displayed
   */
  public static void setLabelMsg(String msg){
    infoLabel.setText(msg);
  }

  /**
   * Highlights valid possible movement of the selected chess piece
   *  
   * @param row The row coordinate of the selected chess piece
   * @param col The column coordinate of the selected chess piece
   */
  public static void possibleMoves(int row, int col) {
    buttons[row][col].setBackground(Color.yellow);
  }

  /**
   * Reset the highlighted possible movement if the player clicks on the chess piece again
   */
  public static void resetMoves() {
    for(int i = 0; i < 8; i++) {
      for(int j = 0; j < 7; j++) {
        buttons[i][j].setBackground(Color.white);
      }
    }
  }
}
