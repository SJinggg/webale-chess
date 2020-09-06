package me.samoa.chess.model;

public class Board {
  
  private final int BOARDHEIGHT = 8, BOARDWIDTH = 7;
  private Slot[][] slots = new Slot[BOARDHEIGHT][BOARDWIDTH];

  public Board(){
    for(int i = 0; i < BOARDHEIGHT; i++) {
      for(int j = 0; j < BOARDWIDTH; j++) {
        slots[i][j] = new Slot(i, j); 
      }
    }
  }

  public int getBoardHeight(){
    return BOARDHEIGHT; 
  }

  public int getBoardWidth() {
    return BOARDWIDTH; 
  }

  public Slot[][] getSlots() {
    return slots;
  }
}