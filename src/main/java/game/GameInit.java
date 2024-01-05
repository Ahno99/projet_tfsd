package game;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The GameInit class initializes the Minesweeper game grid and handles game setup.
 * It generates the grid based on the provided size and difficulty.
 */
public class GameInit {
    
  private int[][] grid;
  private static final Logger LOGGER = Logger.getLogger(GameInit.class.getName());

  /**
     * Constructor for GameInit.
     * Initializes the game grid based on the provided grid size and difficulty level.
     *
     * @param gridSize   The size of the game grid.
     * @param difficulty The difficulty level of the game (E - Easy, M - Medium, H - Hard).
     */

  public GameInit(int gridSize, String difficulty) {
    int bombs = diff(difficulty, gridSize);
    grid = finalgrid(gridSize, bombs);
  }

  /**
     * Retrieves the generated game grid.
     *
     * @return The generated game grid.
     */

  public int[][] getGrid() {
    return grid;
  }

  /**
     * Calculates the number of bombs based on the difficulty level and grid size.
     *
     * @param difficulty The difficulty level of the game.
     * @param gridSize   The size of the game grid.
     * @return The calculated number of bombs.
     */

  private static int diff(String difficulty, int gridsize) {

    int nbCells = gridsize * gridsize;
    int bombs;

    if (difficulty.equals("E")) {
      bombs = (int) Math.floor(nbCells * 10 / 100);
    } else if (difficulty.equals("M")) {
      bombs = (int) Math.floor(nbCells * 20 / 100);
    } else if (difficulty.equals("H")) {
      bombs = (int) Math.floor(nbCells * 30 / 100);
    } else {
      System.exit(1);
      LOGGER.log(Level.WARNING, "Invalid difficulty selected: {0}", difficulty);
      bombs = 0;
    }
    LOGGER.log(Level.INFO, "Number of bombs: {0}", bombs);
    return bombs;
  }

  /**
     * Creates the initial game grid with zeros and places bombs randomly.
     *
     * @param gridSize The size of the game grid.
     * @param nbBomb   The number of bombs to be placed.
     * @return The initialized game grid.
     */

  private static int[][] finalgrid(int gridsize, int nbBomb) {
    int[][] grid = initgrid(gridsize);
    grid = placeBomb(grid, nbBomb, gridsize);
    for (int i = 0; i < gridsize; i++) { // Fixed the assignment operator
      for (int j = 0; j < gridsize; j++) {
        if (grid[i][j] == 0) {
          grid[i][j] = nbBomb(grid, i, j, gridsize);
        }
      }
    }
    return grid;
  }

  /**
     * Initializes the game grid with zeros.
     *
     * @param gridSize The size of the game grid.
     * @return The initialized game grid.
     */

  private static int[][] initgrid(int gridsize) {
    int[][] grid = new int[gridsize][gridsize];
        
    for (int i = 0; i < gridsize; i++) {
      for (int j = 0; j < gridsize; j++) {
        grid[i][j] = 0;             
      }
    }
    return grid;
  }

  /**
     * Places bombs randomly in the game grid.
     *
     * @param grid     The game grid.
     * @param bombs    The number of bombs to be placed.
     * @param gridSize The size of the game grid.
     * @return The game grid with bombs placed.
     */

  private static int[][] placeBomb(int[][] grid, int bombs, int gridsize) {
    int bombsPlaced = 0;
    while (bombsPlaced != bombs) {
      int i = new Random().nextInt(gridsize);
      int j = new Random().nextInt(gridsize);
      if (grid[i][j] != -1) { // Checking if there's no bomb already
        grid[i][j] = -1;
        bombsPlaced++;
      }
    }
    return grid;
  }

  /**
     * Counts the number of neighboring bombs for a specific cell in the game grid.
     *
     * @param grid     The game grid.
     * @param i        The row index of the cell.
     * @param j        The column index of the cell.
     * @return The number of neighboring bombs.
     */
    
  public static int nbBomb(int[][] grid, int i, int j, int gridsize) {
    int counter = 0;

    //top left side
    if (i > 0 && j > 0) {
      if (grid[i - 1][j - 1] == -1) {
        counter++;
      }
    }
    //top  side
    if (i > 0) {
      if (grid[i - 1][j] == -1) {
        counter++;
      }
    }
    //top right side
    if (i > 0 && j < gridsize - 1) {
      if (grid[i - 1][j + 1] == -1) {
        counter++;
      }
    }
    //left side
    if (j > 0) {
      if (grid[i][j - 1] == -1) {
        counter++;
      }
    }
    //right side
    if (j < gridsize - 1) {
      if (grid[i][j + 1] == -1) {
        counter++;
      }
    }
    //bottom left side
    if (i < gridsize - 1 && j > 0) {
      if (grid[i + 1][j - 1] == -1) {
        counter++;
      }
    }
    //bottom side
    if (i < gridsize - 1) {
      if (grid[i + 1][j] == -1) {
        counter++;
      }
    }
    //bottom right side
    if (i < gridsize - 1 && j < gridsize - 1) {
      if (grid[i + 1][j + 1] == -1) {
        counter++;
      }
    }
    return counter;
  }
}
