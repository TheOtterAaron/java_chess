package org.otterlabs.hosting.gord1861.FinalChess;

import java.util.Dictionary;
import java.util.Hashtable;

import javax.swing.*;

/**
 * Exposes static helper methods for loading Swing icons from disk.  Note that icons are lazy-loaded and cached, meaning
 * no manual initialization or caching is necessary.
 * @author Aaron Gordon
 *
 */
public class SwingPieceIconFactory
{
   private static Dictionary<String, Icon> icons;
   
   public static Icon getIcon(Piece piece)
   {
      if (icons == null)
      {
         icons = new Hashtable<String, Icon>();
      }
      
      String filename = filenameFromPiece(piece);
      
      if (icons.get(filename)  == null)
      {
         icons.put(filename, new ImageIcon(filename));
      }
      
      return icons.get(filename);
   }
   
   private static String filenameFromPiece(Piece piece)
   {
      String filename = "img\\";

      switch (piece.getType())
      {
      case PAWN:
         filename = filename.concat("p");
         break;
      case ROOK:
         filename = filename.concat("r");
         break;
      case KNIGHT:
         filename = filename.concat("n");
         break;
      case BISHOP:
         filename = filename.concat("b");
         break;
      case QUEEN:
         filename = filename.concat("q");
         break;
      case KING:
         filename = filename.concat("k");
         break;
      }
      
      switch (piece.getTeam())
      {
      case WHITE:
         filename = filename.concat("w");
         break;
      case BLACK:
         filename = filename.concat("b");
         break;
      }
      
      filename = filename.concat(".png");
      
      return filename;
   }
}
