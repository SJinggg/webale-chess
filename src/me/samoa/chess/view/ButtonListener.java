package me.samoa.chess.view;

import java.awt.Color;
import java.awt.event.*;
import java.util.List;
import javax.swing.*;

import me.samoa.chess.controller.*;
import me.samoa.chess.model.GameManager;

/**
 * The listener for every slot in the Board
 * 
 * @author Koh Shi Jing
 */
public class ButtonListener implements ActionListener {
  private static List<MovementInfo> movementInfos;

  @Override
  public void actionPerformed(ActionEvent evt) {
    JButton clicked = (JButton)evt.getSource();
    int selectedRow = Character.getNumericValue(clicked.getName().charAt(1));
    int selectedCol = Character.getNumericValue(clicked.getName().charAt(3));
    clicked.setBackground(Color.yellow);

    System.out.println(clicked.getName());
    String selPiece = GameManager.getInstance().getBoard().getSlotOccupied(selectedRow, selectedCol) ? GameManager.getInstance().getBoard().getSlot(selectedRow, selectedCol).getOccupiedPiece().getType().toString() + " " : "";
    GameGUI.setLabelMsg("Selected " + selPiece + "on " + clicked.getName());
    if(movementInfos == null) {
      movementInfos = API.getInstance().getState().onSelect(selectedRow, selectedCol);
      if (movementInfos == null) {
        GameGUI.setLabelMsg("Invalid movement");
        System.out.println(API.getInstance().getState().getStateType() + "State");
        clicked.setBackground(Color.white);
        return;
      }
      for (MovementInfo movementInfo : movementInfos) {
        System.out.println(String.format("[%d %d] : %s", movementInfo.getRow(), movementInfo.getCol(), movementInfo.hasOpponent() ? "Has Opponent" : "No Opponent"));
        GameGUI.possibleMoves(movementInfo.getRow(), movementInfo.getCol());
      }
      System.out.println();
    } else {
      List<PositionInfo> positionInfos = API.getInstance().getState().onMove(selectedRow,selectedCol);
      if (positionInfos == null) {
        System.out.println(API.getInstance().getState().getStateType() + "State");
        clicked.setBackground(Color.white);
        movementInfos = null;
        GameGUI.resetMoves();
        return;
      }
      for (PositionInfo positionInfo : positionInfos) {
        System.out.println(String.format("[%d %d] : %s %s %s",
        positionInfo.getRow(), positionInfo.getCol(), positionInfo.isNorth() ? "North" : "South", positionInfo.getTeam(), positionInfo.getType()));
      }
        
      GameGUI.turnButtons();
      System.out.println();
      GameStatusInfo statusInfo = API.getInstance().getState().onCheck();
      GameGUI.setLabelMsg(String.format("Game Status: %s, Current Turn: %s, Winner: %s",
      statusInfo.getStatus(), statusInfo.getCurrentTurn(), statusInfo.getWinner()));
      if(statusInfo.getWinner() != null){
        JOptionPane.showMessageDialog(null, statusInfo.getWinner() + " has won!",  
        "information", JOptionPane.INFORMATION_MESSAGE);
        Menu.startAction.setEnabled(true);
        GameGUI.disableButtons();
      }
      System.out.println();
      movementInfos = null;
    }
  }
}
