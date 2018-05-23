package org.otterlabs.hosting.gord1861.FinalChess;

import java.util.ArrayList;
import java.util.List;

/**
 * Subclasses PieceBehavior to implement movement rules for the pawn.  The pawn may only move forward one square, but
 * may move forward two squares on its first movement.  The pawn is only able to capture pieces in an adjacent,
 * diagonal square, referred to as En Passant.
 * @author Aaron Gordon
 *
 */
public class PawnBehavior extends PieceBehavior
{
   @Override
   protected List<ChessVector> calculatePath(Board board, ChessVector position, ChessVector destination)
   {
      List<ChessVector> path = new ArrayList<ChessVector>();
      
      ChessVector delta = destination.subtract(position);
      PieceTeam team = board.getPiece(position).getTeam();
      
      if (team == PieceTeam.WHITE && delta.getY() > 0)
      {
         if (delta.getX() == 0)
         {
            // First Move
            if (position.getY() == 1 &&
                delta.getY() == 2)
            {
               path.add(new ChessVector(position.getX(), position.getY() + 1));
               path.add(destination);
            }
            else if (delta.getY() == 1)
            {
               path.add(destination);
            }
         }
         // En Passant
         else if (Math.abs(delta.getX()) == 1 &&
                  delta.getY() == 1 &&
                  board.getPiece(destination) != null)
         {
            path.add(destination);
         }
      }
      else if (team == PieceTeam.BLACK && delta.getY() < 0)
      {
         if (delta.getX() == 0)
         {
            // First Move
            if (position.getY() == board.getHeight() - 2 &&
                delta.getY() == -2)
            {
               path.add(new ChessVector(position.getX(), position.getY() - 1));
               path.add(destination);
            }
            else if (delta.getY() == -1)
            {
               path.add(destination);
            }
         }
         // En Passant
         else if (Math.abs(delta.getX()) == 1 &&
                  delta.getY() == -1 &&
                  board.getPiece(destination) != null)
         {
            path.add(destination);
         }
      }
      
      return path;
   }
}
