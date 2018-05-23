package org.otterlabs.hosting.gord1861.FinalChess;

import java.util.List;

/**
 * Abstract class that determines if a given movement is valid for a given board.  Classes handling movement rules for
 * individual piece types should subclass this class and implements its calculatePath method.
 * @author Aaron Gordon
 *
 */
public abstract class PieceBehavior
{
   public boolean isValidMove(Board board, ChessVector position, ChessVector destination)
   {
      // There must be a move
      if (position.equals(destination))
      {
         return false;
      }
      
      // There must be a piece to move
      if (board.getPiece(position) == null)
      {
         return false;
      }
      
      // The destination must be on the board
      if (!board.isOnBoard(destination))
      {
         return false;
      }
      
      // The player must not move onto a friendly piece
      if (board.getPiece(destination) != null)
      {
         Piece srcPiece = board.getPiece(position);
         Piece dstPiece = board.getPiece(destination);
         
         if (srcPiece.getTeam() == dstPiece.getTeam())
         {
            return false;
         }
      }
      
      // A path to the destination must exist
      List<ChessVector> path = calculatePath(board, position, destination);
      if (path.isEmpty())
      {
         return false;
      }

      // The path must not be blocked
      for (ChessVector step : path)
      {
         if (board.getPiece(step) != null &&
             !step.isEqual(destination))
         {
            return false;
         }
      }
      
      return true;
   }
   
   protected abstract List<ChessVector> calculatePath(Board board, ChessVector position, ChessVector destination);
}
