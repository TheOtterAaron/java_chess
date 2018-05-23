package org.otterlabs.hosting.gord1861.FinalChess;

import java.util.ArrayList;
import java.util.List;

/**
 * Subclasses PieceBehavior to implement movement rules for the bishop.  The bishop may move any distance in a
 * diagonal line, but may not pass through other pieces. 
 * @author Aaron Gordon
 *
 */
public class BishopBehavior extends PieceBehavior
{
   @Override
   protected List<ChessVector> calculatePath(Board board, ChessVector position, ChessVector destination)
   {
      List<ChessVector> path = new ArrayList<ChessVector>();
      
      ChessVector delta = destination.subtract(position);
      
      // Movement is diagonal iff absolute value of X and Y are equal
      int magX = Math.abs(delta.getX());
      int magY = Math.abs(delta.getY());
      
      if (magX == magY &&
          magX != 0)
      {
         int xSign = delta.getX() / magX;
         int ySign = delta.getY() / magY;
         
         for (int d = 1; d <= magX; d++)
         {
            int dx = d * xSign;
            int dy = d * ySign;
            
            ChessVector step = new ChessVector(
                  position.getX() + dx,
                  position.getY() + dy);
            path.add(step);
         }
      }
      
      return path;
   }
}
