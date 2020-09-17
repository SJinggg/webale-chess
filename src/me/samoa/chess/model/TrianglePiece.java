package me.samoa.chess.model;

import java.util.ArrayList;
import java.util.List;

/**
 * The Triangle piece can move any number of steps diagonally. It CANNOT skip
 * over other pieces.
 * @deprecated
 * Merged into a single class
 */
@Deprecated
public class TrianglePiece extends Piece{

  /**
   * Constructor of Triangle Piece
   * 
   * @param player Player of Triangle Piece
   * @param r Row of Triangle Piece
   * @param c Column of Triangle Piece
   */
  public TrianglePiece(Player player, int r, int c) {
    super(player, r, c);
    type = Type.Triangle;
  }

  /**
   * On turn movement of webale piece
   * 
   * @param turn the number of turn
   */
  @Override
  public void onTurn(int turn) {}

  /**
   * On move movement of webale piece
   * 
   * @param slot the slot of the piece intends to move to
   */
  @Override
  public void onMove(Slot slot) {}

  /**
   * Get all placeable slot for this piece on the webale board
   * 
   * @return list of the slot that is valid for potential movement
   */
  @Override
  public List<Slot> getAllPlaceableSlot() {
    // southeast, northwest, southwest, northeast
    int[][] increments = { { 1, 1 }, { -1, -1 }, { 1, -1 }, { -1, 1 } };
    ArrayList<Slot> placeableSlots = new ArrayList<>();

    for (int[] increment : increments) {
      int row = this.getPositionR() + increment[0];
      int col = this.getPositionC() + increment[1];

      while (!(row >= getBoard().getBoardHeight() || col >= getBoard().getBoardWidth() || row < 0 || col < 0)) {
        Slot slot = getBoard().getSlot(row, col);

        if (!slot.isOccupied()) {
          placeableSlots.add(slot);
          row += increment[0];
          col += increment[1];
          continue;
        }
        if (slot.getOccupiedPiece().getPlayer().getOpponentTeam() == getPlayer().getTeam()) {
          placeableSlots.add(slot);
          break;
        }
        break;
      }
    }
    return placeableSlots;
  }
}