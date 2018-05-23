package org.otterlabs.hosting.gord1861.FinalChess;

import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.*;

/**
 * Implementation of the GameView interface that uses Java swing to render the board and pieces to the screen. 
 * @author Aaron Gordon
 *
 */
public class SwingGameView extends JFrame implements GameView
{
   private static final int SQUARE_SIZE = 64;
   
   private int boardWidth;
   private int boardHeight;
   private JPanel[][] squares;
   
   public SwingGameView(GameModel model)
   {
      boardWidth = model.getBoard().getWidth();
      boardHeight = model.getBoard().getHeight();

      setLocationRelativeTo(null);
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setTitle("Final Chess");
      setSize(boardWidth * SQUARE_SIZE, boardHeight * SQUARE_SIZE);
      setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
      setLayout(new GridLayout(boardHeight, boardWidth));
      
      squares = new JPanel[boardWidth][boardHeight];
      for (int y = boardHeight - 1; y >= 0; y--)
      {
         for (int x = 0; x < boardWidth; x++)
         {
            JPanel square = new JPanel();
            squares[x][y] = square;
            
            if ((x - y) % 2 == 0)
            {
               square.setBackground(Color.decode("#decbad"));
            }
            else
            {
               square.setBackground(Color.decode("#615148"));
            }
            square.setSize(SQUARE_SIZE, SQUARE_SIZE);
            add(square);

            JLabel label = new JLabel();
            label.setBorder(BorderFactory.createEmptyBorder(4, 0, 0, 0));
            square.add(label);
            
         }
      }
      
      setVisible(true);
   }
   
   @Override
   public void addListener(GameViewListener listener)
   {
      for (int y = boardHeight - 1; y >= 0; y--)
      {
         for (int x = 0; x < boardWidth; x++)
         {
            JPanel square = squares[x][y];
            square.addMouseListener(new SwingGameViewMouseListener(listener, x, y));
         }
      }
   }
   
   @Override
   public void updateView(GameModel model)
   {
      if (model.getBoard().getWidth() != boardWidth ||
          model.getBoard().getHeight() != boardHeight)
      {
         return;
      }
      
      for (int x = 0; x < boardWidth; x++)
      {
         for (int y = 0; y < boardHeight; y++)
         {
            JPanel square = squares[x][y]; 
            JLabel label = (JLabel)(square.getComponent(0));
            
            Piece piece = model.getBoard().getPiece(new ChessVector(x, y));
            if (piece == null)
            {
               label.setIcon(null);
               continue;
            }
            
            label.setIcon(SwingPieceIconFactory.getIcon(piece));
         }
      }
   }
   
   @Override
   public void displayMessage(String message)
   {
      JOptionPane.showMessageDialog(null, message);
   }
   
   /**
    * Nested private class used to pass mouse events onto a GameViewListener and to handle highlighting of the board
    * square beneath the mouse cursor.
    * @author Aaron Gordon
    *
    */
   private class SwingGameViewMouseListener implements MouseListener
   {
      private GameViewListener listener;
      private int x;
      private int y;      
      private Color color;
      
      public SwingGameViewMouseListener(GameViewListener listener, int x, int y)
      {
         this.listener = listener;
         this.x = x;
         this.y = y;
         
         JPanel square = squares[x][y];
         color = square.getBackground();
      }
      
      @Override
      public void mouseClicked(MouseEvent e)
      {
         listener.positionSelected(new ChessVector(x, y));
      }

      @Override
      public void mouseEntered(MouseEvent e)
      {
         squares[x][y].setBackground(Color.decode("#adcade"));
      }

      @Override
      public void mouseExited(MouseEvent e)
      {
         squares[x][y].setBackground(color);
      }

      @Override
      public void mousePressed(MouseEvent e)
      {
         squares[x][y].setBackground(Color.decode("#d8621f"));
      }

      @Override
      public void mouseReleased(MouseEvent e)
      {
         squares[x][y].setBackground(color);
      }  
   }
}
