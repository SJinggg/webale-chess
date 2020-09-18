package me.samoa.chess.view;

import javax.swing.*;

import java.awt.event.*;
import java.io.FileNotFoundException;
import java.util.List;

import me.samoa.chess.controller.API;
import me.samoa.chess.controller.ClearState;
import me.samoa.chess.controller.GameStatusInfo;
import me.samoa.chess.controller.MovementInfo;
import me.samoa.chess.controller.PositionInfo;
import me.samoa.chess.controller.ReadyState;
import me.samoa.chess.model.GameManager;
import me.samoa.chess.model.Team;
import me.samoa.chess.model.GameManager.GameState;

/**
 * The elements of menu that will be used in the game
 * 
 * @author Koh Shi Jing
 */
public class Menu extends JMenuBar {
  private JMenuItem start;
  private JMenuItem save;
  private JMenuItem end;
  private JMenuItem load;
  protected static Action startAction;

  /**
   * Menu constructor, main components of menu are here
   */
  public Menu() {
    super();
    Action endAction = new AbstractAction("End") {
      @Override
      public void actionPerformed(ActionEvent evt) {
        if(GameManager.getInstance().getCurrentPlayer().getPlayerName() == "bluePlayer") GameGUI.turnButtons();
        API api = API.getInstance();
        api.setState(new ClearState(api));
        startAction.setEnabled(true);
        GameGUI.disableButtons();
        GameGUI.setLabelMsg("Press Start to begin");
        setEnabled(false);
      }
    };

    startAction = new AbstractAction("Start") {
      @Override
      public void actionPerformed(ActionEvent evt) {
        GameStatusInfo previousStatus = new GameStatusInfo();
        try {
          GameGUI.enableButtons();
          List<PositionInfo> postionInfos = API.getInstance().getState().onReset();
          for (PositionInfo positionInfo : postionInfos) {
            System.out.println(String.format("[%d %d] : %s %s %s", positionInfo.getRow(), positionInfo.getCol(),
                positionInfo.isNorth() ? "North" : "South", positionInfo.getTeam(), positionInfo.getType()));
          }
          System.out.println();
        } catch (Exception e) {
          e.printStackTrace();
        }

        try {
          GameStatusInfo initState = API.getInstance().getState().onStart();
          GameGUI.setLabelMsg(String.format("Game Status: %s, Current Turn: %s, Winner: %s", initState.getStatus(),
              initState.getCurrentTurn(), initState.getWinner()));
          System.out.println();
          if(initState.getCurrentTurn() != previousStatus.getCurrentTurn())
          GameGUI.turnButtons();
        } catch (Exception e) {
          e.printStackTrace();
        }
        
        setEnabled(false);
        endAction.setEnabled(true);
        GameGUI.chessWImage();
      }
    };

    Action saveAction = new AbstractAction("Save") {
      @Override
      public void actionPerformed(ActionEvent evt) {
        GameManager gameManager = GameManager.getInstance();
        String inputValue = JOptionPane.showInputDialog(null, "Please input what you would like to save your file as:", "Save Game", JOptionPane.QUESTION_MESSAGE);
        if (inputValue != null && !inputValue.equals("")) {
          inputValue = inputValue.replaceAll("\\.", "");
          gameManager.saveGame("src/save/" + inputValue + ".txt");
          JOptionPane.showMessageDialog(null, "Game saved successfully!", "Save game", JOptionPane.INFORMATION_MESSAGE);
        }
      }
    };

    Action loadAction = new AbstractAction("Load") {
      @Override
      public void actionPerformed(ActionEvent evt) {
        GameManager gameManager = GameManager.getInstance();
        String inputValue = "";
        GameStatusInfo previousStatus = new GameStatusInfo();
        boolean fileExist = false;
        while (inputValue == null || inputValue.equals("") || !fileExist) {
          inputValue = JOptionPane.showInputDialog(null, "Please input the name of your save file:", "Load Game", JOptionPane.QUESTION_MESSAGE);
          if(inputValue != null) {
            try {
              gameManager.loadGame("src/save/" + inputValue + ".txt");
              fileExist = true; 
            }catch(FileNotFoundException e) {
              JOptionPane.showMessageDialog(null, "File " + inputValue + ".txt does not exist", "Wrong file?", JOptionPane.WARNING_MESSAGE);
              fileExist = false;
            }
          }
          else return;
        }
        API api = API.getInstance();
        api.setState(new ReadyState(api));
        try {
          GameStatusInfo loadState = API.getInstance().getState().onStart();
          GameGUI.setLabelMsg(String.format("Game Status: %s, Current Turn: %s, Winner: %s",
          loadState.getStatus(), loadState.getCurrentTurn(), loadState.getWinner()));
          System.out.println();
          System.out.println(loadState.getCurrentTurn());
          if(loadState.getCurrentTurn() != previousStatus.getCurrentTurn())
            GameGUI.turnButtons();
        } catch (Exception e) {
          e.printStackTrace();
        }
        startAction.setEnabled(false);
        endAction.setEnabled(true);
        GameGUI.enableButtons();
        GameGUI.chessWImage();
        GameGUI.enableButtons();
        JOptionPane.showMessageDialog(null, "Game loaded successfully!", "Loaded game", JOptionPane.INFORMATION_MESSAGE);
      }
    };

    start = new JMenuItem(startAction);
    save = new JMenuItem(saveAction);
    load = new JMenuItem(loadAction);
    end = new JMenuItem(endAction);

    super.add(start);
    super.add(save);
    super.add(load);
    super.add(end);
    
  }

}