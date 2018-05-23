package org.otterlabs.hosting.gord1861.FinalChess;

/**
 * Implements high-level game rules via a GameViewListener implementation.  This class functions as the "glue" between
 * GameView and GameModel.  Note that only high-level rules are implemented in this class, such as turn order and win
 * conditions.  Rules relating to individual pieces are handled by their respective PieceBehavior subclasses.
 * @author Aaron Gordon
 *
 */
public class GameController
{
   private GameModel model;
   private GameView view;
   
   private PieceTeam currentPlayer;
   private ChessVector selectedPosition; 
   
   public GameController(GameModel model, GameView view)
   {
      this.model = model;
      this.view = view;
      
      this.view.addListener(new GameControllerViewListener());
      
      currentPlayer = PieceTeam.WHITE;
      selectedPosition = null;
   }
   
   private class GameControllerViewListener implements GameViewListener
   {
      @Override
      public void positionSelected(ChessVector position)
      {
         if (selectedPosition == null)
         {
            Piece selectedPiece = model.getBoard().getPiece(position);            
            if (selectedPiece != null &&
                selectedPiece.getTeam() == currentPlayer)
            {
               DebugLog.write("Selected " + selectedPiece);
               selectedPosition = position;
            }
         }
         else if (selectedPosition.isEqual(position))
         {
            return;
         }
         else
         {
            ChessVector selectedDestination = position;
            Piece selectedPiece = model.getBoard().getPiece(selectedPosition);
            
            if (PieceBehaviorFactory.getPieceBehavior(selectedPiece).isValidMove(
                  model.getBoard(),
                  selectedPosition,
                  selectedDestination))
            {
               DebugLog.write(selectedPiece + " at " + selectedPosition + " to " + selectedDestination);
               
               Piece capturedPiece = model.getBoard().getPiece(selectedDestination); 
               if (capturedPiece != null)
               {
                  DebugLog.write(capturedPiece + " captured");
                  model.addCapturedPiece(model.getBoard().removePiece(selectedDestination));
                  
                  if (capturedPiece.getType() == PieceType.KING)
                  {
                     DebugLog.write(currentPlayer + " captured their opponents king");
                     view.displayMessage(currentPlayer + " WINS!");
                  }
               }
               model.getBoard().movePiece(selectedPosition, selectedDestination);

               if (selectedPiece.getType() == PieceType.PAWN &&
                   (selectedDestination.getY() == model.getBoard().getHeight() - 1 ||
                    selectedDestination.getY() == 0))
               {
                  DebugLog.write(selectedPiece + " promoted to QUEEN");
                  model.getBoard().removePiece(selectedDestination);
                  model.getBoard().addPiece(
                        selectedDestination,
                        new Piece(PieceType.QUEEN, selectedPiece.getTeam()));
               }
               
               currentPlayer = (currentPlayer == PieceTeam.WHITE) ? PieceTeam.BLACK : PieceTeam.WHITE;
               DebugLog.write(currentPlayer + "\'s turn");

               selectedPosition = null;
               
               view.updateView(model);
            }
            else
            {
               selectedPiece = model.getBoard().getPiece(position);
               if (selectedPiece != null &&
                   selectedPiece.getTeam() == currentPlayer)
               {
                  DebugLog.write("Selected " + selectedPiece); 
                  selectedPosition = position;
               }
               else
               {
                  DebugLog.write("Invalid move, piece deselected");
                  selectedPosition = null;
               }
            }
         }
      }
   }
}
