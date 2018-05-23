package org.otterlabs.hosting.gord1861.FinalChess;

import java.util.ArrayList;
import java.util.List;

/**
 * Subclasses PieceBehavior to implement movement rules for the king.  The king may move to any adjacent square.
 * @author Aaron Gordon
 *
 */
public class KingBehavior extends PieceBehavior
{
   @Override
   protected List<ChessVector> calculatePath(Board board, ChessVector position, ChessVector destination)
   {
      List<ChessVector> path = new ArrayList<ChessVector>();
      
      ChessVector delta = destination.subtract(position);
      
      // Adjacent squares have a distance of one
      if (Math.abs(delta.getX()) <= 1 &&
          Math.abs(delta.getY()) <= 1)
      {
         path.add(destination);
      }
      
      return path;
   }
}
