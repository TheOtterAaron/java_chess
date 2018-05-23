package org.otterlabs.hosting.gord1861.FinalChess;

import java.util.ArrayList;
import java.util.List;

/**
 * Subclasses PieceBehavior to implement movement rules for the rook.  The rook may move any distance in a
 * straight line, but may not pass through other pieces. 
 * @author Aaron Gordon
 *
 */
public class RookBehavior extends PieceBehavior
{
   @Override
   protected List<ChessVector> calculatePath(Board board, ChessVector position, ChessVector destination)
   {
      List<ChessVector> path = new ArrayList<ChessVector>();
      
      ChessVector delta = destination.subtract(position);
      
      if (delta.getX() == 0)
      {
         if (delta.getY() < 0)
         {
            for (int dy = -1; dy >= delta.getY(); dy--)
            {
               ChessVector step = new ChessVector(position.getX(), position.getY() + dy);
               path.add(step);
            }
         }
         else if (delta.getY() > 0)
         {
            for (int dy = 1; dy <= delta.getY(); dy++)
            {
               ChessVector step = new ChessVector(position.getX(), position.getY() + dy);
               path.add(step);
            }
         }
      }
      else if (delta.getY() == 0)
      {
         if (delta.getX() < 0)
         {
            for (int dx = -1; dx >= delta.getX(); dx--)
            {
               ChessVector step = new ChessVector(position.getX() + dx, position.getY());
               path.add(step);
            }
         }
         else if (delta.getX() > 0)
         {
            for (int dx = 1; dx <= delta.getX(); dx++)
            {
               ChessVector step = new ChessVector(position.getX() + dx, position.getY());
               path.add(step);
            }
         }
      }
      
      return path;
   }
}
