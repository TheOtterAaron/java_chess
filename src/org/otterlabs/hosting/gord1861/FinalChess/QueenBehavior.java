package org.otterlabs.hosting.gord1861.FinalChess;

import java.util.List;

/**
 * Subclasses PieceBehavior to implement movement rules for the queen.  The queen has the combined movement
 * capabilities of both the bishop and the rook.
 * @author Aaron Gordon
 *
 */
public class QueenBehavior extends PieceBehavior
{
   // NOTE: Queen is a combination of bishop and rook, so we override PieceBehavior's isValidMove method and leave
   // calculatePath effectively unimplemented.  This should not be the typical paradigm used when extending
   // PieceBehavior. 
   
   @Override
   public boolean isValidMove(Board board, ChessVector position, ChessVector destination)
   {
      RookBehavior rookBehavior = new RookBehavior();
      BishopBehavior bishopBehavior = new BishopBehavior();
      
      return rookBehavior.isValidMove(board, position, destination) ||
             bishopBehavior.isValidMove(board, position, destination);
   }
   
   @Override
   protected List<ChessVector> calculatePath(Board board, ChessVector position, ChessVector destination)
   {
      // Unused
      return null;
   }
}
