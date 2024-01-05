package game;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * The Minesweeper class initializes and starts the Minesweeper game interface.
 * It handles user input for grid size and difficulty, then creates the game grid and GUI.
 */
public class Minesweeper {
  private static final Logger LOGGER = Logger.getLogger(Minesweeper.class.getName());

  /**
   * The main method of the Minesweeper game.
   * It initializes the game and starts the graphical user interface.
   *
   * @param args The command-line arguments (not used in this application).
   */
  public static void main(String[] args) {

    LOGGER.setLevel(Level.INFO); // Set the default logging level to INFO

    // Ask for grid size and difficulty
    int gridSize = Integer.parseInt(JOptionPane.showInputDialog("Enter grid size:"));
    String difficulty = JOptionPane.showInputDialog("Enter Difficulty (E/M/H):");

    // Initialize game
    GameInit game = new GameInit(gridSize, difficulty);
    int[][] grid = game.getGrid();

    // Start the game
    createandshowgui(grid, gridSize);
  }

  /**
   * Creates and displays the game's graphical user interface.
   *
   * @param grid     The grid representing the Minesweeper game.
   * @param gridSize The size of the grid.
   */
  private static void createandshowgui(int[][] grid, int gridSize) {
    JFrame frame = new JFrame("Minesweeper");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    JPanel panel = new JPanel(new GridLayout(gridSize, gridSize));
    JButton[][] buttons = new JButton[gridSize][gridSize];

    // Create buttons for the grid
    for (int i = 0; i < gridSize; i++) {
      for (int j = 0; j < gridSize; j++) {
        int finalI = i;
        int finalJ = j;
        JButton button = new JButton();
        button.addActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
            // Handle button click
            int cellContent = grid[finalI][finalJ];
            if (cellContent == -1) {
              buttons[finalI][finalJ].setText("B");
              LOGGER.log(Level.SEVERE, "Game Lost! You clicked on a bomb.");
              JOptionPane.showMessageDialog(
                  null,
                  "Game Lost! You clicked on a bomb.",
                  "Game Over", JOptionPane.INFORMATION_MESSAGE);
              frame.dispose();
            } else {
              buttons[finalI][finalJ].setText(String.valueOf(cellContent));
              LOGGER.log(Level.INFO,
                  "Clicked on cell ({0},{1}) with value {2}",
                  new Object[]{finalI, finalJ, cellContent});
            }
          }
        });

        buttons[i][j] = button;
        panel.add(button);
      }
    }

    frame.add(panel);
    frame.pack();
    frame.setVisible(true);
  }
}
