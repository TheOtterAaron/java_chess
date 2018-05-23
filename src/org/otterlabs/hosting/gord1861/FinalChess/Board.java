package org.otterlabs.hosting.gord1861.FinalChess;

import java.util.Dictionary;
import java.util.Hashtable;

/**
 * Represents a chess board of arbitrary dimensions.  Pieces may be added to any open position on the board and removed
 * from the board.  Pieces already on the board may be added to any other open position on the board.  Note that the
 * board does not enforce any movement logic beyond forbidding the stacking of pieces.  Pieces on the board may be
 * looked up via board positions.
 * @author Aaron Gordon
 *
 */
public class Board
{
   private int width;
   private int height;
   private int[][] board;
   private Dictionary<Integer, Piece> pieces;
   private int handleCounter;
   private final int EMPTY_HANDLE = 0;
   
   public Board()
   {
      this(8, 8); // Standard Chess board
   }
   
   public Board(int width, int height)
   {
      this. width = width;
      this.height = height;

      board = new int[width][height];
      for (int x = 0; x < width; x++)
      {
         for (int y = 0; y < height; y++)
         {
            board[x][y] = EMPTY_HANDLE;
         }
      }
      
      pieces = new Hashtable<Integer, Piece>();      
      handleCounter = EMPTY_HANDLE;
   }
   
   public int getWidth()
   {
      return width;
   }
   
   public int getHeight()
   {
      return height;
   }
   
   public boolean isOnBoard(ChessVector position)
   {
      return isValidPosition(position);
   }
   
   public boolean addPiece(ChessVector position, Piece piece)
   {
      if (getPieceHandle(position) != EMPTY_HANDLE)
      {
         return false;
      }

      int pieceHandle = ++handleCounter;      
      setPieceHandle(position, pieceHandle);
      pieces.put(pieceHandle, piece);
      
      return true;
   }
   
   public Piece getPiece(ChessVector position)
   {
      int pieceHandle = getPieceHandle(position);
      if (pieceHandle == EMPTY_HANDLE)
      {
         return null;
      }
      
      return pieces.get(pieceHandle);
   }
   
   public Piece removePiece(ChessVector position)
   {
      int pieceHandle = getPieceHandle(position);
      if (pieceHandle == EMPTY_HANDLE)
      {
         return null;
      }
      
      Piece removedPiece = pieces.get(pieceHandle);
      
      setPieceHandle(position, EMPTY_HANDLE);
      pieces.remove(pieceHandle);
      
      return removedPiece;
   }
   
   public boolean movePiece(ChessVector position, ChessVector destination)
   {
      int srcPieceHandle = getPieceHandle(position);
      int dstPieceHandle = getPieceHandle(destination);
      
      if (srcPieceHandle == EMPTY_HANDLE)
      {
         return false;
      }
      
      if (dstPieceHandle != EMPTY_HANDLE)
      {
         return false;
      }
      
      setPieceHandle(position, EMPTY_HANDLE);
      setPieceHandle(destination, srcPieceHandle);
      
      return true;
   }
   
   private int getPieceHandle(ChessVector position)
   {
      if (isValidPosition(position))
      {
         return board[position.getX()][position.getY()];
      }
      return EMPTY_HANDLE;
   }
   
   private void setPieceHandle(ChessVector position, int handle)
   {
      if (isValidPosition(position))
      {
         board[position.getX()][position.getY()] = handle;
      }
   }
   
   private boolean isValidPosition(ChessVector position)
   {
      return position.getX() >= 0 &&
             position.getX() < width &&
             position.getY() >= 0 &&
             position.getY() < height;
   }
}
