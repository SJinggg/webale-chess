package me.samoa.chess.view;

import me.samoa.chess.model.*;

import java.awt.Color;
import java.awt.event.*;
import java.util.List;
import javax.swing.*;

import me.samoa.chess.controller.*;

public class ButtonListener implements ActionListener {
    private static List<MovementInfo> movementInfos;

    @Override
    public void actionPerformed(ActionEvent evt) {
        JButton clicked = (JButton)evt.getSource();
        int selectedRow = Character.getNumericValue(clicked.getName().charAt(1));
        int selectedCol = Character.getNumericValue(clicked.getName().charAt(3));
        clicked.setBackground(Color.yellow);

        System.out.println(clicked.getName());
<<<<<<< HEAD
        
=======
        //perform movement here...? // listener not inside control
        GameGUI.setLabelMsg("Select on" + clicked.getName());
>>>>>>> e192919adf5d7c03aa5793b49382f702810edb2e
        if(movementInfos == null){
            movementInfos = API.getInstance().getState().onSelect(selectedRow, selectedCol);
            if (movementInfos == null) {
                GameGUI.setLabelMsg("Invalid");
                System.out.println(API.getInstance().getState().getStateType() + "State");
                clicked.setBackground(Color.white);
                return;
              }
              for (MovementInfo movementInfo : movementInfos) {
                System.out.println(String.format("[%d %d] : %s", movementInfo.getRow(), movementInfo.getCol(), movementInfo.hasOpponent() ? "Has Opponent" : "No Opponent"));
                GameGUI.possibleMoves(movementInfo.getRow(), movementInfo.getCol());
              }
              System.out.println();
        }else {
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
              }
              System.out.println();
              movementInfos = null;
        }
    }

}
