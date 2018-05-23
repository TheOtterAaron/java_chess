package org.otterlabs.hosting.gord1861.FinalChess;

/**
 * Stores two integer components (x, y) and exposes helper methods for working with them.  Intended for use in
 * enumerating and peforming calculations with board positions.  
 * @author Aaron Gordon
 *
 */
public class ChessVector
{
   private int x;
   private int y;
   
   public ChessVector(int x, int y)
   {
      this.x = x;
      this.y = y;
   }
   
   public int getX()
   {
      return x;
   }
   
   public int getY()
   {
      return y;
   }
   
   public ChessVector add(ChessVector operand)
   {
      return new ChessVector(
            x + operand.getX(),
            y + operand.getY());
   }
   
   public ChessVector subtract(ChessVector operand)
   {
      return new ChessVector(
            x - operand.getX(),
            y - operand.getY());
   }
   
   public boolean isEqual(ChessVector operand)
   {
      return this.x == operand.getX() &&
             this.y == operand.getY();
   }
   
   @Override
   public ChessVector clone()
   {
      return new ChessVector(
            this.x,
            this.y);
   }
   
   @Override
   public String toString()
   {
      return rank(x) + String.valueOf(y + 1);
   }
   
   private String rank(int x)
   {
      return Character.toString((char)(65 + x));
   }
}
