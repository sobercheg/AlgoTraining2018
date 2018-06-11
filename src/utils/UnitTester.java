package utils;

import java.util.Arrays;

/**
 * Common utils for testing.
 */
public class UnitTester {
    public static void assertEquals(Object expected, Object actual) {
        if (checkNulls(expected, actual)) return;
        if (!expected.equals(actual))
            throw new IllegalStateException(String.format("Expected [%s] is not equal to actual [%s]", expected, actual));
    }

    public static void assertEquals(int[] expected, int[] actual) {
        if (checkNulls(expected, actual)) return;
        assertEquals(expected, actual, Math.max(expected.length, actual.length));
    }

    public static void assertEquals(int[] expected, int[] actual, int size) {
        for (int i = 0; i < size; i++) {
            if (actual.length <= i || expected.length <= i || expected[i] != actual[i])
                throw new IllegalStateException(String.format("Expected [%s] is not equal to actual [%s]",
                        Arrays.toString(expected), Arrays.toString(actual)));
        }
    }

    public static void assertEquals(String[] expected, String[] actual) {
        if (checkNulls(expected, actual)) return;
        if (!Arrays.equals(expected, actual))
            throw new IllegalStateException(String.format("Expected [%s] is not equal to actual [%s]",
                    Arrays.toString(expected), Arrays.toString(actual)));
    }

    public static void assertArrayEquals(char[][] expected, char[][] actual) {
        if (checkNulls(expected, actual)) return;
        if (!Arrays.deepEquals(expected, actual))
            throw new IllegalStateException(String.format("Expected [%s] is not equal to actual [%s]",
                    Arrays.deepToString(expected), Arrays.deepToString(actual)));
    }

    public static void assertArrayEquals(int[][] expected, int[][] actual) {
        if (checkNulls(expected, actual)) return;
        if (!Arrays.deepEquals(expected, actual))
            throw new IllegalStateException(String.format("Expected [%s] is not equal to actual [%s]",
                    Arrays.deepToString(expected), Arrays.deepToString(actual)));
    }

    private static boolean checkNulls(Object expected, Object actual) {
        if (expected == null && actual == null) return true;
        if (expected == null) throw new IllegalStateException("Actual is not null");
        if (actual == null) throw new IllegalStateException("Expected is not null");
        return false;
    }
}
