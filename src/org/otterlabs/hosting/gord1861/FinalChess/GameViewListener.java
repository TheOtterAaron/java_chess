package org.otterlabs.hosting.gord1861.FinalChess;

/**
 * Interface to be implemented by classes that need to respond to a GameView.
 * @author Aaron
 *
 */
public interface GameViewListener
{
   public void positionSelected(ChessVector position);
}
