package org.otterlabs.hosting.gord1861.FinalChess;

/**
 * The main entry point for the program.  Initialized a GameModel, GameView and GameController.
 * @author Aaron Gordon
 *
 */
public class Main
{
   public static void main(String[] args)
   {
      GameModel model = new GameModel(8, 8);
      GameView view = new SwingGameView(model);
      
      // Originally I wanted to handle piece setup in a separate class
      // (via reading data from a plain text file) but I ran out of time
      // and so stuck it here
      Piece whitePawn = new Piece(PieceType.PAWN, PieceTeam.WHITE);
      Piece whiteRook = new Piece(PieceType.ROOK, PieceTeam.WHITE);
      Piece whiteKnight = new Piece(PieceType.KNIGHT, PieceTeam.WHITE);
      Piece whiteBishop = new Piece(PieceType.BISHOP, PieceTeam.WHITE);
      Piece whiteQueen = new Piece(PieceType.QUEEN, PieceTeam.WHITE);
      Piece whiteKing = new Piece(PieceType.KING, PieceTeam.WHITE);
      
      Piece blackPawn = new Piece(PieceType.PAWN, PieceTeam.BLACK);
      Piece blackRook = new Piece(PieceType.ROOK, PieceTeam.BLACK);
      Piece blackKnight = new Piece(PieceType.KNIGHT, PieceTeam.BLACK);
      Piece blackBishop = new Piece(PieceType.BISHOP, PieceTeam.BLACK);
      Piece blackQueen = new Piece(PieceType.QUEEN, PieceTeam.BLACK);
      Piece blackKing = new Piece(PieceType.KING, PieceTeam.BLACK);
      
      Board board = model.getBoard();
      
      // Pawns
      for (int i = 0; i < 8; i++)
      {
         board.addPiece(new ChessVector(i, 1), whitePawn);
         board.addPiece( new ChessVector(i, 6), blackPawn);
      }

      // Rooks
      board.addPiece(new ChessVector(0, 0), whiteRook);
      board.addPiece(new ChessVector(7, 0), whiteRook);
      
      board.addPiece(new ChessVector(0, 7), blackRook);
      board.addPiece(new ChessVector(7, 7), blackRook);
      
      // Knights
      board.addPiece(new ChessVector(1, 0), whiteKnight);
      board.addPiece(new ChessVector(6, 0), whiteKnight);
      
      board.addPiece(new ChessVector(1, 7), blackKnight);
      board.addPiece(new ChessVector(6, 7), blackKnight);
      
      // Bishops
      board.addPiece(new ChessVector(2, 0), whiteBishop);
      board.addPiece(new ChessVector(5, 0), whiteBishop);
      
      board.addPiece(new ChessVector(2, 7), blackBishop);
      board.addPiece(new ChessVector(5, 7), blackBishop);
      
      // Queens and Kings
      board.addPiece(new ChessVector(3, 0), whiteQueen);
      board.addPiece(new ChessVector(4, 0), whiteKing);
      
      board.addPiece(new ChessVector(3, 7), blackQueen);
      board.addPiece(new ChessVector(4, 7), blackKing);

      view.updateView(model);
      
      GameController controller = new GameController(model, view);
   }
}
