package epi;

import utils.UnitTester;

import java.util.Arrays;

/**
 * EPI: Recursion, page 296
 * Sudoku Solver
 */
public class SudokuSolver {

    private boolean solvePartialSudoku(int[][] grid, int i, int j) {
        if (j == grid[0].length) {
            j = 0;
            i++;
            if (i == grid.length) {
                return true;
            }
        }

        if (grid[i][j] != 0) {
            // Skip the element if it is already filled
            return solvePartialSudoku(grid, i, j + 1);
        }

        // Try to insert all possible values into a cell
        for (int val = 1; val <= grid.length; val++) {
            if (isValidAssignment(grid, i, j, val)) {
                grid[i][j] = val;
                if (solvePartialSudoku(grid, i, j + 1)) {
                    return true;
                }
            }
        }
        grid[i][j] = 0; // Undo assignment
        return false;
    }

    private boolean isValidAssignment(int[][] grid, int i, int j, int val) {
        // Check the column
        for (int r = 0; r < grid.length; r++) {
            if (grid[r][j] == val) {
                return false;
            }
        }

        // Check the row
        for (int c = 0; c < grid[0].length; c++) {
            if (grid[i][c] == val) {
                return false;
            }
        }

        // Check the area
        int areaSize = (int) Math.sqrt(grid.length);
        int areaRow = i / areaSize;
        int areaCol = j / areaSize;
        for (int r = 0; r < areaSize; r++) {
            for (int c = 0; c < areaSize; c++) {
                if (grid[areaRow * areaSize + r][areaCol * areaSize + c] == val) {
                    return false;
                }
            }

        }

        return true; // Valid
    }

    public static void main(String[] args) {
        SudokuSolver s = new SudokuSolver();
        int[][] grid = new int[][]{
                {0, 2, 6, 0, 0, 0, 8, 1, 0},
                {3, 0, 0, 7, 0, 8, 0, 0, 6},
                {4, 0, 0, 0, 5, 0, 0, 0, 7},
                {0, 5, 0, 1, 0, 7, 0, 9, 0},
                {0, 0, 3, 9, 0, 5, 1, 0, 0},
                {0, 4, 0, 3, 0, 2, 0, 5, 0},
                {1, 0, 0, 0, 3, 0, 0, 0, 2},
                {5, 0, 0, 2, 0, 4, 0, 0, 9},
                {0, 3, 8, 0, 0, 0, 4, 6, 0}
        };
        boolean isValid = s.solvePartialSudoku(grid, 0, 0);
        System.out.println("Is valid: " + isValid);
        System.out.println(Arrays.deepToString(grid));
        UnitTester.assertEquals(true, isValid);
        UnitTester.assertArrayEquals(
                new int[][]{
                        {7, 2, 6, 4, 9, 3, 8, 1, 5},
                        {3, 1, 5, 7, 2, 8, 9, 4, 6},
                        {4, 8, 9, 6, 5, 1, 2, 3, 7},
                        {8, 5, 2, 1, 4, 7, 6, 9, 3},
                        {6, 7, 3, 9, 8, 5, 1, 2, 4},
                        {9, 4, 1, 3, 6, 2, 7, 5, 8},
                        {1, 9, 4, 8, 3, 6, 5, 7, 2},
                        {5, 6, 7, 2, 1, 4, 3, 8, 9},
                        {2, 3, 8, 5, 7, 9, 4, 6, 1}},
                grid);
    }
}
