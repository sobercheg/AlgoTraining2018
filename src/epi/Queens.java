package epi;

import utils.UnitTester;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * EPI: Recursion, page 285
 * Generate All Nonattacking Placements Of n-Queens.
 */
public class Queens {

    private void place(int[] placed, int level, int size, List<int[]> placements) {
        if (level >= size) {
            placements.add(Arrays.copyOf(placed, placed.length));
            return;
        }
        // Try to place a queen in every column of the given level
        for (int col = 0; col < size; col++) {
            if (!isAttacking(level, col, placed)) {
                placed[level] = col; // place the queen
                place(placed, level + 1, size, placements);
            }
        }
    }

    // Checks if the queen at {level, col} is attacking any queen in placed (with rows < level).
    private boolean isAttacking(int level, int col, int[] placed) {
        if (level == 0) {
            return false;
        }
        for (int prevLevel = 0; prevLevel < level; prevLevel++) {
            if (col == placed[prevLevel] || Math.abs(col - placed[prevLevel]) == level - prevLevel) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        Queens q = new Queens();

        List<int[]> placements = new ArrayList<>();
        q.place(new int[5], 0, 5, placements);
        UnitTester.assertEquals(placements.size(), 10);

        placements = new ArrayList<>();
        q.place(new int[8], 0, 8, placements);
        UnitTester.assertEquals(placements.size(), 92);

        for (int[] placement : placements) {
            printPlacement(placement);
            System.out.println();
        }
    }

    private static void printPlacement(int[] cols) {
        char[] line = new char[cols.length];
        Arrays.fill(line, '-');
        for (int col : cols) {
            System.out.println(new StringBuilder(new String(line)).deleteCharAt(0).insert(col, "*"));
        }
    }

}
