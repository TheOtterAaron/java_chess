package org.otterlabs.hosting.gord1861.FinalChess;

import java.util.Dictionary;
import java.util.Hashtable;

/**
 * Exposes static helper methods for identifying which subclass of PieceBehavior to use for a given Piece.  Note that
 * PieceBehaviors are lazy-loaded and cached, meaning no manual initialization or caching is necessary.
 * @author Aaron Gordon
 *
 */
public class PieceBehaviorFactory
{
   private static Dictionary<PieceType, PieceBehavior> behaviors = new Hashtable<PieceType, PieceBehavior>();
   
   public static PieceBehavior getPieceBehavior(Piece piece)
   {
      PieceType pieceType = piece.getType();
      
      if (behaviors.get(pieceType) == null)
      {
         PieceBehavior pieceBehavior; 
         
         switch (pieceType)
         {
         case PAWN:
            pieceBehavior = new PawnBehavior();
            break;
         case ROOK:
            pieceBehavior = new RookBehavior();
            break;
         case KNIGHT:
            pieceBehavior = new KnightBehavior();
            break;
         case BISHOP:
            pieceBehavior = new BishopBehavior();
            break;
         case QUEEN:
            pieceBehavior = new QueenBehavior();
            break;
         case KING:
            pieceBehavior = new KingBehavior();
            break;
         default:
            return null;
         }
         
         behaviors.put(pieceType, pieceBehavior);
      }
      
      return behaviors.get(pieceType);
   }
}
