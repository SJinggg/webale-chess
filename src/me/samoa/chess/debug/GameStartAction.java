package me.samoa.chess.debug;

import java.util.List;

import me.samoa.chess.controller.API;
import me.samoa.chess.controller.GameStatusInfo;
import me.samoa.chess.controller.PositionInfo;

public class GameStartAction implements Action {

  @Override
  public void run() {
    // test board reset
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

    // test game initial state
    try {
      GameStatusInfo initState = API.getInstance().getState().onStart();
      System.out.println(String.format("Game Status: %s, Current Turn: %s, Winner: %s",
      initState.getStatus(), initState.getCurrentTurn(), initState.getWinner()));
      System.out.println();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
  
}
