package org.otterlabs.hosting.gord1861.FinalChess;

import java.util.ArrayList;
import java.util.List;

/**
 * Subclasses PieceBehavior to implement movement rules for the knight.  The knight moves in an "L" shape, which may
 * also be thought of as moving any three squares away provided the resulting motion is not a straight line.
 * @author Aaron Gordon
 *
 */
public class KnightBehavior extends PieceBehavior
{
   @Override
   protected List<ChessVector> calculatePath(Board board, ChessVector position, ChessVector destination)
   {
      List<ChessVector> path = new ArrayList<ChessVector>();
      
      ChessVector delta = destination.subtract(position);
      
      // Knight must move three squares away, but can't move in a straight line (x and y can't be zero)
      int manhattanDistance = Math.abs(delta.getX()) + Math.abs(delta.getY());
      if (manhattanDistance == 3 &&
          delta.getX() != 0 &&
          delta.getY() != 0)
      {
         path.add(destination);
      }
      
      return path;
   }
}
