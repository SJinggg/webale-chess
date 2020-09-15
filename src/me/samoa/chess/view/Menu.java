package me.samoa.chess.view;

import javax.swing.*;

import java.awt.event.*;
import java.util.List;

import me.samoa.chess.controller.API;
import me.samoa.chess.controller.GameStatusInfo;
import me.samoa.chess.controller.PositionInfo;

public class Menu extends JMenuBar {
  private JButton startButton;
  private JButton saveButton;
  private JButton endButton;
  private JButton loadButton;

  public Menu() {
    super();

    startButton = new JButton("Start");
    endButton = new JButton("End");
    loadButton = new JButton("Load");
    saveButton = new JButton("Save");

    startButton.addMouseListener(new MouseAdapter() {
      public void mouseClicked(MouseEvent evt) {
        try {
          List<PositionInfo> postionInfos = API.getInstance().getState().onReset();
          for (PositionInfo positionInfo : postionInfos) {
            System.out.println(String.format("[%d %d] : %s %s %s",
            positionInfo.getRow(), positionInfo.getCol(), positionInfo.isNorth() ? "North" : "South", positionInfo.getTeam(), positionInfo.getType()));
          }
          System.out.println();
        } catch (Exception e) {
          e.printStackTrace();
        }

        try {
          GameStatusInfo initState = API.getInstance().getState().onStart();
          System.out.println(String.format("Game Status: %s, Current Turn: %s, Winner: %s",
          initState.getStatus(), initState.getCurrentTurn(), initState.getWinner()));
          System.out.println();
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    });

    endButton.addMouseListener(new MouseAdapter() {
      public void mouseClicked(MouseEvent evt) {
        try {
          GameStatusInfo gameStatus = new GameStatusInfo();
          if(gameStatus.getWinner() != null){
            System.out.println("Winner is " + gameStatus.getWinner());
          }
          //confirmation -> clear & reset
          
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    });
    super.add(startButton);
    super.add(saveButton);
    super.add(loadButton);
    super.add(endButton);
  }

}