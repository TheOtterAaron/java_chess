package org.otterlabs.hosting.gord1861.FinalChess;

import java.util.ArrayList;
import java.util.List;

/**
 * Stores data related to a game of chess, including the board and a list of captured pieces used for calculating
 * scores in some chess variants.  Exposes methods for accessing and mutating said data.
 * @author Aaron Gordon
 *
 */
public class GameModel
{
   private Board board;
   private List<Piece> capturedPieces;
   
   public GameModel(int boardWidth, int boardHeight)
   {
      board = new Board(boardWidth, boardHeight);
      capturedPieces = new ArrayList<Piece>();
   }
   
   public Board getBoard()
   {
      return board;
   }
   
   public Piece[] getCapturedPieces()
   {
      return (Piece[])(capturedPieces.toArray());
   }
   
   public void addCapturedPiece(Piece piece)
   {
      capturedPieces.add(piece);
   }
}
