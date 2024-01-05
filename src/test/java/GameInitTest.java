import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

import game.GameInit;

/**
 * The GameInitTest class contains test cases for the GameInit class functionalities.
 */

public class GameInitTest {

  /**
   * Tests the initialization of the game grid based on the provided size and difficulty.
   * Verifies that the grid is not null and has the expected dimensions.
   */

  @Test
  public void testGridInitialization() {
    GameInit game = new GameInit(5, "E");
    int[][] grid = game.getGrid();

    assertNotNull(grid);
    assertEquals(5, grid.length);
    assertEquals(5, grid[0].length);
  }

  /**
   * Tests bomb generation based on the difficulty level.
   * Checks if the number of bombs in the grid matches the expected count for the difficulty.
   */

  @Test
  public void testDifficultyBombs() {
    GameInit game = new GameInit(8, "M");
    int[][] grid = game.getGrid();

    int bombCount = 0;
    for (int[] row : grid) {
      for (int cell : row) {
        if (cell == -1) {
          bombCount++;
        }
      }
    }

    assertEquals((int) (8 * 8 * 0.2), bombCount); // Medium difficulty should have 20% bombs 
  }

  /**
   * Tests the calculation of neighboring bombs around specific cells in the grid.
   * Validates the nbBomb() method functionality using a predefined grid.
   */
  
  @Test
  public void testNeighboringBombs() {
    int[][] testGrid = {
        {-1, 0, 0},
        {0, 0, -1},
        {0, -1, 0}
    };

    assertEquals(
        1, GameInit.nbBomb(testGrid, 0, 2, 3)); 
    assertEquals(
        3, GameInit.nbBomb(testGrid, 1, 1, 3)); 
    assertEquals(
        2, GameInit.nbBomb(testGrid, 2, 2, 3));
  }
}
