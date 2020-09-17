package me.samoa.chess.view;

import javax.swing.*;

import java.awt.event.*;
import java.util.List;

import me.samoa.chess.controller.API;
import me.samoa.chess.controller.ClearState;
import me.samoa.chess.controller.GameStatusInfo;
import me.samoa.chess.controller.PositionInfo;
import me.samoa.chess.controller.ReadyState;
import me.samoa.chess.model.GameManager;
import me.samoa.chess.model.Team;

/**
 * The elements of menu that will be used in the game
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
        API api = API.getInstance();
        api.setState(new ClearState(api));
        startAction.setEnabled(true);
        GameGUI.setLabelMsg("Press Start to begin");
        setEnabled(false);
      }
    };

    startAction = new AbstractAction("Start") {
      @Override
      public void actionPerformed(ActionEvent evt) {
        try {
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
        String inputValue = JOptionPane.showInputDialog("Please input the name of the file to save");
        if (inputValue != null && !inputValue.equals("")) {
          inputValue = inputValue.replaceAll("\\.", "");
          gameManager.saveGame("src/save/" + inputValue + ".txt");
        }
      }
    };

    Action loadAction = new AbstractAction("Load") {
      @Override
      public void actionPerformed(ActionEvent evt) {
        GameManager gameManager = GameManager.getInstance();
        String inputValue = "";
        while (inputValue == null || inputValue.equals(""))
          inputValue = JOptionPane.showInputDialog("Please input the name of the file to load");
        gameManager.loadGame("src/save/" + inputValue + ".txt");
        API api = API.getInstance();
        api.setState(new ReadyState(api));
        try {
          GameStatusInfo loadState = API.getInstance().getState().onStart();
          GameGUI.setLabelMsg(String.format("Game Status: %s, Current Turn: %s, Winner: %s",
          loadState.getStatus(), loadState.getCurrentTurn(), loadState.getWinner()));
          System.out.println();
          if(loadState.getCurrentTurn() == Team.BLUE)
            GameGUI.turnButtons();
        } catch (Exception e) {
          e.printStackTrace();
        }
        startAction.setEnabled(false);
        GameGUI.chessWImage();
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