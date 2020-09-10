package me.samoa.chess.debug;

import java.util.List;

import me.samoa.chess.controller.API;
import me.samoa.chess.controller.GameStatusInfo;
import me.samoa.chess.controller.MovementInfo;
import me.samoa.chess.controller.PositionInfo;

public class MoveAction implements Action {

  private int selectedRow, selectedCol, moveRow, moveCol;

  public MoveAction(int selectedRow, int selectedCol, int moveRow, int moveCol) {
    this.selectedRow = selectedRow;
    this.selectedCol = selectedCol;
    this.moveRow = moveRow;
    this.moveCol = moveCol;
  }

  @Override
  public void run() {
    try {
      List<MovementInfo> movementInfos = API.getInstance().getState().onSelect(selectedRow, selectedCol);
      if (movementInfos == null) {
        System.out.println(API.getInstance().getState().getStateType() + "State");
        return;
      }
      for (MovementInfo movementInfo : movementInfos) {
        System.out.println(String.format("[%d %d] : %s", movementInfo.getRow(), movementInfo.getCol(), movementInfo.hasOpponent() ? "Has Opponent" : "No Opponent"));
      }
      System.out.println();
      List<PositionInfo> positionInfos = API.getInstance().getState().onMove(moveRow,moveCol);
      if (positionInfos == null) {
        System.out.println(API.getInstance().getState().getStateType() + "State");
        return;
      }
      for (PositionInfo positionInfo : positionInfos) {
        System.out.println(String.format("[%d %d] : %s %s %s",
        positionInfo.getRow(), positionInfo.getCol(), positionInfo.isNorth() ? "North" : "South", positionInfo.getTeam(), positionInfo.getType()));
      }
      System.out.println();
      GameStatusInfo statusInfo = API.getInstance().getState().onCheck();
      System.out.println(String.format("Game Status: %s, Current Turn: %s, Winner: %s",
      statusInfo.getStatus(), statusInfo.getCurrentTurn(), statusInfo.getWinner()));
      System.out.println();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }


  
}
