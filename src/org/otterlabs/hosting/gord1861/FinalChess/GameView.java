package org.otterlabs.hosting.gord1861.FinalChess;

/**
 * Interface to be implemented by classes intended for use as a GameView with the GameController class.  This interface
 * allows the same controller and model to be used with any view, such as a Swing UI view, a console display or even
 * an outbound network connection.
 * @author Aaron Gordon
 *
 */
public interface GameView
{
   public void addListener(GameViewListener listener);
   public void updateView(GameModel model);
   public void displayMessage(String message);
}
