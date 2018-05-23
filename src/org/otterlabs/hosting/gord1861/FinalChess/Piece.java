package org.otterlabs.hosting.gord1861.FinalChess;

/**
 * Represents a chess piece, including both its type (pawn, rook, etc.) and team (white or black).
 * @author Aaron Gordon
 *
 */
public class Piece
{
   private PieceType type;
   private PieceTeam team;
   
   public Piece(PieceType type, PieceTeam team)
   {
      this.type = type;
      this.team = team;
   }
   
   public PieceType getType()
   {
      return type;
   }
   
   public PieceTeam getTeam()
   {
      return team;
   }
   
   @Override
   public String toString()
   {
      return team.toString() + " " + type.toString();
   }
}
